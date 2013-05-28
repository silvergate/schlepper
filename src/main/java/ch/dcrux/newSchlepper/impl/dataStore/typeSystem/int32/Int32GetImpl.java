package ch.dcrux.newSchlepper.impl.dataStore.typeSystem.int32;

import ch.dcrux.newSchlepper.commandProcessor.*;
import ch.dcrux.newSchlepper.dataStore.DataOrUid;
import ch.dcrux.newSchlepper.dataStore.typeSystem.int32.Int32Get;
import ch.dcrux.newSchlepper.impl.dataStore.Store;
import ch.dcrux.newSchlepper.impl.dataStore.javaSpecific.IData;
import ch.dcrux.newSchlepper.impl.dataStore.javaSpecific.Int32Data;
import ch.dcrux.newSchlepper.impl.dataStore.typeSystem.base.GetterBaseImpl;

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
        final IData originalValue = processor.getDataStore().getData(dou);
        if (originalValue instanceof Int32Data) {
            return ((Int32Data) originalValue).getValue();
        } else {
            return null;
        }
    }
}
