package ch.dcrux.newSchlepper.dataStore.typeSystem.int32;

import ch.dcrux.newSchlepper.dataStore.typeSystem.IMetadata;
import ch.dcrux.newSchlepper.dataStore.typeSystem.Types;

/**
 * Buran.
 *
 * @author: ${USER} Date: 22.05.13 Time: 23:21
 */
public class Int32Meta implements IMetadata {
    @Override
    public Types getDataType() {
        return Types.int32;
    }
}
