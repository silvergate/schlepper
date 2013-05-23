package ch.dcrux.newSchlepper.dataStore.impl.interceptors;

import ch.dcrux.newSchlepper.commandProcessor.CommandId;
import ch.dcrux.newSchlepper.commandProcessor.CommandList;
import ch.dcrux.newSchlepper.commandProcessor.ISourceRef;
import ch.dcrux.newSchlepper.commandProcessor.interception.IGetDataInterceptionImpl;
import ch.dcrux.newSchlepper.commandProcessor.interception.IInterceptionCmpIndex;
import ch.dcrux.newSchlepper.commandProcessor.interception.InterceptionIndexTypeRef;
import ch.dcrux.newSchlepper.dataStore.commands.AddData;
import ch.dcrux.newSchlepper.dataStore.commands.NullCommand;
import ch.dcrux.newSchlepper.dataStore.impl.Store;
import ch.dcrux.newSchlepper.dataStore.interceptors.AddDataInterceptor;

import java.util.Set;

/**
 * Buran.
 *
 * @author: ${USER} Date: 24.05.13 Time: 00:15
 */
public class AddDataInterceptorImpl
        implements IGetDataInterceptionImpl<AddData, Store, AddDataInterceptor> {
    @Override
    public CommandList createCommandList(Store processor, AddDataInterceptor interception,
            ISourceRef modifyCommandListSource, CommandList modifyCommandList,
            Set<CommandId> matchedCommands, ISourceRef interceptor) {
        return CommandList.single(new NullCommand());
    }

    @Override
    public Class<AddDataInterceptor> getSupportingInterface() {
        return AddDataInterceptor.class;
    }

    @Override
    public InterceptionIndexTypeRef<AddData> getIndexType() {
        return AddDataInterceptionIndex.TYPE;
    }

    @Override
    public IInterceptionCmpIndex<AddData> createIndexInstance() {
        return new AddDataInterceptionIndex();
    }
}
