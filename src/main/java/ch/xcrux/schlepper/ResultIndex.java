package ch.xcrux.schlepper;

/**
 * Buran.
 *
 * @author: ${USER} Date: 29.04.13 Time: 19:58
 */
public class ResultIndex {
    private final short index;

    public ResultIndex(short index) {
        this.index = index;
    }

    public ResultIndex(int index) {
        this((short) index);
    }

    public short getIndex() {
        return index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResultIndex that = (ResultIndex) o;

        if (index != that.index) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) index;
    }
}
