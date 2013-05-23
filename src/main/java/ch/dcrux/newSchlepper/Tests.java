package ch.dcrux.newSchlepper;

import ch.dcrux.newSchlepper.commandProcessor.*;
import ch.dcrux.newSchlepper.dataStore.DataOrUid;
import ch.dcrux.newSchlepper.dataStore.DataTarget;
import ch.dcrux.newSchlepper.dataStore.Uid;
import ch.dcrux.newSchlepper.dataStore.commands.AddData;
import ch.dcrux.newSchlepper.dataStore.impl.SourceRef;
import ch.dcrux.newSchlepper.dataStore.impl.Store;
import ch.dcrux.newSchlepper.dataStore.interceptors.AddDataInterceptor;
import ch.dcrux.newSchlepper.dataStore.typeSystem.int32.Int32Get;
import ch.dcrux.newSchlepper.dataStore.typeSystem.int32.Int32Meta;
import ch.dcrux.newSchlepper.dataStore.typeSystem.int32.Int32Set;

/**
 * Buran.
 *
 * @author: ${USER} Date: 21.05.13 Time: 22:09
 */
public class Tests {
    public static void main(String[] args) {
        SourceRef server = new SourceRef("server");
        SourceRef client = new SourceRef("client");

        Store store = new Store(new IContext() {
            @Override
            public void onResultSuccess(CommandListId commandListId, ResultList resultList) {
                System.out.println("result success");
                System.out.println(
                        "Integer 1: " + resultList.get(new CommandId((short) 1), Integer.class));
                System.out.println(
                        "Integer 3: " + resultList.get(new CommandId((short) 3), Integer.class));
            }

            @Override
            public void onResultFailure(CommandListId commandListId,
                    WrappedCommandException wrappedCommandException) {
                System.out.println("result Failure");
            }
        });

        /* Add-data-interception */
        store.getInterceptionService().add(server, new AddDataInterceptor());
        store.getInterceptionService().add(client, new AddDataInterceptor());

        final DataTarget id = DataTarget
                .dataOrOuid(DataOrUid.data(Uid.c("BC78ABBF-FB2B-470D-8047-5393EFD3441A")));
        store.run(server, CommandList
                .c(new AddData(id.getOne().getUid(), new Int32Meta()), new Int32Get(id),
                        new Int32Set(id, 343), new Int32Get(id),
                        new AddData(null, new Int32Meta())));

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File
            // Templates.
        }

        store.shutdown();
    }
}
