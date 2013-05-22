package ch.dcrux.newSchlepper.commandProcessor.interception;

import ch.dcrux.newSchlepper.commandProcessor.IModifyCommand;

/**
 * Buran.
 *
 * @author: ${USER} Date: 22.05.13 Time: 09:12
 */
public interface IAbortInterception<TModifyCommand extends IModifyCommand<?>>
        extends IInterception<TModifyCommand> {
}
