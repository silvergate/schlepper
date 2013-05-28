package ch.dcrux.newSchlepper.impl.dataStore.typeSystem.base;

import ch.dcrux.newSchlepper.commandProcessor.CommandException;
import ch.dcrux.newSchlepper.commandProcessor.IModifyCommand;
import ch.dcrux.newSchlepper.commandProcessor.IModifyCommandImpl;
import ch.dcrux.newSchlepper.commandProcessor.ResultList;
import ch.dcrux.newSchlepper.dataStore.DataOrUid;
import ch.dcrux.newSchlepper.dataStore.DataTarget;
import ch.dcrux.newSchlepper.impl.dataStore.Store;

/**
 * Buran.
 *
 * @author: ${USER} Date: 22.05.13 Time: 22:46
 */
public abstract class SetterBaseImpl<TRetval, TCommandClass extends IModifyCommand<TRetval>>
        implements IModifyCommandImpl<Store, TRetval, TCommandClass> {

    protected DataOrUid getDataOrUid(ResultList resultList, DataTarget target)
            throws CommandException {
        return BaseUtil.getDataOrUid(resultList, target);
    }
}
