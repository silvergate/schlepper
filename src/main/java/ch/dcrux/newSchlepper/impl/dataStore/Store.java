package ch.dcrux.newSchlepper.impl.dataStore;

import ch.dcrux.newSchlepper.commandProcessor.IContext;
import ch.dcrux.newSchlepper.commandProcessor.ISourceRef;
import ch.dcrux.newSchlepper.impl.commandProcessor.Processor;
import ch.dcrux.newSchlepper.impl.dataStore.commands.AddDataImpl;
import ch.dcrux.newSchlepper.impl.dataStore.commands.NullCommandImpl;
import ch.dcrux.newSchlepper.impl.dataStore.interceptors.AddDataInterceptorImpl;
import ch.dcrux.newSchlepper.impl.dataStore.typeSystem.int32.Int32GetImpl;
import ch.dcrux.newSchlepper.impl.dataStore.typeSystem.int32.Int32SetImpl;

/**
 * Buran.
 *
 * @author: ${USER} Date: 22.05.13 Time: 00:32
 */
public class Store extends Processor<Store> {

    private final DataStore dataStore = new DataStore();
    private final MetaStore metaStore = new MetaStore();
    private final IContext context;

    public Store(IContext context) {
        super(new StoreInterceptionService());
        register();
        this.context = context;
    }

    protected void register() {
        this.registerCommand(new Int32SetImpl());
        this.registerCommand(new Int32GetImpl());
        this.registerCommand(new AddDataImpl());
        this.registerCommand(new NullCommandImpl());

        getInterceptionService().register(new AddDataInterceptorImpl());
    }

    public MetaStore getMetaStore() {
        return metaStore;
    }

    public DataStore getDataStore() {
        return dataStore;
    }

    @Override
    protected IContext getContext(ISourceRef sourceRef) {
        return this.context;
    }

    @Override
    protected Store getThis() {
        return this;
    }
}
