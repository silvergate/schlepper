package ch.dcrux.newSchlepper.dataStore.typeSystem.collection;

import ch.dcrux.newSchlepper.dataStore.typeSystem.IMetadata;
import ch.dcrux.newSchlepper.dataStore.typeSystem.ITypeConstraint;
import ch.dcrux.newSchlepper.dataStore.typeSystem.Types;

/**
 * Buran.
 *
 * @author: ${USER} Date: 26.05.13 Time: 20:39
 */
public class ColMetadata implements IMetadata {
    @Override
    public Types getDataType() {
        return Types.collection;
    }

    @Override
    public ITypeConstraint getConstraint() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
