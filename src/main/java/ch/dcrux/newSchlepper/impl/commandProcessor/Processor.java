package ch.dcrux.newSchlepper.impl.commandProcessor;

import ch.dcrux.newSchlepper.commandProcessor.*;
import ch.dcrux.newSchlepper.commandProcessor.interception.AbortException;
import ch.dcrux.newSchlepper.commandProcessor.interception.IInterceptionContext;
import ch.dcrux.newSchlepper.commandProcessor.interception.InterceptionId;
import ch.dcrux.newSchlepper.commandProcessor.interception.TargetedAbort;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Buran.
 *
 * @author: ${USER} Date: 21.05.13 Time: 22:20
 */
public abstract class Processor<TProcessor extends Processor<TProcessor>> {

    protected abstract IContext getContext(ISourceRef sourceRef);

    protected abstract TProcessor getThis();

    private final InterceptionService interceptionService;

    protected Processor(InterceptionService interceptionService) {
        this.interceptionService = interceptionService;
    }

    public InterceptionService getInterceptionService() {
        return this.interceptionService;
    }

    private final Map<Class<?>, IReadOnlyCommandImpl<TProcessor, ?, ?>> readOnlyCommandImpls =
            new HashMap<>();
    private final Map<Class<?>, IModifyCommandImpl<TProcessor, ?, ?>> modifyCommandImpls =
            new HashMap<>();

    /* Executor service for modifying the data tree */
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    /* Executor for callback */
    private final ExecutorService returnValuesExecutorService = Executors.newCachedThreadPool();
    /* Interception callbacks */
    private final ExecutorService interceptionCallbackExecutorService =
            Executors.newCachedThreadPool();

    private AtomicInteger commandListCounter = new AtomicInteger(); //TODO: Unique only for one
    // ISourceRef

    public void registerCommand(IReadOnlyCommandImpl<TProcessor, ?, ?> commandImpl) {
        this.readOnlyCommandImpls.put(commandImpl.getSupportedClass(), commandImpl);
    }

    public void registerCommand(IModifyCommandImpl<TProcessor, ?, ?> commandImpl) {
        this.modifyCommandImpls.put(commandImpl.getSupportedClass(), commandImpl);
    }

    private void invokeFailure(final IContext context, final CommandListId commandListId,
            final WrappedCommandException wrappedCommandException) {
        returnValuesExecutorService.execute(new Runnable() {
            @Override
            public void run() {
                context.onResultFailure(commandListId, wrappedCommandException);
            }
        });
    }

    private void invokeSuccess(final IContext context, final CommandListId commandListId,
            final ResultList resultList) {
        returnValuesExecutorService.execute(new Runnable() {
            @Override
            public void run() {
                context.onResultSuccess(commandListId, resultList);
            }
        });
    }

    private void checkForAborts(Processor processor, ISourceRef modifyCommandListSrc,
            CommandList modifyCommandList) throws WrappedCommandException {
        final Collection<TargetedAbort> aborts = this.interceptionService
                .getAborts(processor, modifyCommandListSrc, modifyCommandList);
        if (!aborts.isEmpty()) {
            final AbortException ae = new AbortException(aborts);
            final Set<CommandId> commandIdSet = new HashSet<>();
            for (final TargetedAbort targetedAbort : ae.getAborts()) {
                commandIdSet.addAll(targetedAbort.getSource());
            }
            throw new WrappedCommandException(commandIdSet, ae);
        }
    }

    private void doRollback(RollbackStorage storage, CommandId failedCommandId, ISourceRef sender,
            CommandList list) {
        short beginIndex = failedCommandId.getId();
        short endIndex = 0;
        for (short index = beginIndex; index >= endIndex; index--) {
            /* Rollback every modification change */
            final CommandId commandId = new CommandId(index);
            final ICommand<?> command = list.get(commandId);

            /* Only modification changes */
            if (command instanceof IModifyCommand<?>) {
                final IModifyCommandImpl modifyCommandImpl =
                        this.modifyCommandImpls.get(command.getClass());
                storage.setCurrentCommandId(commandId);
                modifyCommandImpl
                        .rollback(sender, getThis(), commandId, list, (IModifyCommand) command,
                                storage);
            }
        }
    }

