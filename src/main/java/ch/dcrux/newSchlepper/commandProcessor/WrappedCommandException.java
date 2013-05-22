package ch.dcrux.newSchlepper.commandProcessor;

import java.util.Collections;
import java.util.Set;

/**
 * Buran.
 *
 * @author: ${USER} Date: 21.05.13 Time: 22:25
 */
public class WrappedCommandException extends Exception {
    private final Set<CommandId> commandIds;
    private final CommandException exception;

    public WrappedCommandException(CommandId commandId, CommandException exception) {
        this.commandIds = Collections.singleton(commandId);
        this.exception = exception;
    }

    public WrappedCommandException(Set<CommandId> commandIds, CommandException exception) {
        this.commandIds = commandIds;
        this.exception = exception;
    }

    public Set<CommandId> getCommandIds() {
        return commandIds;
    }

    public CommandException getException() {
        return exception;
    }
}
