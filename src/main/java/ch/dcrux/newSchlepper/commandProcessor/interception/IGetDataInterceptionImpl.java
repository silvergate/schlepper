package ch.dcrux.newSchlepper.commandProcessor.interception;

import ch.dcrux.newSchlepper.commandProcessor.CommandId;
import ch.dcrux.newSchlepper.commandProcessor.CommandList;
import ch.dcrux.newSchlepper.commandProcessor.IModifyCommand;
import ch.dcrux.newSchlepper.commandProcessor.ISourceRef;
import ch.dcrux.newSchlepper.commandProcessor.impl.Processor;

import java.util.Set;

/**
 * Buran.
 *
 * @author: ${USER} Date: 22.05.13 Time: 09:33
 */
public interface IGetDataInterceptionImpl<TModifyCommand extends IModifyCommand<?>,
        TProcessor extends Processor, TGetDataInterception extends
        IGetDataInterception<TModifyCommand>>
        extends IInterceptionImpl<TModifyCommand, TProcessor, TGetDataInterception> {

    /* Nullable */
    CommandList createCommandList(TProcessor processor, TGetDataInterception interception,
            ISourceRef modifyCommandListSource, CommandList modifyCommandList,
            Set<CommandId> matchedCommands, ISourceRef interceptor);
}
