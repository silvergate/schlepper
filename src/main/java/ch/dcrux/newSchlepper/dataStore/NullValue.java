package ch.dcrux.newSchlepper.dataStore;

import java.io.Serializable;

/**
 * Buran.
 *
 * @author: ${USER} Date: 24.05.13 Time: 00:18
 */
public final class NullValue implements Serializable {
    private NullValue() {
    }

    public static final NullValue SINGLETON = new NullValue();

    @Override
    public String toString() {
        return "<NullValue>";
    }
}
