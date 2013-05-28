package ch.dcrux.newSchlepper.impl.dataStore.typeSystem.function;

import ch.dcrux.newSchlepper.commandProcessor.ISourceRef;
import ch.dcrux.newSchlepper.commandProcessor.interception.*;
import ch.dcrux.newSchlepper.dataStore.DataId;
import ch.dcrux.newSchlepper.dataStore.typeSystem.function.FunctionCall;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Buran.
 *
 * @author: ${USER} Date: 24.05.13 Time: 00:23
 */
public class FunctionCallInterceptionIndex implements IInterceptionCmpIndex<FunctionCall> {

    public static final InterceptionIndexTypeRef<FunctionCall> TYPE =
            new InterceptionIndexTypeRef<FunctionCall>("functionCall");

    private final Map<DataId, FunctionCall> interceptions = new HashMap<>();

    @Override
    public Class<FunctionCall> getSupportedClass() {
        return FunctionCall.class;
    }

    @Override
    public InterceptionIndexTypeRef<FunctionCall> getIndexType() {
        return TYPE;
    }

    @Override
    public void add(IInterception<FunctionCall> interception, ISourceRef interceptorRef,
            InterceptionId interceptionId) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean remove(ISourceRef interceptorRef, InterceptionId interceptionId) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getNumOfInterceptions() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Set<InterceptionTriple> getMatchingInterceptions(FunctionCall modifyCommand,
            boolean includeGetDataInterceptions, boolean includeAbortExceptions) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


}
