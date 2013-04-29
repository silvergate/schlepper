package ch.xcrux.schlepper.types.int32;

import ch.xcrux.schlepper.IMetadata;
import ch.xcrux.schlepper.TypeId;

/**
 * Buran.
 *
 * @author: ${USER} Date: 27.04.13 Time: 13:25
 */
public class Int32Meta implements IMetadata {

    @Override
    public TypeId getTypeId() {
        return Int32Factory.TYPE_ID;
    }
}
