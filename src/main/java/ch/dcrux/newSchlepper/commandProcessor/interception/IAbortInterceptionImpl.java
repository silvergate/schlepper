package ch.dcrux.newSchlepper.commandProcessor.interception;

import ch.dcrux.newSchlepper.commandProcessor.CommandId;
import ch.dcrux.newSchlepper.commandProcessor.CommandList;
import ch.dcrux.newSchlepper.commandProcessor.IModifyCommand;
import ch.dcrux.newSchlepper.commandProcessor.ISourceRef;
import ch.dcrux.newSchlepper.impl.commandProcessor.Processor;

import java.util.Set;

/**
 * Buran.
 *
 * @author: ${USER} Date: 22.05.13 Time: 09:33
 */
public interface IAbortInterceptionImpl<TModifyCommand extends IModifyCommand<?>,
        TProcessor extends Processor, TAbortInterception extends IAbortInterception<TModifyCommand>>
        extends IInterceptionImpl<TModifyCommand, TProcessor, TAbortInterception> {

    Set<TargetedAbort> createAbortList(TProcessor processor, TAbortInterception interception,
            ISourceRef modifyCommandListSource, CommandList modifyCommandList,
            Set<CommandId> matchedCommand, ISourceRef interceptor);

    void onCommit(TProcessor processor, TAbortInterception interception,
            ISourceRef modifyCommandListSource, CommandList modifyCommandList,
            Set<CommandId> matchedCommand, ISourceRef interceptor);
}
