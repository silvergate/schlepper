package ch.dcrux.newSchlepper.commandProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * Buran.
 *
 * @author: ${USER} Date: 21.05.13 Time: 22:21
 */
public class ResultList {
    private final List<Object> resultList = new ArrayList<>();

    public void add(Object result) {
        this.resultList.add(result);
    }

    public <TRetval extends Object> TRetval get(CommandId commandId, Class<TRetval> retvalClass) {
        final Object value = this.resultList.get(commandId.getId());
        return (TRetval) value;
    }

    public <TRetval extends Object> TRetval getOrNull(CommandId commandId,
            Class<TRetval> retvalClass) {
        if (resultList.size() >= commandId.getId()) {
            return null;
        }
        final Object value = this.resultList.get(commandId.getId());
        if (value == null) {
            return null;
        }
        if (!retvalClass.isAssignableFrom(value.getClass())) {
            return null;
        }
        return (TRetval) value;
    }

    public int getNumberOfResults() {
        return this.resultList.size();
    }
}
