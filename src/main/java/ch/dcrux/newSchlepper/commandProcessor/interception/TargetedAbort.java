package ch.dcrux.newSchlepper.commandProcessor.interception;

import ch.dcrux.newSchlepper.commandProcessor.CommandId;

import java.util.Set;

/**
 * Buran.
 *
 * @author: ${USER} Date: 22.05.13 Time: 09:23
 */
public class TargetedAbort {

    public TargetedAbort(Set<CommandId> source, IAbort abort) {
        this.source = source;
        this.abort = abort;
    }

    private final Set<CommandId> source;
    private final IAbort abort;

    public Set<CommandId> getSource() {
        return source;
    }

    public IAbort getAbort() {
        return abort;
    }
}
