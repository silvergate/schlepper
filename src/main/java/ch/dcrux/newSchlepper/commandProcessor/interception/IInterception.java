package ch.dcrux.newSchlepper.commandProcessor.interception;

import ch.dcrux.newSchlepper.commandProcessor.IModifyCommand;

/**
 * Buran.
 *
 * @author: ${USER} Date: 22.05.13 Time: 00:41
 */
public interface IInterception<TModifyCommand extends IModifyCommand<?>> {
    InterceptionIndexTypeRef<TModifyCommand> getIndexType();

    IInterceptionCmpIndex<TModifyCommand> createIndexInstance();
}
