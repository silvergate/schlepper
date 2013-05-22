package ch.dcrux.newSchlepper.commandProcessor.interception;

import ch.dcrux.newSchlepper.commandProcessor.IModifyCommand;
import ch.dcrux.newSchlepper.commandProcessor.ISourceRef;

/**
 * Buran.
 *
 * @author: ${USER} Date: 22.05.13 Time: 17:45
 */
public class InterceptionTriple {
    private final IInterception<? extends IModifyCommand<?>> interception;
    private final ISourceRef interceptorRef;
    private final InterceptionId interceptionId;

    public InterceptionTriple(IInterception<? extends IModifyCommand<?>> interception,
            ISourceRef interceptorRef, InterceptionId interceptionId) {
        this.interception = interception;
        this.interceptorRef = interceptorRef;
        this.interceptionId = interceptionId;
    }

    public IInterception<? extends IModifyCommand<?>> getInterception() {
        return interception;
    }

    public ISourceRef getInterceptorRef() {
        return interceptorRef;
    }

    public InterceptionId getInterceptionId() {
        return interceptionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InterceptionTriple that = (InterceptionTriple) o;

        if (!interception.equals(that.interception)) return false;
        if (!interceptionId.equals(that.interceptionId)) return false;
        if (!interceptorRef.equals(that.interceptorRef)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = interception.hashCode();
        result = 31 * result + interceptorRef.hashCode();
        result = 31 * result + interceptionId.hashCode();
        return result;
    }
}
