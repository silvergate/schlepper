package ch.xcrux.schlepper.types.composite;

/**
 * Buran.
 *
 * @author: ${USER} Date: 26.04.13 Time: 18:39
 */
public class FieldIndex {
    private final short index;

    public FieldIndex(short index) {
        this.index = index;
    }

    public short getIndex() {
        return index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FieldIndex that = (FieldIndex) o;

        if (index != that.index) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) index;
    }
}
