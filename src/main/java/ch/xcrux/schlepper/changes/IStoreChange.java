package ch.xcrux.schlepper.changes;

import ch.xcrux.schlepper.taskItem.ITaskItem;

/**
 * Buran.
 *
 * @author: ${USER} Date: 27.04.13 Time: 17:49
 */
public interface IStoreChange<TRetData extends Object> extends ITaskItem {
    IRollbackableStoreChange<TRetData> createChange();
}
