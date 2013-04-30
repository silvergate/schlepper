package ch.xcrux.schlepper.globals;

import ch.xcrux.schlepper.*;
import ch.xcrux.schlepper.changes.IRollbackableStoreChange;
import ch.xcrux.schlepper.changes.IStoreChange;
import ch.xcrux.schlepper.changes.IStoreInts;
import ch.xcrux.schlepper.meta.IMetadata;
import ch.xcrux.schlepper.meta.MetadataId;
import com.google.common.base.Optional;

/**
 * Buran.
 *
 * @author: ${USER} Date: 27.04.13 Time: 13:48
 */
public class AddData implements IStoreChange<DataIdAndMetadataId> {
    private final IMetadata metadata;
    private final Optional<Uid> uid;

    public AddData(IMetadata metadata) {
        this(Optional.<Uid>absent(), metadata);
    }

    public AddData( Optional<Uid> uid, IMetadata metadata) {
        this.metadata = metadata;
        this.uid = uid;
    }

    public IMetadata getMetadata() {
        return metadata;
    }

    public Optional<Uid> getUid() {
        return uid;
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
                if (uid.isPresent()) {
                    functions.connectToUid(dataId, uid.get());
                }

                return StoreChangeInfo.modificationRet(new DataIdAndMetadataId(dataId, mid));
            }
        };
    }
}
