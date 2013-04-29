package ch.xcrux.schlepper.types.composite;

import ch.xcrux.schlepper.IMetadata;
import ch.xcrux.schlepper.TypeId;

/**
 * Buran.
 *
 * @author: ${USER} Date: 26.04.13 Time: 23:44
 */
public class CompositeMeta implements IMetadata {
    @Override
    public TypeId getTypeId() {
        return CompositeFactory.TYPE_ID;
    }
}
