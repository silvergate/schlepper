package ch.dcrux.newSchlepper.commandProcessor.interception;

import ch.dcrux.newSchlepper.commandProcessor.CommandException;

import java.util.Collection;

/**
 * Buran.
 *
 * @author: ${USER} Date: 22.05.13 Time: 10:01
 */

public class AbortException extends CommandException {
    public AbortException(Collection<TargetedAbort> aborts) {
        this.aborts = aborts;
    }

    private final Collection<TargetedAbort> aborts;

    public Collection<TargetedAbort> getAborts() {
        return aborts;
    }
}
