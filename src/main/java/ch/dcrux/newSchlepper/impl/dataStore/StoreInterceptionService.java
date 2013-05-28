package ch.dcrux.newSchlepper.impl.dataStore;

import ch.dcrux.newSchlepper.commandProcessor.ISourceRef;
import ch.dcrux.newSchlepper.commandProcessor.ResultList;
import ch.dcrux.newSchlepper.commandProcessor.WrappedCommandException;
import ch.dcrux.newSchlepper.commandProcessor.interception.IInterceptionContext;
import ch.dcrux.newSchlepper.commandProcessor.interception.InterceptionId;
import ch.dcrux.newSchlepper.impl.commandProcessor.InterceptionService;

/**
 * Buran.
 *
 * @author: ${USER} Date: 22.05.13 Time: 21:35
 */
public class StoreInterceptionService extends InterceptionService {
    @Override
    public IInterceptionContext getContext(ISourceRef interceptorRef) {
        return new IInterceptionContext() {
            @Override
            public void onResultSuccess(ISourceRef interceptor, InterceptionId interceptionId,
                    ResultList resultList) {
                System.out.println("Interception Success for ID: " + interceptionId.getId() + ", " +
                        "Source: " + interceptor);
                for (final Object result : resultList) {
                    System.out.println("  - Value: " + result);
                }
            }

            @Override
            public void onResultFailure(ISourceRef interceptor, InterceptionId interceptionId,
                    WrappedCommandException wrappedCommandException) {
                System.out.println("Interception Failed");
            }
        };
    }
}
