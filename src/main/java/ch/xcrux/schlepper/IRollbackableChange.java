package ch.xcrux.schlepper;

import com.sun.istack.internal.Nullable;

/**
 * Buran. Change, welcher gerollbackt werden kann.
 *
 * @author: ${USER} Date: 27.04.13 Time: 17:39
 */
public interface IRollbackableChange<TData extends Object, TMetadata extends IMetadata,
        TRetData extends Object> {
    ChangeInfo<TData, TRetData> perform(DataId dataId, TMetadata metadata,
            @Nullable TData currentData, IStoreInts storeInts);


    //boolean isNoop();
    //void rollback(DataId dataId);
}
