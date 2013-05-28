package ch.dcrux.newSchlepper.impl.dataStore.typeSystem.function;

import ch.dcrux.newSchlepper.commandProcessor.CommandId;
import ch.dcrux.newSchlepper.commandProcessor.CommandList;
import ch.dcrux.newSchlepper.commandProcessor.ISourceRef;
import ch.dcrux.newSchlepper.commandProcessor.interception.IAbortInterceptionImpl;
import ch.dcrux.newSchlepper.commandProcessor.interception.IInterceptionCmpIndex;
import ch.dcrux.newSchlepper.commandProcessor.interception.InterceptionIndexTypeRef;
import ch.dcrux.newSchlepper.commandProcessor.interception.TargetedAbort;
import ch.dcrux.newSchlepper.dataStore.typeSystem.function.CallInterceptor;
import ch.dcrux.newSchlepper.dataStore.typeSystem.function.FunctionCall;
import ch.dcrux.newSchlepper.impl.dataStore.Store;

import java.util.Set;

/**
 * Buran.
 *
 * @author: ${USER} Date: 24.05.13 Time: 00:15
 */
public class FunctionCallInterceptorImpl
        implements IAbortInterceptionImpl<FunctionCall, Store, CallInterceptor> {

    @Override
    public Set<TargetedAbort> createAbortList(Store processor, CallInterceptor interception,
            ISourceRef modifyCommandListSource, CommandList modifyCommandList,
            Set<CommandId> matchedCommand, ISourceRef interceptor) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onCommit(Store processor, CallInterceptor interception,
            ISourceRef modifyCommandListSource, CommandList modifyCommandList,
            Set<CommandId> matchedCommand, ISourceRef interceptor) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Class<CallInterceptor> getSupportingInterface() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public InterceptionIndexTypeRef<FunctionCall> getIndexType() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public IInterceptionCmpIndex<FunctionCall> createIndexInstance() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
