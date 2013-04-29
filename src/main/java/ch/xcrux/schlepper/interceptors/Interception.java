package ch.xcrux.schlepper.interceptors;

import com.google.common.base.Optional;

/**
 * Buran.
 *
 * @author: ${USER} Date: 30.04.13 Time: 00:36
 */
public class Interception {
    private final InterceptorId interceptor;
    private final Optional<Object> retValue;

    public Interception(InterceptorId interceptor, Optional<Object> retValue) {
        this.interceptor = interceptor;
        this.retValue = retValue;
    }

    public InterceptorId getInterceptor() {
        return interceptor;
    }

    public Optional<Object> getRetValue() {
        return retValue;
    }
}
