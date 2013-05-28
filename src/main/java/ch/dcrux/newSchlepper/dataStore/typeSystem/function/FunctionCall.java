package ch.dcrux.newSchlepper.dataStore.typeSystem.function;

import ch.dcrux.newSchlepper.commandProcessor.CommandException;
import ch.dcrux.newSchlepper.commandProcessor.IModifyCommand;
import ch.dcrux.newSchlepper.dataStore.DataId;
import ch.dcrux.newSchlepper.dataStore.DataTarget;

import java.util.Collections;
import java.util.Set;

/**
 * Buran.
 *
 * @author: ${USER} Date: 28.05.13 Time: 17:50
 */
public class FunctionCall implements IModifyCommand<DataId> {

    public FunctionCall(DataTarget dataTarget) {
        this.dataTarget = dataTarget;
    }

    public DataTarget getDataTarget() {
        return dataTarget;
    }

    private final DataTarget dataTarget;

    @Override
    public Set<Class<? extends CommandException>> getPossibleExceptions() {
        return Collections.emptySet();
    }
    //TODO: Noch einen function call machen, wo der server-tree nicht modifiziert werden muss.
}
