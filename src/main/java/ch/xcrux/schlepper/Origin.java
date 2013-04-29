package ch.xcrux.schlepper;

/**
 * Buran.
 *
 * @author: ${USER} Date: 29.04.13 Time: 21:15
 */
public enum Origin {
    server(0),
    client(1);

    Origin(int value) {
        this.value = value;
    }

    private final int value;

    public int getValue() {
        return value;
    }
}
