package ch.xcrux.schlepper.data;

import ch.xcrux.schlepper.IChange;

/**
 * Buran.
 *
 * @author: ${USER} Date: 26.04.13 Time: 19:43
 */
public interface IData {
    /**
     * Returns <code>true</code> if data has changed.
     *
     * @param change
     * @return
     */
    boolean applyChange(IChange change);
}
