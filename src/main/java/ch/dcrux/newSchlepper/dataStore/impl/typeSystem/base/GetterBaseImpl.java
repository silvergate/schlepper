package ch.dcrux.newSchlepper.dataStore.impl.typeSystem.base;

import ch.dcrux.newSchlepper.commandProcessor.CommandException;
import ch.dcrux.newSchlepper.commandProcessor.IReadOnlyCommand;
import ch.dcrux.newSchlepper.commandProcessor.IReadOnlyCommandImpl;
import ch.dcrux.newSchlepper.commandProcessor.ResultList;
import ch.dcrux.newSchlepper.dataStore.DataOrUid;
import ch.dcrux.newSchlepper.dataStore.DataTarget;
import ch.dcrux.newSchlepper.dataStore.impl.Store;

/**
 * Buran.
 *
 * @author: ${USER} Date: 22.05.13 Time: 23:10
 */
public abstract class GetterBaseImpl<TRetval, TCommandClass extends IReadOnlyCommand<TRetval>>
        implements IReadOnlyCommandImpl<Store, TRetval, TCommandClass> {

    protected DataOrUid getDataOrUid(ResultList resultList, DataTarget target)
            throws CommandException {
        return DataIdOrUidGetter.getDataOrUid(resultList, target);
    }
}
