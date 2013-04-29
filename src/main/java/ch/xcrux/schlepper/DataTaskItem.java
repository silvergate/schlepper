package ch.xcrux.schlepper;

/**
 * Buran.
 *
 * @author: ${USER} Date: 28.04.13 Time: 23:12
 */
public class DataTaskItem<TData extends Object, TMetadata extends IMetadata,
        TRetData extends Object>
        implements ITaskItem {
    private final DataId dataId;
    private final IChange<TData, TMetadata, TRetData> task;

    public DataTaskItem(DataId dataId, IChange<TData, TMetadata, TRetData> task) {
        this.dataId = dataId;
        this.task = task;
    }

    public DataId getDataId() {
        return dataId;
    }

    public IChange<TData, TMetadata, TRetData> getTask() {
        return task;
    }
}
