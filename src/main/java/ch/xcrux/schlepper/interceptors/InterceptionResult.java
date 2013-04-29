package ch.xcrux.schlepper.interceptors;

/**
 * Buran.
 *
 * @author: ${USER} Date: 30.04.13 Time: 00:12
 */
public class InterceptionResult<TRetVal extends Object> {
    private final TRetVal retVal;

    public InterceptionResult(TRetVal retVal) {
        this.retVal = retVal;
    }

    public TRetVal getRetVal() {
        return retVal;
    }
}
