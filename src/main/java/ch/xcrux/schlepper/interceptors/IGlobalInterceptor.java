package ch.xcrux.schlepper.interceptors;

import ch.xcrux.schlepper.changes.IStoreChange;
import com.sun.istack.internal.Nullable;

/**
 * Buran.
 *
 * @author: ${USER} Date: 30.04.13 Time: 00:11
 */
public interface IGlobalInterceptor<TRetVal extends Object, TStoreChangeRet extends Object,
        TStoreChange extends IStoreChange<TStoreChangeRet>>
        extends IInterceptor {

    Class<TStoreChange> getSupportedChangeClass();

    @Nullable
    InterceptionResult<TRetVal> matches(TStoreChange change, @Nullable TStoreChangeRet changeRet);
}
