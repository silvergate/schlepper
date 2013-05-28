package ch.dcrux.newSchlepper.impl.dataStore.javaSpecific;

import java.io.Serializable;

/**
 * Buran.
 *
 * @author: ${USER} Date: 28.05.13 Time: 22:39
 */
public interface IData extends Serializable {
    IData createClone();
}
