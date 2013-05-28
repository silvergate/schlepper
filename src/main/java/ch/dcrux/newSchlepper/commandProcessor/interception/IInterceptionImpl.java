package ch.dcrux.newSchlepper.commandProcessor.interception;

import ch.dcrux.newSchlepper.commandProcessor.IModifyCommand;
import ch.dcrux.newSchlepper.impl.commandProcessor.Processor;

/**
 * Buran.
 *
 * @author: ${USER} Date: 24.05.13 Time: 00:00
 */
public interface IInterceptionImpl<TModifyCommand extends IModifyCommand<?>,
        TProcessor extends Processor, TInterception extends IInterception> {
    Class<TInterception> getSupportingInterface();

    InterceptionIndexTypeRef<TModifyCommand> getIndexType();

    IInterceptionCmpIndex<TModifyCommand> createIndexInstance();
}
