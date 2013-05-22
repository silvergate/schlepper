package ch.dcrux.newSchlepper.commandProcessor;

import ch.dcrux.newSchlepper.commandProcessor.impl.Processor;

/**
 * Buran.
 *
 * @author: ${USER} Date: 21.05.13 Time: 22:30
 */
public interface IReadOnlyCommandImpl<TProcessor extends Processor, TRetval,
        TCommandClass extends IReadOnlyCommand<TRetval>>
        extends ICommandImpl<TProcessor> {
    Class<TCommandClass> getSupportedClass();

    TRetval run(ISourceRef sender, TProcessor processor, CommandId thisId, CommandList commandList,
            TCommandClass command, ResultList currentResultList) throws CommandException;
}
