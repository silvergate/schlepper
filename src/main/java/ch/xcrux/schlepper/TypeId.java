package ch.xcrux.schlepper;

/**
 * Buran.
 *
 * @author: ${USER} Date: 27.04.13 Time: 14:55
 */
public class TypeId {
    private final byte id;

    public TypeId(byte id) {
        this.id = id;
    }

    public byte getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TypeId typeId = (TypeId) o;

        if (id != typeId.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) id;
    }
}
