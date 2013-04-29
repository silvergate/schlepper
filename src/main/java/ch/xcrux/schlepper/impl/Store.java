package ch.xcrux.schlepper.impl;

import ch.xcrux.schlepper.*;
import ch.xcrux.schlepper.globals.AddData;
import ch.xcrux.schlepper.globals.DataIdAndMetadataId;
import com.sun.istack.internal.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Buran.
 *
 * @author: ${USER} Date: 26.04.13 Time: 19:43
 */
public class Store implements IStore {

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    private final Map<DataId, Object> data = new HashMap<>();

    private final Map<DataId, MetadataId> dataToMetadata = new HashMap<>();

    private final Map<MetadataId, IMetadata> metadata = new HashMap<>();

    private int dataIdIncrementor;

    private int metadataIdInctementor;

    private TypeRegistry typeRegistry;

    private TypeRegistry getTypeRegistry() {
        if (this.typeRegistry==null) {
            this.typeRegistry = new TypeRegistry();
            this.typeRegistry.fill();
        }
        return this.typeRegistry;
    }

    @Override
    public void addChange(DataId dataId, IChange change) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void addChange(IChange change) {

        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public <T extends IFacade> T getFacade(DataId dataId, Class<T> type) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public <T extends Object> T requestAndGet(IGetRequest<T> request) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


    private DataId generateUniqueDataId() {
      this.dataIdIncrementor++;
        return new DataId(this.dataIdIncrementor-1);
    }

    private MetadataId storeMetadataGetId(IMetadata metadata) {
        this.metadataIdInctementor++;
        MetadataId mid = new MetadataId(this.metadataIdInctementor);
        this.metadata.put(mid, metadata);
        return mid;
    }

    private final IStoreInts si = new IStoreInts() {
        @Override
        public DataId createDataId(MetadataId metadataId) {
            final DataId did = generateUniqueDataId();
            dataToMetadata.put(did, metadataId);
            return did;
        }

        @Override
        public MetadataId createMetadataId(IMetadata metadata) {
            MetadataId metadataId = storeMetadataGetId(metadata);
            return metadataId;
        }

        @Override
        public Object storeValue(DataId dataId, @Nullable Object value) {
            return data.put(dataId, value);
        }

        @Override
        public Object removeValue(DataId dataId) {
            dataToMetadata.remove(dataId);
            return data.remove(dataId);
        }

        @Override
        public Object createData(IMetadata metadata) {
            final IFacadeFactory<?, ?, ?> type = getTypeRegistry().getFacade(metadata.getTypeId());
            return type.createDataInstance();
        }
    } ;

    private TaskListResults processInThread(TaskSet taskSet) {
        TaskListResults results = new TaskListResults();
        for (int i=0; i<taskSet.getSize(); i++) {
            final ITaskItem taskItem = taskSet.getItem(i);
            if (taskItem instanceof IStoreChange) {
                IStoreChange storeChange = (IStoreChange)taskItem;
                IRollbackableStoreChange rc = storeChange.createChange();
                final StoreChangeInfo performResult = rc.perform(this.si);
                if (performResult.isHasRetValue()) {
                    results.getResults().put(i, performResult.getRetVal());
                    System.out.println("Stored at index " + i);
                } else {
                    System.out.println("No return at index " + i);

                }
                continue;
            }

            DataId dataId = null;
            IChange change = null;
            if (taskItem instanceof DataTaskItem) {
                DataTaskItem dti = (DataTaskItem)taskItem;
                dataId = dti.getDataId();
                change = dti.getTask();
            }
            if (taskItem instanceof RefDataTaskItem) {
                RefDataTaskItem rdti = (RefDataTaskItem)taskItem;
                final IDataIdProvider dataIdProvider = (IDataIdProvider)results.getResults().get
                        (rdti.getBaseIndex());
                if (dataIdProvider==null) {
                    throw new IllegalStateException("No result found at index " + i);
                }
                dataId = dataIdProvider.getDataId();
                change = rdti.getTask();
            }

            final IRollbackableChange rbc = change.createChange();
            final MetadataId metadataId = dataToMetadata.get(dataId);
            final IMetadata metadata1 = metadata.get(metadataId);
            final Object currentData = data.get(dataId);
            final ChangeInfo ci = rbc.perform(dataId, metadata1, currentData);
            if (ci.isHasRetValue()) {
                results.getResults().put(i, ci.getRetValue());
            }
            if (ci.isUseNewValue()) {
                data.put(dataId, ci.getNewValue()) ;
            }
        }
        return results;
    }

    public TaskListResults process(final TaskSet taskSet)
            throws ExecutionException, InterruptedException {
        return this.executorService.submit(new Callable<TaskListResults>() {
            @Override
            public TaskListResults call() throws Exception {
                return processInThread(taskSet);
            }
        }).get();
    }
}
