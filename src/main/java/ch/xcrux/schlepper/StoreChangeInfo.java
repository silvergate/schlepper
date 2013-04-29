package ch.xcrux.schlepper;

/**
 * Buran.
 *
 * @author: ${USER} Date: 28.04.13 Time: 21:23
 */
public class StoreChangeInfo<TRetVal extends Object> {
    private final boolean hasRetValue;
    private final TRetVal retVal;
    private final boolean didModify;

    protected StoreChangeInfo(boolean hasRetValue, TRetVal retVal, boolean didModify) {
        this.hasRetValue = hasRetValue;
        this.retVal = retVal;
        this.didModify = didModify;
    }

    public static <TRetVal extends Object> StoreChangeInfo<TRetVal> noop() {
        return new StoreChangeInfo<TRetVal>(false, null, false);
    }

    public static <TRetVal extends Object> StoreChangeInfo<TRetVal> modificationNoRet() {
        return new StoreChangeInfo<TRetVal>(false, null, true);
    }

    public static <TRetVal extends Object> StoreChangeInfo<TRetVal> modificationRet(TRetVal
            retval) {
        return new StoreChangeInfo<TRetVal>(true, retval, true);
    }

    public static <TRetVal extends Object> StoreChangeInfo<TRetVal> retReadOnly(TRetVal
            retval) {
        return new StoreChangeInfo<TRetVal>(true, retval, false);
    }

    public boolean isHasRetValue() {
        return hasRetValue;
    }

    public TRetVal getRetVal() {
        return retVal;
    }

    public boolean isDidModify() {
        return didModify;
    }
}
