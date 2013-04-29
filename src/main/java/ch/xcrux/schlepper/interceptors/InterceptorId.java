package ch.xcrux.schlepper.interceptors;

/**
 * Buran.
 *
 * @author: ${USER} Date: 30.04.13 Time: 00:33
 */
public class InterceptorId {
    private final int id;

    public InterceptorId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InterceptorId that = (InterceptorId) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "InterceptorId{" +
                "id=" + id +
                '}';
    }
}
