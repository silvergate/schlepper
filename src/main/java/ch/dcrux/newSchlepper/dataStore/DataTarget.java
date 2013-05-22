package ch.dcrux.newSchlepper.dataStore;

import ch.dcrux.newSchlepper.commandProcessor.CommandId;

/**
 * Buran.
 *
 * @author: ${USER} Date: 22.05.13 Time: 22:51
 */
public class DataTarget extends Alt2<DataOrUid, CommandId> {
    protected DataTarget(Object value, int index) {
        super(value, index);
    }

    public static DataTarget dataOrOuid(DataOrUid dataOrUid) {
        return new DataTarget(dataOrUid, 0);
    }

    public static DataTarget command(CommandId commandId) {
        return new DataTarget(commandId, 1);
    }
}
