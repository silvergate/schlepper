package ch.dcrux.newSchlepper.dataStore.impl.typeSystem.int32;

import ch.dcrux.newSchlepper.commandProcessor.*;
import ch.dcrux.newSchlepper.dataStore.DataOrUid;
import ch.dcrux.newSchlepper.dataStore.NullValue;
import ch.dcrux.newSchlepper.dataStore.impl.Store;
import ch.dcrux.newSchlepper.dataStore.impl.typeSystem.base.BaseUtil;
import ch.dcrux.newSchlepper.dataStore.impl.typeSystem.base.SetterBaseImpl;
import ch.dcrux.newSchlepper.dataStore.typeSystem.Types;
import ch.dcrux.newSchlepper.dataStore.typeSystem.int32.Int32Meta;
import ch.dcrux.newSchlepper.dataStore.typeSystem.int32.Int32Set;

/**
 * Buran.
 *
 * @author: ${USER} Date: 22.05.13 Time: 19:41
 */
public class Int32SetImpl extends SetterBaseImpl<Void, Int32Set> {

    @Override
    public Class<Int32Set> getSupportedClass() {
        return Int32Set.class;
    }

    @Override
    public Void run(ISourceRef sender, Store processor, CommandId thisId, CommandList commandList,
            Int32Set command, ResultList currentResultList, IRollbackStoragePut rollbackStoragePut)
            throws CommandException {
        final DataOrUid dou = getDataOrUid(currentResultList, command.getDataTarget());
        final Object originalValue = processor.getDataStore().getData(dou);
        final boolean hadValue = processor.getDataStore().hasData(dou);

        if (!hadValue) {
            rollbackStoragePut.store(NullValue.SINGLETON);
        } else {
            rollbackStoragePut.store(originalValue);
        }

        /* Check metadata */
        final Int32Meta metadata =
                BaseUtil.getMetadataOrNull(processor, currentResultList, command.getDataTarget(),
                        Int32Meta.class);
        if (metadata == null) {
            /* Metadata is missing */
            throw new CommandException();
        }
        if (metadata.getDataType() != Types.int32) {
            /* Wrong type */
            throw new CommandException();
        }

        /* Set data */
        processor.getDataStore().put(dou, command.getValue());

        return null;
    }

    @Override
    public void rollback(ISourceRef sender, Store processor, CommandId thisId,
            CommandList commandList, Int32Set command, IRollbackStorageGet rollbackStorageGet) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

}
