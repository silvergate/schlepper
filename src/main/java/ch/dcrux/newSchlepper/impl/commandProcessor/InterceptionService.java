package ch.dcrux.newSchlepper.impl.commandProcessor;

import ch.dcrux.newSchlepper.commandProcessor.*;
import ch.dcrux.newSchlepper.commandProcessor.interception.*;

import java.text.MessageFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Buran.
 *
 * @author: ${USER} Date: 22.05.13 Time: 08:57
 */
public abstract class InterceptionService {

    public abstract IInterceptionContext getContext(ISourceRef interceptorRef);

    private final AtomicInteger interceptionIdCounter = new AtomicInteger();

    private Map<InterceptionIndexTypeRef<?>, IInterceptionCmpIndex<IModifyCommand<?>>>
            interceptionIndexes = new HashMap<>();

    private Map<Class<? extends IGetDataInterception>, IGetDataInterceptionImpl<?, ?, ?>>
            classIGetDataInterceptionImplMap = new HashMap<>();
    private Map<Class<? extends IAbortInterception>, IAbortInterceptionImpl<?, ?, ?>>
            classIAbortInterceptionImplMap = new HashMap<>();

    public void register(IAbortInterceptionImpl<?, ?, ?> abortInterceptionImpl) {
        this.classIAbortInterceptionImplMap
                .put(abortInterceptionImpl.getSupportingInterface(), abortInterceptionImpl);
    }

    public void register(IGetDataInterceptionImpl<?, ?, ?> getDataInterceptionImpl) {
        this.classIGetDataInterceptionImplMap
                .put(getDataInterceptionImpl.getSupportingInterface(), getDataInterceptionImpl);
    }

    IInterceptionImpl getInterceptionImpl(IInterception<?> interception) {
        if (interception instanceof IAbortInterception) {
            return this.classIAbortInterceptionImplMap.get(interception.getClass());
        } else {
            return this.classIGetDataInterceptionImplMap.get(interception.getClass());
        }

    }

    public InterceptionId add(ISourceRef interceptorRef, IInterception<?> interception) {
        final int interceptionIdInt = this.interceptionIdCounter.getAndIncrement();
        final InterceptionId interceptionId = new InterceptionId(interceptionIdInt);

        /* Get implementation */
        final IInterceptionImpl impl = getInterceptionImpl(interception);

        /* Get or create index */
        IInterceptionCmpIndex index = this.interceptionIndexes.get(impl.getIndexType());
        if (index == null) {
            index = impl.createIndexInstance();
            this.interceptionIndexes
                    .put(impl.getIndexType(), (IInterceptionCmpIndex<IModifyCommand<?>>) index);
        }

        index.add(interception, interceptorRef, interceptionId);

        return interceptionId;
    }

    boolean remove(ISourceRef interceptorRef, InterceptionId interceptionId) {
        if (true) {
            throw new UnsupportedOperationException("Implement ME");
        }
        return true;
    }

    Map<CommandId, Set<InterceptionTriple>> getInterceptions(CommandList commandList,
            boolean includeGetDataInterceptions, boolean includeAbortExceptions) {
        final Map<CommandId, Set<InterceptionTriple>> interceptionSet = new HashMap<>();

        short commandIdShort = 0;
        for (final ICommand<?> command : commandList) {
            if (command instanceof IModifyCommand<?>) {
                for (final Map.Entry<InterceptionIndexTypeRef<?>,
                        IInterceptionCmpIndex<IModifyCommand<?>>> indexEntry : this
                        .interceptionIndexes.entrySet()) {
                    final IInterceptionCmpIndex<IModifyCommand<?>> value = indexEntry.getValue();
                    final boolean isAssignable =
                            value.getSupportedClass().isAssignableFrom(command.getClass());
                    if (isAssignable) {
                        final Set<InterceptionTriple> results =
                                value.getMatchingInterceptions((IModifyCommand<?>) command,
                                        includeGetDataInterceptions, includeAbortExceptions);
                        if (!results.isEmpty()) {
                            final CommandId commandId = new CommandId(commandIdShort);
                            interceptionSet.put(commandId, results);

                        }
                    }
                }
            }
            commandIdShort++;
        }

        return interceptionSet;
    }

