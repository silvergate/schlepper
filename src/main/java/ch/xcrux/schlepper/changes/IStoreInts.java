package ch.xcrux.schlepper.changes;

import ch.xcrux.schlepper.DataId;
import ch.xcrux.schlepper.Uid;
import ch.xcrux.schlepper.meta.IMetadata;
import ch.xcrux.schlepper.meta.MetadataId;
import com.sun.istack.internal.Nullable;

/**
 * Buran.
 *
 * @author: ${USER} Date: 28.04.13 Time: 21:38
 */
public interface IStoreInts {
    DataId createDataId(MetadataId metadataId);

    MetadataId createMetadataId(IMetadata metadata);

    @Nullable
    Object storeValue(DataId dataId, @Nullable Object value);

    @Nullable
    Object removeValue(DataId dataId);

    void connectToUid(DataId dataId, Uid uid);

    @Nullable
    Object createData(IMetadata metadata);
}
