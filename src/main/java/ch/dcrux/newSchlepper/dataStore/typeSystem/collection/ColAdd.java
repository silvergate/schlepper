package ch.dcrux.newSchlepper.dataStore.typeSystem.collection;

import ch.dcrux.newSchlepper.commandProcessor.CommandException;
import ch.dcrux.newSchlepper.commandProcessor.IModifyCommand;
import ch.dcrux.newSchlepper.dataStore.DataId;
import ch.dcrux.newSchlepper.dataStore.typeSystem.IMetadata;

import java.util.Collections;
import java.util.Set;

/**
 * Buran.
 *
 * @author: ${USER} Date: 26.05.13 Time: 10:25
 */
public class ColAdd implements IModifyCommand<DataId> {

    private final IAddType addType;
    private final IMetadata metadata;

    public ColAdd(IMetadata metadata, IAddType addType) {
        this.addType = addType;
        this.metadata = metadata;
    }

    public IAddType getAddType() {
        return addType;
    }

    public IMetadata getMetadata() {
        return metadata;
    }

    @Override
    public Set<Class<? extends CommandException>> getPossibleExceptions() {
        return Collections.emptySet();
    }
}
