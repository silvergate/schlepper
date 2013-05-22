package ch.dcrux.newSchlepper.commandProcessor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Buran.
 *
 * @author: ${USER} Date: 21.05.13 Time: 22:15
 */
public class CommandList implements Iterable<ICommand<?>> {

    private final List<ICommand<?>> commandList = new ArrayList<>();
    private boolean modifyCommand;

    public static CommandList single(ICommand<?> command) {
        final CommandList list = new CommandList();
        list.add(command);
        return list;
    }

    public static CommandList c(ICommand<?>... commands) {
        final CommandList list = new CommandList();
        for (final ICommand<?> command : commands) {
            list.add(command);
        }
        return list;
    }

    public CommandId add(ICommand<?> command) {
        if (this.commandList.size() > Short.MAX_VALUE) {
            throw new IllegalStateException("this.commandList.size()>Short.MAX_VALUE");
        }
        if (command instanceof IModifyCommand) {
            this.modifyCommand = true;
        }
        this.commandList.add(command);
        return new CommandId((short) (this.commandList.size() - 1));
    }

    public ICommand<?> get(CommandId id) {
        return this.commandList.get(id.getId());
    }

    public int getNumOfCommands() {
        return this.commandList.size();
    }

    public boolean hasModifyCommand() {
        return modifyCommand;
    }

    @Override
    public Iterator<ICommand<?>> iterator() {
        return this.commandList.iterator();
    }
}
