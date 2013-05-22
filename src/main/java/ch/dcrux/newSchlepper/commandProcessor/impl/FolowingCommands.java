package ch.dcrux.newSchlepper.commandProcessor.impl;

import ch.dcrux.newSchlepper.commandProcessor.CommandList;
import ch.dcrux.newSchlepper.commandProcessor.ISourceRef;
import ch.dcrux.newSchlepper.commandProcessor.interception.InterceptionId;

/**
 * Buran.
 *
 * @author: ${USER} Date: 22.05.13 Time: 09:51
 */
public class FolowingCommands {
    private final ISourceRef interceptor;
    private final InterceptionId interceptionId;
    private final CommandList commandList;

    public FolowingCommands(ISourceRef interceptor, InterceptionId interceptionId,
            CommandList commandList) {
        this.interceptor = interceptor;
        this.interceptionId = interceptionId;
        this.commandList = commandList;
    }

    public ISourceRef getInterceptor() {
        return interceptor;
    }

    public InterceptionId getInterceptionId() {
        return interceptionId;
    }

    public CommandList getCommandList() {
        return commandList;
    }
}
