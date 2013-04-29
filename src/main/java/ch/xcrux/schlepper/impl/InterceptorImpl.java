package ch.xcrux.schlepper.impl;

import ch.xcrux.schlepper.interceptors.IInterceptor;
import ch.xcrux.schlepper.interceptors.IThreadSwitcher;
import ch.xcrux.schlepper.interceptors.InterceptorId;

/**
 * Buran.
 *
 * @author: ${USER} Date: 30.04.13 Time: 00:38
 */
public class InterceptorImpl {
    private final InterceptorId interceptionId;
    private final IThreadSwitcher switcher;
    private final IInterceptor interceptor;

    public InterceptorImpl(InterceptorId interceptionId, IThreadSwitcher switcher,
            IInterceptor interceptor) {
        this.interceptionId = interceptionId;
        this.switcher = switcher;
        this.interceptor = interceptor;
    }

    public IThreadSwitcher getSwitcher() {
        return switcher;
    }

    public IInterceptor getInterceptor() {
        return interceptor;
    }

    public InterceptorId getInterceptionId() {
        return interceptionId;
    }
}
