package ch.dcrux.newSchlepper.commandProcessor.interception;

import ch.dcrux.newSchlepper.commandProcessor.ISourceRef;
import ch.dcrux.newSchlepper.commandProcessor.ResultList;
import ch.dcrux.newSchlepper.commandProcessor.WrappedCommandException;

/**
 * Buran.
 *
 * @author: ${USER} Date: 22.05.13 Time: 09:43
 */
public interface IInterceptionContext {
    void onResultSuccess(ISourceRef interceptor, InterceptionId interceptionId,
            ResultList resultList);

    void onResultFailure(ISourceRef interceptor, InterceptionId interceptionId,
            WrappedCommandException wrappedCommandException);
}
