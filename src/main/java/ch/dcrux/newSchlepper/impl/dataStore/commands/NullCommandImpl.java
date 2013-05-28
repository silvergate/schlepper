package ch.dcrux.newSchlepper.impl.dataStore.commands;

import ch.dcrux.newSchlepper.commandProcessor.*;
import ch.dcrux.newSchlepper.dataStore.NullValue;
import ch.dcrux.newSchlepper.dataStore.commands.NullCommand;
import ch.dcrux.newSchlepper.impl.dataStore.Store;

/**
 * Buran.
 *
 * @author: ${USER} Date: 24.05.13 Time: 00:21
 */
public class NullCommandImpl implements IReadOnlyCommandImpl<Store, NullValue, NullCommand> {
    @Override
    public Class<NullCommand> getSupportedClass() {
        return NullCommand.class;
    }

    @Override
    public NullValue run(ISourceRef sender, Store processor, CommandId thisId,
            CommandList commandList, NullCommand command, ResultList currentResultList)
            throws CommandException {
        return NullValue.SINGLETON;
    }

}
