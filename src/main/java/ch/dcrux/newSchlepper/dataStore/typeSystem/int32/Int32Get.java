package ch.dcrux.newSchlepper.dataStore.typeSystem.int32;

import ch.dcrux.newSchlepper.commandProcessor.CommandException;
import ch.dcrux.newSchlepper.commandProcessor.IReadOnlyCommand;
import ch.dcrux.newSchlepper.dataStore.DataTarget;

import java.util.Collections;
import java.util.Set;

/**
 * Buran.
 *
 * @author: ${USER} Date: 22.05.13 Time: 19:39
 */
public class Int32Get implements IReadOnlyCommand<Integer> {

    public Int32Get(DataTarget dataTarget) {
        this.dataTarget = dataTarget;
    }

    private final DataTarget dataTarget;

    public DataTarget getDataTarget() {
        return dataTarget;
    }

    @Override
    public Set<Class<? extends CommandException>> getPossibleExceptions() {
        return Collections.emptySet();
    }
}
