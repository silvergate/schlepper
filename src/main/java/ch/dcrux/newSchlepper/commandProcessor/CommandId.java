package ch.dcrux.newSchlepper.commandProcessor;

/**
 * Buran.
 *
 * @author: ${USER} Date: 21.05.13 Time: 22:15
 */
public class CommandId {
    private final short id;

    public CommandId(short id) {
        this.id = id;
    }

    public short getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommandId commandId = (CommandId) o;

        if (id != commandId.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) id;
    }
}
