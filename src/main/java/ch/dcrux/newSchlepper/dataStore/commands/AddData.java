package ch.dcrux.newSchlepper.dataStore.commands;

import ch.dcrux.newSchlepper.commandProcessor.CommandException;
import ch.dcrux.newSchlepper.commandProcessor.IModifyCommand;
import ch.dcrux.newSchlepper.dataStore.DataOrUid;
import ch.dcrux.newSchlepper.dataStore.Uid;
import ch.dcrux.newSchlepper.dataStore.typeSystem.IMetadata;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.Set;

/**
 * Buran.
 *
 * @author: ${USER} Date: 22.05.13 Time: 23:23
 */
public class AddData implements IModifyCommand<DataOrUid> {
    @Nullable
    private final Uid uid;
    private final IMetadata metadata;

    public AddData(Uid uid, IMetadata metadata) {
        this.uid = uid;
        this.metadata = metadata;
    }

    @Nullable
    public Uid getUid() {
        return uid;
    }

    public IMetadata getMetadata() {
        return metadata;
    }

    @Override
    public Set<Class<? extends CommandException>> getPossibleExceptions() {
        return Collections.emptySet();
    }
}
