package ch.dcrux.newSchlepper.dataStore.typeSystem.function;

import ch.dcrux.newSchlepper.dataStore.typeSystem.IMetaTypeAndConstraint;
import ch.dcrux.newSchlepper.dataStore.typeSystem.ITypeConstraint;
import ch.dcrux.newSchlepper.dataStore.typeSystem.Types;

/**
 * Buran.
 *
 * @author: ${USER} Date: 28.05.13 Time: 17:46
 */
public class FunctionConstraint implements IMetaTypeAndConstraint {
    @Override
    public Types getDataType() {
        return Types.function;
    }

    @Override
    public ITypeConstraint getConstraint() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
