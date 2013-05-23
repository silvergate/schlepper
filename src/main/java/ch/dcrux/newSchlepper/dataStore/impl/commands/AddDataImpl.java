package ch.dcrux.newSchlepper.dataStore.impl.commands;

import ch.dcrux.newSchlepper.commandProcessor.*;
import ch.dcrux.newSchlepper.dataStore.DataOrUid;
import ch.dcrux.newSchlepper.dataStore.commands.AddData;
import ch.dcrux.newSchlepper.dataStore.impl.Store;
import ch.dcrux.newSchlepper.dataStore.typeSystem.IMetadata;

/**
 * Buran.
 *
 * @author: ${USER} Date: 22.05.13 Time: 23:28
 */
public class AddDataImpl implements IModifyCommandImpl<Store, DataOrUid, AddData> {
    @Override
    public Class<AddData> getSupportedClass() {
        return AddData.class;
    }

    @Override
    public DataOrUid run(ISourceRef sender, Store processor, CommandId thisId,
            CommandList commandList, AddData command, ResultList currentResultList,
            IRollbackStoragePut rollbackStoragePut) throws CommandException {
        if (command.getUid() != null) {
            final IMetadata currentMetadata =
                    processor.getMetaStore().getMeta(DataOrUid.data(command.getUid()));
            if (currentMetadata != null) {
                // TODO: Metadata already exists.
                throw new CommandException();
            }
        }

        final DataOrUid dataOrUid;
        if (command.getUid() != null) {
            dataOrUid = DataOrUid.data(command.getUid());
        } else {
            dataOrUid = DataOrUid.data(processor.getMetaStore().createNewDataId());
        }

        /* Set metadata */
        processor.getMetaStore().put(dataOrUid, command.getMetadata());

        return dataOrUid;
    }

    @Override
    public void rollback(ISourceRef sender, Store processor, CommandId thisId,
            CommandList commandList, AddData command, IRollbackStorageGet rollbackStorageGet) {
        //TODO
    }
}
