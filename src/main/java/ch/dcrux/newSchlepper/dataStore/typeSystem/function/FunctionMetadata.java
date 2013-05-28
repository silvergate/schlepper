package ch.dcrux.newSchlepper.dataStore.typeSystem.function;

import ch.dcrux.newSchlepper.dataStore.typeSystem.IMetadata;
import ch.dcrux.newSchlepper.dataStore.typeSystem.ITypeConstraint;
import ch.dcrux.newSchlepper.dataStore.typeSystem.Types;

/**
 * Buran.
 *
 * @author: ${USER} Date: 28.05.13 Time: 17:49
 */
public class FunctionMetadata implements IMetadata {

    private final FunctionConstraint constraint;
    private final boolean callOnce;

    public FunctionMetadata(FunctionConstraint constraint, boolean callOnce) {
        this.constraint = constraint;
        this.callOnce = callOnce;
    }

    public boolean isCallOnce() {
        return callOnce;
    }

    @Override
    public Types getDataType() {
        return this.constraint.getDataType();
    }

    @Override
    public ITypeConstraint getConstraint() {
        return this.constraint.getConstraint();
    }
}
