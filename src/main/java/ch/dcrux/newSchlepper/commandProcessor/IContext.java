package ch.dcrux.newSchlepper.commandProcessor;

/**
 * Methods can be called from any thread.
 *
 * @author: ${USER} Date: 21.05.13 Time: 23:07
 */
public interface IContext {
    void onResultSuccess(CommandListId commandListId, ResultList resultList);

    void onResultFailure(CommandListId commandListId,
            WrappedCommandException wrappedCommandException);
}
