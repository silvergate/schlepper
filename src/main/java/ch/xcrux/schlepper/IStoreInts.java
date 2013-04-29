package ch.xcrux.schlepper;

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

    @Nullable
    Object createData(IMetadata metadata);
}
