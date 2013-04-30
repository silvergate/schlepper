package ch.xcrux.schlepper.globals;

import ch.xcrux.schlepper.DataId;
import ch.xcrux.schlepper.IDataIdProvider;
import ch.xcrux.schlepper.meta.MetadataId;

/**
 * Buran.
 *
 * @author: ${USER} Date: 27.04.13 Time: 13:01
 */
public class DataIdAndMetadataId implements IDataIdProvider {
    private final MetadataId metadataId;
    private final DataId dataId;

    public DataIdAndMetadataId(DataId dataId, MetadataId metadataId) {
        this.metadataId = metadataId;
        this.dataId = dataId;
    }

    public MetadataId getMetadataId() {
        return metadataId;
    }

    public DataId getDataId() {
        return dataId;
    }
}
