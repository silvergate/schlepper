package ch.xcrux.schlepper.types.composite;

import ch.xcrux.schlepper.TypeId;
import ch.xcrux.schlepper.meta.IMetadata;

/**
 * Buran.
 *
 * @author: ${USER} Date: 26.04.13 Time: 23:44
 */
public class CompositeMeta implements IMetadata {
    @Override
    public TypeId getTypeId() {
        return CompositeType.TYPE_ID;
    }
}
