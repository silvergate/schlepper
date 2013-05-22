package ch.dcrux.newSchlepper.dataStore.typeSystem.int32;

import ch.dcrux.newSchlepper.commandProcessor.CommandException;
import ch.dcrux.newSchlepper.commandProcessor.IModifyCommand;
import ch.dcrux.newSchlepper.dataStore.DataTarget;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.Set;

/**
 * Buran.
 *
 * @author: ${USER} Date: 22.05.13 Time: 19:39
 */
public class Int32Set implements IModifyCommand<Void> {

    public Int32Set(DataTarget dataTarget, Integer value) {
        this.dataTarget = dataTarget;
        this.value = value;
    }

    private final DataTarget dataTarget;
    @Nullable
    private final Integer value;

    public DataTarget getDataTarget() {
        return dataTarget;
    }

    @Nullable
    public Integer getValue() {
        return value;
    }

    @Override
    public Set<Class<? extends CommandException>> getPossibleExceptions() {
        return Collections.emptySet();
    }
}
