package ch.xcrux.schlepper.impl;

import ch.xcrux.schlepper.*;
import ch.xcrux.schlepper.changes.*;
import ch.xcrux.schlepper.interceptors.*;
import ch.xcrux.schlepper.meta.IMetadata;
import ch.xcrux.schlepper.meta.MetadataId;
import ch.xcrux.schlepper.taskItem.*;
import com.google.common.base.Optional;
import com.sun.istack.internal.Nullable;

import java.util.*;
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

    private int interceptorIdIncrementor;

    private TypeRegistry typeRegistry;

    private final Set<InterceptorImpl> interceptorSet = new HashSet<>();

    private final Map<Uid, DataId> guidToDataIdMap = new HashMap<>();

    private TypeRegistry getTypeRegistry() {
        if (this.typeRegistry == null) {
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
    public <T extends IType> T getFacade(DataId dataId, Class<T> type) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void shutdown() {
        this.executorService.shutdown();
    }

    public InterceptorId addInterception(IThreadSwitcher threadSwitcher, IInterceptor interceptor) {
        this.interceptorIdIncrementor++;
        InterceptorId ici = new InterceptorId(this.interceptorIdIncrementor);
        this.interceptorSet.add(new InterceptorImpl(ici, threadSwitcher, interceptor));
        return ici;
    }

    private DataId generateUniqueDataId() {
        this.dataIdIncrementor++;
        return new DataId(this.dataIdIncrementor - 1);
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
        public void connectToUid(DataId dataId, Uid uid) {
            guidToDataIdMap.put(uid, dataId);
        }

        @Override
        public Object createData(IMetadata metadata) {
            final IFactory<?, ?, ?> type = getTypeRegistry().getFacade(metadata.getTypeId());
            return type.createDataInstance();
        }
    };

    private void invokeInterceptors(TaskListResults tlResults,
            List<ProcessedTaskItem> processedTaskItems) {
        final Map<IThreadSwitcher, List<Interception>> results = new HashMap<>();
        for (final InterceptorImpl interceptor : interceptorSet) {
            for (final ProcessedTaskItem pti : processedTaskItems) {

                IThreadSwitcher ts = null;
                InterceptionResult ir = null;
                InterceptorId interceptorId = interceptor.getInterceptionId();

                if ((pti.getDataId() != null) &&
                        (interceptor.getInterceptor() instanceof IDataInterceptor)) {
                    final IDataInterceptor dataInterceptor =
                            (IDataInterceptor) interceptor.getInterceptor();

                    final boolean classMatch =
                            (dataInterceptor.getChangeClass().equals(pti.getChange().getClass()));
                    final boolean didMatch = (dataInterceptor.getDataId().getDataId() != null) &&
                            (dataInterceptor.getDataId().getDataId().equals(pti.getDataId()));
                    final boolean uidMatch = (dataInterceptor.getDataId().getUid() != null) &&
                            (pti.getDataId().equals(this.guidToDataIdMap
                                    .get(dataInterceptor.getDataId().getUid())));

                    if (classMatch && (didMatch || uidMatch)) {
                       /* Interception matches? */
                        Object originalRetVal = tlResults.get(pti.getResultIndex());
                        final InterceptionResult mv =
                                dataInterceptor.matches(pti.getChange(), originalRetVal);
                        if (mv != null) {
                            ir = mv;
                            ts = interceptor.getSwitcher();
                        }
                    }
                } else {
                    if ((pti.getStoreChange() != null) &&
                            (interceptor.getInterceptor() instanceof IGlobalInterceptor)) {
                        IGlobalInterceptor globalInterceptor =
                                (IGlobalInterceptor) interceptor.getInterceptor();

                        if (globalInterceptor.getSupportedChangeClass()
                                .equals(pti.getStoreChange().getClass())) {
                            Object originalRetVal = tlResults.get(pti.getResultIndex());
                            final InterceptionResult mv =
                                    globalInterceptor.matches(pti.getStoreChange(), originalRetVal);
                            if (mv != null) {
                                ir = mv;
                                ts = interceptor.getSwitcher();
                            }
                        }
                    }
                }

                if (ir != null) {
                    List<Interception> ic = results.get(ts);
                    if (ic == null) {
                        ic = new ArrayList<>();
                        results.put(ts, ic);
                        ic.add(new Interception(interceptorId,
                                Optional.fromNullable(ir.getRetVal())));
                    }
                }
            }

            /* Invoke */
            for (final Map.Entry<IThreadSwitcher, List<Interception>> entries : results
                    .entrySet()) {
                final IThreadSwitcher threadSwitcher = entries.getKey();
                threadSwitcher.invoke(entries.getValue());
            }
        }

    }

    private TaskListResults processInThread(TaskSet taskSet) {
        TaskListResults results = new TaskListResults();
        List<ProcessedTaskItem> resultsForIncerceptors = new ArrayList<>();

        for (int i = 0; i < taskSet.getSize(); i++) {
            final ITaskItem taskItem = taskSet.getItem(new ResultIndex(i));
            if (taskItem instanceof IStoreChange) {
                IStoreChange storeChange = (IStoreChange) taskItem;
                IRollbackableStoreChange rc = storeChange.createChange();
                final StoreChangeInfo performResult = rc.perform(this.si);
                if (performResult.isHasRetValue()) {
                    results.getResults().put(i, performResult.getRetVal());
                    System.out.println("Stored at index " + i);
                } else {
                    System.out.println("No return at index " + i);

                }
                if (performResult.isDidModify()) {
                    resultsForIncerceptors
                            .add(ProcessedTaskItem.change(new ResultIndex(i), storeChange));
                }
                continue;
            }

            DataId dataId = null;
            IChange change = null;
            if (taskItem instanceof DataTaskItem) {
                DataTaskItem dti = (DataTaskItem) taskItem;
                dataId = dti.getDataId();
                change = dti.getTask();
            }
            if (taskItem instanceof RefDataTaskItem) {
                RefDataTaskItem rdti = (RefDataTaskItem) taskItem;
                final IDataIdProvider dataIdProvider =
                        (IDataIdProvider) results.getResults().get(rdti.getBaseIndex());
                if (dataIdProvider == null) {
                    throw new IllegalStateException("No result found at index " + i);
                }
                dataId = dataIdProvider.getDataId();
                change = rdti.getTask();
            }
            if (taskItem instanceof UidTaskItem) {
                final UidTaskItem uidti = (UidTaskItem) taskItem;
                dataId = this.guidToDataIdMap.get(uidti.getUid());
                change = uidti.getTask();
            }

            final IRollbackableChange rbc = change.createChange();
            final MetadataId metadataId = dataToMetadata.get(dataId);
            final IMetadata metadata1 = metadata.get(metadataId);
            final Object currentData = data.get(dataId);
            final ChangeInfo ci = rbc.perform(dataId, metadata1, currentData, this.si);
            if (ci.isHasRetValue()) {
                results.getResults().put(i, ci.getRetValue());
            }
            if (ci.isUseNewValue()) {
                data.put(dataId, ci.getNewValue());
            }
            if (ci.isDidModify() || ci.isUseNewValue()) {
                resultsForIncerceptors
                        .add(ProcessedTaskItem.change(new ResultIndex(i), dataId, change));
            }
        }

        /* Invoke interceptors */
        invokeInterceptors(results, resultsForIncerceptors);

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
