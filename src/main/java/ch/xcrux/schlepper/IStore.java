package ch.xcrux.schlepper;

import ch.xcrux.schlepper.changes.IChange;

/**
 * Buran.
 *
 * @author: ${USER} Date: 26.04.13 Time: 21:23
 */
public interface IStore {
    void addChange(DataId dataId, IChange change);

    void addChange(IChange change);

    <T extends IType> T getFacade(DataId dataId, Class<T> type);
}
