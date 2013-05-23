package ch.dcrux.newSchlepper.dataStore.commands;

import ch.dcrux.newSchlepper.commandProcessor.CommandException;
import ch.dcrux.newSchlepper.commandProcessor.IReadOnlyCommand;
import ch.dcrux.newSchlepper.dataStore.NullValue;

import java.util.Collections;
import java.util.Set;

/**
 * Buran.
 *
 * @author: ${USER} Date: 24.05.13 Time: 00:18
 */
public class NullCommand implements IReadOnlyCommand<NullValue> {
    @Override
    public Set<Class<? extends CommandException>> getPossibleExceptions() {
        return Collections.emptySet();
    }
}
