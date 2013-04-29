package ch.xcrux.schlepper;

/**
 * Buran.
 *
 * @author: ${USER} Date: 28.04.13 Time: 23:13
 */
public class RefDataTaskItem<TData extends Object, TMetadata extends IMetadata,
        TRetData extends Object> implements ITaskItem {
    private final int baseIndex;
    private final IChange<TData, TMetadata, TRetData> task;

    public RefDataTaskItem(int baseIndex, IChange<TData, TMetadata, TRetData> task) {
        this.baseIndex = baseIndex;
        this.task = task;
    }

    public int getBaseIndex() {
        return baseIndex;
    }

    public IChange<TData, TMetadata, TRetData> getTask() {
        return task;
    }
}
