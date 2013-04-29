package ch.xcrux.schlepper.globals;

import ch.xcrux.schlepper.DataId;
import ch.xcrux.schlepper.interceptors.IGlobalInterceptor;
import ch.xcrux.schlepper.interceptors.InterceptionResult;

/**
 * Buran.
 *
 * @author: ${USER} Date: 30.04.13 Time: 00:22
 */
public class AddDataInterceptor
        implements IGlobalInterceptor<DataId, DataIdAndMetadataId, AddData> {
    @Override
    public Class<AddData> getSupportedChangeClass() {
        return AddData.class;
    }

    @Override
    public InterceptionResult<DataId> matches(AddData change,
            DataIdAndMetadataId dataIdAndMetadataId) {
        return new InterceptionResult<DataId>(dataIdAndMetadataId.getDataId());
    }
}