    Map<InterceptionTriple, Set<CommandId>> getInterceptionsByTriple(CommandList commandList,
            boolean includeGetDataInterceptions, boolean includeAbortExceptions) {
        final Map<InterceptionTriple, Set<CommandId>> result = new HashMap<>();
        final Map<CommandId, Set<InterceptionTriple>> interceptions =
                getInterceptions(commandList, includeGetDataInterceptions, includeAbortExceptions);
        for (final Map.Entry<CommandId, Set<InterceptionTriple>> interceptionsEntry : interceptions
                .entrySet()) {
            for (final InterceptionTriple triple : interceptionsEntry.getValue()) {
                Set<CommandId> commandIds = result.get(triple);
                if (commandIds == null) {
                    commandIds = new HashSet<>();
                    result.put(triple, commandIds);
                }
                commandIds.add(interceptionsEntry.getKey());
            }
        }
        return result;
    }

    Collection<FolowingCommands> getFollowingCommands(Processor processor,
            ISourceRef modifyCommandListSrc, CommandList modifyCommandList) {
        final Collection<FolowingCommands> followingCommands = new ArrayList<>();

        final Map<InterceptionTriple, Set<CommandId>> interceptions =
                getInterceptionsByTriple(modifyCommandList, true, false);
        for (final Map.Entry<InterceptionTriple, Set<CommandId>> interceptionEntry : interceptions
                .entrySet()) {
            final InterceptionTriple triple = interceptionEntry.getKey();
            final Set<CommandId> commandIds = interceptionEntry.getValue();

            /* Get interception implementation */
            IGetDataInterception<IModifyCommand<?>> getDataInterception =
                    (IGetDataInterception<IModifyCommand<?>>) triple.getInterception();
            final IGetDataInterceptionImpl impl =
                    this.classIGetDataInterceptionImplMap.get(getDataInterception.getClass());
            if (impl == null) {
                throw new IllegalStateException(MessageFormat
                        .format("No implementation for " + "class {0} found.",
                                getDataInterception.getClass()));
            }

            /* Execute */
            final CommandList commandList =
                    impl.createCommandList(processor, getDataInterception, modifyCommandListSrc,
                            modifyCommandList, commandIds, triple.getInterceptorRef());
            if (commandList != null) {
                if (commandList.hasModifyCommand()) {
                    /* Modify-Commands are not allowed */
                    System.err.println("Modify-Commands are not allowed");
                } else {
                    /* OK */
                    followingCommands.add(new FolowingCommands(triple.getInterceptorRef(),
                            triple.getInterceptionId(), commandList));
                }
            }
        }

        return followingCommands;
    }

    Collection<TargetedAbort> getAborts(Processor processor, ISourceRef modifyCommandListSrc,
            CommandList modifyCommandList) {
        final Collection<TargetedAbort> targetAborts = new ArrayList<>();

        final Map<InterceptionTriple, Set<CommandId>> interceptions =
                getInterceptionsByTriple(modifyCommandList, false, true);
        for (final Map.Entry<InterceptionTriple, Set<CommandId>> interceptionEntry : interceptions
                .entrySet()) {
            final InterceptionTriple triple = interceptionEntry.getKey();
            final Set<CommandId> commandIds = interceptionEntry.getValue();

            /* Get interception implementation */
            IAbortInterception<IModifyCommand<?>> abortInterception =
                    (IAbortInterception<IModifyCommand<?>>) triple.getInterception();
            final IAbortInterceptionImpl impl =
                    this.classIAbortInterceptionImplMap.get(abortInterception.getClass());
            if (impl == null) {
                throw new IllegalStateException(MessageFormat
                        .format("No implementation for " + "class {0} found.",
                                abortInterception.getClass()));
            }

            /* Execute */
            final Set<TargetedAbort> targetedAborts =
                    impl.createAbortList(processor, abortInterception, modifyCommandListSrc,
                            modifyCommandList, commandIds, triple.getInterceptorRef());
            if (targetedAborts != null) {
                    /* OK */
                targetAborts.addAll(targetedAborts);
            }
        }

        /* On success, invoke commit */
        if (targetAborts.isEmpty()) {
            for (final Map.Entry<InterceptionTriple, Set<CommandId>> interceptionEntry :
                    interceptions
                    .entrySet()) {
                final InterceptionTriple triple = interceptionEntry.getKey();
                final Set<CommandId> commandIds = interceptionEntry.getValue();

                /* Get interception implementation */
                IAbortInterception<IModifyCommand<?>> abortInterception =
                        (IAbortInterception<IModifyCommand<?>>) triple.getInterception();
                final IAbortInterceptionImpl impl =
                        this.classIAbortInterceptionImplMap.get(abortInterception.getClass());
                if (impl == null) {
                    throw new IllegalStateException(MessageFormat
                            .format("No implementation for " + "class {0} found.",
                                    abortInterception.getClass()));
                }

                /* Execute */
                impl.onCommit(processor, abortInterception, modifyCommandListSrc, modifyCommandList,
                        commandIds, triple.getInterceptorRef());
            }
        }

        return targetAborts;
    }

}
