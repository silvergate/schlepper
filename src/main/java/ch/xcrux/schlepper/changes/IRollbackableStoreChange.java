package ch.xcrux.schlepper.changes;

import ch.xcrux.schlepper.StoreChangeInfo;

/**
 * Buran.
 *
 * @author: ${USER} Date: 27.04.13 Time: 17:50
 */
public interface IRollbackableStoreChange<TRetData extends Object> {
    StoreChangeInfo<TRetData> perform(IStoreInts functions);
}
