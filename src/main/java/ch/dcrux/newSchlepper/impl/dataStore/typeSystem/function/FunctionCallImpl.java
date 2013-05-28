package ch.dcrux.newSchlepper.impl.dataStore.typeSystem.function;

import ch.dcrux.newSchlepper.commandProcessor.*;
import ch.dcrux.newSchlepper.dataStore.DataId;
import ch.dcrux.newSchlepper.dataStore.typeSystem.function.FunctionCall;
import ch.dcrux.newSchlepper.dataStore.typeSystem.function.FunctionMetadata;
import ch.dcrux.newSchlepper.impl.dataStore.Store;
import ch.dcrux.newSchlepper.impl.dataStore.typeSystem.base.BaseUtil;

/**
 * Buran.
 *
 * @author: ${USER} Date: 22.05.13 Time: 23:28
 */
public class FunctionCallImpl implements IModifyCommandImpl<Store, DataId, FunctionCall> {
    @Override
    public Class<FunctionCall> getSupportedClass() {
        return FunctionCall.class;
    }

    @Override
    public DataId run(ISourceRef sender, Store processor, CommandId thisId, CommandList commandList,
            FunctionCall command, ResultList currentResultList,
            IRollbackStoragePut rollbackStoragePut) throws CommandException {
        final FunctionMetadata meta =
                BaseUtil.getMetadataOrNull(processor, currentResultList, command.getDataTarget(),
                        FunctionMetadata.class);
        if (meta.isCallOnce()) {
            //TODO: Hier den function-call entfernen.
        }
        return null;
    }

    @Override
    public void rollback(ISourceRef sender, Store processor, CommandId thisId,
            CommandList commandList, FunctionCall command, IRollbackStorageGet rollbackStorageGet) {
        //TODO: Hier den function-call wieder hinzufügen, falls entfernt wurde.
    }
}
