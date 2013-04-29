package ch.xcrux.schlepper;

import ch.xcrux.schlepper.data.IData;
import com.sun.istack.internal.Nullable;

/**
 * Buran.
 *
 * @author: ${USER} Date: 26.04.13 Time: 21:33
 */
public interface IFacadeFactory<T extends IFacade, TData extends Object,
        TMetadata extends IMetadata> {
    T create(IStore store, DataId dataId);

    boolean isValid(@Nullable TMetadata metadata, @Nullable TData currentData, @Nullable IChange change);

    TypeId getTypeId();

    @Nullable
    TData createDataInstance();

    <TGetRequest extends Object> TGetRequest processGetRequest(IGetRequest<TGetRequest>
            request, @Nullable Object
            currentData);
}
