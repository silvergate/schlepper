package ch.xcrux.schlepper.taskItem;

import ch.xcrux.schlepper.Uid;
import ch.xcrux.schlepper.changes.IChange;
import ch.xcrux.schlepper.meta.IMetadata;

/**
 * Buran.
 *
 * @author: ${USER} Date: 28.04.13 Time: 23:12
 */
public class UidTaskItem<TData extends Object, TMetadata extends IMetadata, TRetData extends Object>
        implements ITaskItem {
    private final Uid uid;
    private final IChange<TData, TMetadata, TRetData> task;

    public UidTaskItem(Uid uid, IChange<TData, TMetadata, TRetData> task) {
        this.uid = uid;
        this.task = task;
    }

    public Uid getUid() {
        return uid;
    }

    public IChange<TData, TMetadata, TRetData> getTask() {
        return task;
    }
}
