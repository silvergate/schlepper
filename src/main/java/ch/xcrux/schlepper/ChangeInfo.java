package ch.xcrux.schlepper;

/**
 * Buran.
 *
 * @author: ${USER} Date: 28.04.13 Time: 15:02
 */
public class ChangeInfo<TData extends Object, TRetValue extends Object> {
    private final boolean noop;
    private final boolean useNewValue;
    private final Object newValue;
    private final boolean didModify;
    private final boolean hasRetValue;
    private final TRetValue retValue;

    protected ChangeInfo(boolean noop, boolean useNewValue, Object newValue, boolean didModify,
            boolean hasRetValue, TRetValue retValue) {
        this.noop = noop;
        this.useNewValue = useNewValue;
        this.newValue = newValue;
        this.didModify = didModify;
        this.hasRetValue = hasRetValue;
        this.retValue = retValue;
    }

    public static <TData extends Object, TRetValue extends Object> ChangeInfo<TData,
            TRetValue> readOnlyAndReturn(
            TRetValue retValue) {
        return new ChangeInfo<TData, TRetValue>(false, false, null, false, true, retValue);
    }

    public static <TData extends Object, TRetValue extends Object> ChangeInfo<TData,
            TRetValue> modifyData(
            TData newValue) {
        return new ChangeInfo<TData, TRetValue>(false, true, newValue, false, false, null);
    }

    public static <TData extends Object, TRetValue extends Object> ChangeInfo<TData,
            TRetValue> modifyDataInner() {
        return new ChangeInfo<TData, TRetValue>(false, false, null, true, false, null);
    }

    public static <TData extends Object, TRetValue extends Object> ChangeInfo<TData,
            TRetValue> noop() {
        return new ChangeInfo<TData, TRetValue>(true, false, null, false, false, null);
    }

    public boolean isDidModify() {
        return didModify;
    }

    public boolean isNoop() {
        return noop;
    }

    public boolean isUseNewValue() {
        return useNewValue;
    }

    public Object getNewValue() {
        return newValue;
    }

    public boolean isHasRetValue() {
        return hasRetValue;
    }

    public TRetValue getRetValue() {
        return retValue;
    }
}
