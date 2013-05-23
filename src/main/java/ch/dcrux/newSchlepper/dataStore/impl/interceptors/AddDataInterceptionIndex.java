package ch.dcrux.newSchlepper.dataStore.impl.interceptors;

import ch.dcrux.newSchlepper.commandProcessor.ISourceRef;
import ch.dcrux.newSchlepper.commandProcessor.interception.*;
import ch.dcrux.newSchlepper.dataStore.commands.AddData;

import java.util.*;

/**
 * Buran.
 *
 * @author: ${USER} Date: 24.05.13 Time: 00:23
 */
public class AddDataInterceptionIndex implements IInterceptionCmpIndex<AddData> {

    public static final InterceptionIndexTypeRef<AddData> TYPE =
            new InterceptionIndexTypeRef<AddData>("AddDataInterceptionIndex");

    private final List<InterceptionTriple> interceptions = new ArrayList<InterceptionTriple>();

    @Override
    public Class<AddData> getSupportedClass() {
        return AddData.class;
    }

    @Override
    public InterceptionIndexTypeRef<AddData> getIndexType() {
        return TYPE;
    }

    @Override
    public void add(IInterception<AddData> interception, ISourceRef interceptorRef,
            InterceptionId interceptionId) {
        final InterceptionTriple triple =
                new InterceptionTriple(interception, interceptorRef, interceptionId);
        this.interceptions.add(triple);
    }

    @Override
    public boolean remove(ISourceRef interceptorRef, InterceptionId interceptionId) {
        final Set<InterceptionTriple> toRemove = new HashSet<InterceptionTriple>();
        for (final InterceptionTriple triple : this.interceptions) {
            if (triple.getInterceptionId().equals(interceptionId) &&
                    triple.getInterceptorRef().equals(interceptorRef)) {
                toRemove.add(triple);
            }

        }
        this.interceptions.removeAll(toRemove);
        return !toRemove.isEmpty();
    }

    @Override
    public int getNumOfInterceptions() {
        return this.interceptions.size();
    }

    @Override
    public Set<InterceptionTriple> getMatchingInterceptions(AddData modifyCommand,
            boolean includeGetDataInterceptions, boolean includeAbortExceptions) {
        if (!includeGetDataInterceptions) {
            return Collections.emptySet();
        }

        final Set<InterceptionTriple> results = new HashSet<InterceptionTriple>();
        for (final InterceptionTriple triple : this.interceptions) {
            results.add(triple);
        }

        return results;
    }
}
