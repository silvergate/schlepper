package ch.dcrux.newSchlepper.commandProcessor.interception;

/**
 * Buran.
 *
 * @author: ${USER} Date: 22.05.13 Time: 09:02
 */
public class InterceptionId {
    private final int id;

    public InterceptionId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InterceptionId that = (InterceptionId) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
