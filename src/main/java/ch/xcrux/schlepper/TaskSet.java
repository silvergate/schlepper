package ch.xcrux.schlepper;

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
        return this.taskItemList.size()-1;
    }

    public int getSize() {
        return this.taskItemList.size();
    }

    public ITaskItem getItem(int index) {
        return this.taskItemList.get(index);
    }

    public int add(DataId dataId, IChange<?, ?, ?> change) {
        return add(new DataTaskItem<>(dataId, change));
    }

    public int add(int baseIndex, IChange<?, ?, ?> change) {
        return add(new RefDataTaskItem<>(baseIndex, change));
    }
}
