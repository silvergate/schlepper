package ch.dcrux.newSchlepper.commandProcessor;

import java.util.Set;

/**
 * Buran.
 *
 * @author: ${USER} Date: 21.05.13 Time: 22:10
 */
public interface ICommand<TRetval extends Object> {
    Set<Class<? extends CommandException>> getPossibleExceptions();
}