    private void processInExecutorContext(IContext context, ISourceRef sender,
            CommandList commandList, CommandListId commandListId) {

        /* Rollback storage */
        RollbackStorage rollbackStorage = new RollbackStorage();

        /* Result list */
        final ResultList resultList = new ResultList();

        short commandIdShort = 0;
        for (final ICommand<?> command : commandList) {
            final CommandId commandId = new CommandId(commandIdShort);

            if (command instanceof IReadOnlyCommand<?>) {
                final IReadOnlyCommandImpl readOnlyCommandImpl =
                        this.readOnlyCommandImpls.get(command.getClass());
                try {
                    final Object retval = readOnlyCommandImpl
                            .run(sender, getThis(), commandId, commandList,
                                    (IReadOnlyCommand) command, resultList);
                    resultList.add(retval);
                } catch (CommandException e) {
                    /* Failure, cancel execution */
                    invokeFailure(context, commandListId,
                            new WrappedCommandException(commandId, e));
                    return;
                }
            } else {
                if (command instanceof IModifyCommand<?>) {
                    final IModifyCommandImpl modifyCommandImpl =
                            this.modifyCommandImpls.get(command.getClass());
                    try {
                        rollbackStorage.setCurrentCommandId(commandId);
                        final Object retval = modifyCommandImpl
                                .run(sender, getThis(), commandId, commandList,
                                        (IModifyCommand) command, resultList, rollbackStorage);
                        resultList.add(retval);
                    } catch (CommandException e) {
                    /* Failure, cancel execution */
                        /* Perform rollback */
                        doRollback(rollbackStorage, commandId, sender, commandList);
                        invokeFailure(context, commandListId,
                                new WrappedCommandException(commandId, e));
                        return;
                    }
                } else {
                    throw new IllegalArgumentException("Unknown command type.");
                }
            }
            commandIdShort++;
        }

        /* Check aborts */
        try {
            checkForAborts(this, sender, commandList);
        } catch (WrappedCommandException wrappedCommandException) {
            doRollback(rollbackStorage, new CommandId((short) (commandList.getNumOfCommands() - 1)),
                    sender, commandList);
            invokeFailure(context, commandListId, wrappedCommandException);
            return;
        }

        /* Invoke sender */
        invokeSuccess(context, commandListId, resultList);

        /* Invoke data interceptors */
        invokeDataInterceptors(this, sender, commandList);
    }

    private void invokeFailureForFollowingCommands(final ISourceRef interceptor,
            final InterceptionId interceptionId, final IInterceptionContext context,
            final WrappedCommandException wrappedCommandException) {
        this.interceptionCallbackExecutorService.execute(new Runnable() {
            @Override
            public void run() {
                context.onResultFailure(interceptor, interceptionId, wrappedCommandException);
            }
        });
    }

    private void invokeSuccessForFollowingCommands(final ISourceRef interceptor,
            final InterceptionId interceptionId, final IInterceptionContext context,
            final ResultList resultList) {
        this.interceptionCallbackExecutorService.execute(new Runnable() {
            @Override
            public void run() {
                context.onResultSuccess(interceptor, interceptionId, resultList);
            }
        });
    }

    private void processInExecutorContextForFolowingCommands(IInterceptionContext context,
            ISourceRef interceptor, CommandList commandList, InterceptionId interceptionId) {

        /* Result list */
        final ResultList resultList = new ResultList();

        short commandIdShort = 0;
        for (final ICommand<?> command : commandList) {
            final CommandId commandId = new CommandId(commandIdShort);

            if (command instanceof IReadOnlyCommand<?>) {
                final IReadOnlyCommandImpl readOnlyCommandImpl =
                        this.readOnlyCommandImpls.get(command.getClass());
                try {
                    final Object retval = readOnlyCommandImpl
                            .run(interceptor, getThis(), commandId, commandList,
                                    (IReadOnlyCommand) command, resultList);
                    resultList.add(retval);
                } catch (CommandException e) {
                    /* Failure, cancel execution */
                    invokeFailureForFollowingCommands(interceptor, interceptionId, context,
                            new WrappedCommandException(commandId, e));
                    return;
                }
            } else {
                throw new IllegalArgumentException("Modifying commands not allowed.");
            }
            commandIdShort++;
        }

        /* Invoke sender */
        invokeSuccessForFollowingCommands(interceptor, interceptionId, context, resultList);
    }

    private void invokeDataInterceptors(Processor processor, ISourceRef modifyCommandListSrc,
            CommandList modifyCommandList) {
        final Collection<FolowingCommands> folowingCommands = this.interceptionService
                .getFollowingCommands(processor, modifyCommandListSrc, modifyCommandList);
        for (FolowingCommands folowingCommand : folowingCommands) {
            final IInterceptionContext context =
                    this.interceptionService.getContext(folowingCommand.getInterceptor());

            processInExecutorContextForFolowingCommands(context, folowingCommand.getInterceptor(),
                    folowingCommand.getCommandList(), folowingCommand.getInterceptionId());
        }
    }

    public CommandListId run(final ISourceRef sender, final CommandList commandList) {
        final boolean modifyCommand = commandList.hasModifyCommand();
        //TODO: if modifyCommand = false: can execute in parallel

        final int commandId = commandListCounter.getAndIncrement();
        final CommandListId commandListId = new CommandListId(commandId);
        final IContext context = getContext(sender);
        this.executorService.execute(new Runnable() {
            @Override
            public void run() {
                processInExecutorContext(context, sender, commandList, commandListId);
            }
        });

        return commandListId;
    }

    public void shutdown() {
        this.executorService.shutdown();
        this.returnValuesExecutorService.shutdown();
        this.interceptionCallbackExecutorService.shutdown();
    }
}
