package ch.dcrux.newSchlepper.commandProcessor.interception;

import ch.dcrux.newSchlepper.commandProcessor.IModifyCommand;
import ch.dcrux.newSchlepper.commandProcessor.ISourceRef;

import java.util.Set;

/**
 * Buran.
 *
 * @author: ${USER} Date: 22.05.13 Time: 00:50
 */
public interface IInterceptionCmpIndex<TModifyCommand extends IModifyCommand<?>> {
    Class<TModifyCommand> getSupportedClass();

    InterceptionIndexTypeRef<TModifyCommand> getIndexType();

    /* Add and remove */
    void add(IInterception<TModifyCommand> interception, ISourceRef interceptorRef,
            InterceptionId interceptionId);

    boolean remove(ISourceRef interceptorRef, InterceptionId interceptionId);

    int getNumOfInterceptions();

    Set<InterceptionTriple> getMatchingInterceptions(TModifyCommand modifyCommand,
            boolean includeGetDataInterceptions, boolean includeAbortExceptions);
}
