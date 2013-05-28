package ch.dcrux.newSchlepper.dataStore.typeSystem.function;

import ch.dcrux.newSchlepper.dataStore.typeSystem.AltConstraints;
import ch.xcrux.schlepper.meta.IConstraint;

/**
 * Buran.
 *
 * @author: ${USER} Date: 28.05.13 Time: 17:47
 */
public class FunctionConstraintP implements IConstraint {
    private final AltConstraints input;
    private final AltConstraints output;

    public FunctionConstraintP(AltConstraints input, AltConstraints output) {
        this.input = input;
        this.output = output;
    }

    public AltConstraints getInput() {
        return input;
    }

    public AltConstraints getOutput() {
        return output;
    }
}
