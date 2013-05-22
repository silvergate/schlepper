package ch.dcrux.newSchlepper.dataStore.impl.typeSystem.int32;

import ch.dcrux.newSchlepper.commandProcessor.*;
import ch.dcrux.newSchlepper.dataStore.DataOrUid;
import ch.dcrux.newSchlepper.dataStore.impl.Store;
import ch.dcrux.newSchlepper.dataStore.impl.typeSystem.base.GetterBaseImpl;
import ch.dcrux.newSchlepper.dataStore.typeSystem.int32.Int32Get;

/**
 * Buran.
 *
 * @author: ${USER} Date: 22.05.13 Time: 19:41
 */
public class Int32GetImpl extends GetterBaseImpl<Integer, Int32Get> {
    @Override
    public Class<Int32Get> getSupportedClass() {
        return Int32Get.class;
    }

    @Override
    public Integer run(ISourceRef sender, Store processor, CommandId thisId,
            CommandList commandList, Int32Get command, ResultList resultList)
            throws CommandException {
        final DataOrUid dou = getDataOrUid(resultList, command.getDataTarget());
        final Object originalValue = processor.getDataStore().getData(dou);
        if (originalValue instanceof Integer) {
            return (Integer) originalValue;
        } else {
            return null;
        }
    }
}
