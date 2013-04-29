package ch.xcrux.schlepper.interceptors;

import ch.xcrux.schlepper.DataId;
import ch.xcrux.schlepper.IChange;
import com.sun.istack.internal.Nullable;

/**
 * Buran.
 *
 * @author: ${USER} Date: 30.04.13 Time: 00:09
 */
public interface IDataInterceptor<TRetData extends Object, TRetDataChange extends Object,
        TChange extends IChange<?, ?, TRetDataChange>>
        extends IInterceptor {

    Class<TChange> getChangeClass();

    @Nullable
    InterceptionResult<TRetData> matches(TChange change, @Nullable TRetDataChange changeRet);

    DataId getDataId();
}
