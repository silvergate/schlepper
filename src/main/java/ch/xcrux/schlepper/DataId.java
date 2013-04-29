package ch.xcrux.schlepper;

/**
 * Buran.
 *
 * @author: ${USER} Date: 26.04.13 Time: 19:18
 */
public class DataId {
    private final int id;

    public DataId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DataId dataId = (DataId) o;

        if (id != dataId.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
