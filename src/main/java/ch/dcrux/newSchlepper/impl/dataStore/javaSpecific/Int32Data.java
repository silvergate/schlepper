package ch.dcrux.newSchlepper.impl.dataStore.javaSpecific;

/**
 * Buran.
 *
 * @author: ${USER} Date: 28.05.13 Time: 22:47
 */
public class Int32Data implements IData {
    private final int value;

    public Int32Data(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public IData createClone() {
        return new Int32Data(this.value);
    }
}
