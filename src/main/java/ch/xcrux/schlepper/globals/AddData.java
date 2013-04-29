package ch.xcrux.schlepper.globals;

import ch.xcrux.schlepper.*;

/**
 * Buran.
 *
 * @author: ${USER} Date: 27.04.13 Time: 13:48
 */
public class AddData implements IStoreChange<DataIdAndMetadataId> {
    private final IMetadata metadata;

    public AddData(IMetadata metadata) {
        this.metadata = metadata;
    }

    public IMetadata getMetadata() {
        return metadata;
    }

    @Override
    public IRollbackableStoreChange<DataIdAndMetadataId> createChange() {
        return new IRollbackableStoreChange<DataIdAndMetadataId>() {
            @Override
            public StoreChangeInfo<DataIdAndMetadataId> perform(IStoreInts functions) {
                final MetadataId mid = functions.createMetadataId(metadata);
                final DataId dataId = functions.createDataId(mid);
                final Object data = functions.createData(metadata);
                functions.storeValue(dataId, data);

                return StoreChangeInfo.modificationRet(new DataIdAndMetadataId(dataId, mid));
            }
        };
    }
}
