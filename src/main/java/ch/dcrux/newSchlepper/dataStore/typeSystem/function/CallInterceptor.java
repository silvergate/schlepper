package ch.dcrux.newSchlepper.dataStore.typeSystem.function;

import ch.dcrux.newSchlepper.commandProcessor.interception.IAbortInterception;
import ch.dcrux.newSchlepper.dataStore.DataOrUid;

/**
 * Buran.
 *
 * @author: ${USER} Date: 23.05.13 Time: 22:34
 */
public class CallInterceptor implements IAbortInterception<FunctionCall> {
    private final DataOrUid dataOrUid;

    public CallInterceptor(DataOrUid dataOrUid) {
        this.dataOrUid = dataOrUid;
    }

    public DataOrUid getDataOrUid() {
        return dataOrUid;
    }
}
