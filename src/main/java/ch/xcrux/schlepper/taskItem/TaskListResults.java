package ch.xcrux.schlepper.taskItem;

import java.util.HashMap;
import java.util.Map;

/**
 * Buran.
 *
 * @author: ${USER} Date: 28.04.13 Time: 23:21
 */
public class TaskListResults {
    Map<Integer, Object> results = new HashMap<>();

    public Map<Integer, Object> getResults() {
        return results;
    }

    public Object get(ResultIndex index) {
        return this.results.get((int) index.getIndex());
    }
}
