package ch.xcrux.schlepper.taskItem;

import ch.xcrux.schlepper.DataId;
import ch.xcrux.schlepper.Uid;
import ch.xcrux.schlepper.changes.IChange;

import java.util.ArrayList;
import java.util.List;

/**
 * Buran.
 *
 * @author: ${USER} Date: 28.04.13 Time: 23:06
 */
public class TaskSet {

    private final List<ITaskItem> taskItemList = new ArrayList<>();

    public int add(ITaskItem taskItem) {
        this.taskItemList.add(taskItem);
        return this.taskItemList.size() - 1;
    }

    public int getSize() {
        return this.taskItemList.size();
    }

    public ITaskItem getItem(ResultIndex index) {
        return this.taskItemList.get(index.getIndex());
    }

    public ResultIndex add(DataId dataId, IChange<?, ?, ?> change) {
        return new ResultIndex(add(new DataTaskItem<>(dataId, change)));
    }

    public ResultIndex add(Uid uid, IChange<?, ?, ?> change) {
        return new ResultIndex(add(new UidTaskItem<>(uid, change)));
    }

    public ResultIndex add(int baseIndex, IChange<?, ?, ?> change) {
        return new ResultIndex(add(new RefDataTaskItem<>(baseIndex, change)));
    }
}
