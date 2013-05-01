package ch.xcrux.schlepper.globals;

import ch.xcrux.schlepper.DataId;
import ch.xcrux.schlepper.Uid;
import ch.xcrux.schlepper.interceptors.IGlobalInterceptor;
import ch.xcrux.schlepper.interceptors.InterceptionResult;
import com.sun.istack.internal.Nullable;

/**
 * Buran.
 *
 * @author: ${USER} Date: 30.04.13 Time: 00:22
 */
public class AddDataInterceptor
        implements IGlobalInterceptor<DataId, DataIdAndMetadataId, AddData> {

    @Nullable
    private final Uid uid;

    @Override
    public Class<AddData> getSupportedChangeClass() {
        return AddData.class;
    }

    public AddDataInterceptor(Uid uid) {
        this.uid = uid;
    }

    public AddDataInterceptor() {
        this(null);
    }

    public Uid getUid() {
        return uid;
    }

    @Override
    public InterceptionResult<DataId> matches(AddData change,
            DataIdAndMetadataId dataIdAndMetadataId) {
        return new InterceptionResult<DataId>(dataIdAndMetadataId.getDataId());
    }
}
