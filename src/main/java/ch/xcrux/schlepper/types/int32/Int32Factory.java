package ch.xcrux.schlepper.types.int32;

import ch.xcrux.schlepper.*;
import com.sun.istack.internal.Nullable;

/**
 * Buran.
 *
 * @author: ${USER} Date: 27.04.13 Time: 15:56
 */
public class Int32Factory implements IFacadeFactory<Int32, Integer, Int32Meta> {

    public static final TypeId TYPE_ID = new TypeId((byte)3);


    @Override
    public Int32 create(IStore store, DataId dataId) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isValid(@Nullable Int32Meta metadata, @Nullable Integer currentData,
            @Nullable IChange change) {
        return true;
    }

    @Override
    public TypeId getTypeId() {
        return TYPE_ID;
    }

    @Override
    public Integer createDataInstance() {
        return null;
    }

    @Override
    public <TGetRequest extends Object> TGetRequest processGetRequest(
            IGetRequest<TGetRequest> request, @Nullable Object currentData) {
        if (request instanceof Int32Get) {
            return (TGetRequest)currentData;
        }
        throw new IllegalArgumentException("");
    }


}
