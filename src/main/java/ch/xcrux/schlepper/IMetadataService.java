package ch.xcrux.schlepper;

import com.sun.istack.internal.Nullable;

/**
 * Buran.
 *
 * @author: ${USER} Date: 26.04.13 Time: 23:19
 */
public interface IMetadataService {
    MetadataId setMetadataFor(DataId dataId, IMetadata metadata);

    void setMetadata(DataId dataId, MetadataId metadataId);

    void removeMetadata(MetadataId metadataId);

    @Nullable
    MetadataId getMetadataFor(DataId dataId);

    IMetadata getMetadata(MetadataId metadataId);
}
