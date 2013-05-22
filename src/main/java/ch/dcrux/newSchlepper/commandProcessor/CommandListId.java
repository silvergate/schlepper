package ch.dcrux.newSchlepper.commandProcessor;

/**
 * Buran.
 *
 * @author: ${USER} Date: 21.05.13 Time: 23:04
 */
public class CommandListId {
    private final int id;

    public CommandListId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommandListId that = (CommandListId) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
