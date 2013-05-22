package ch.xcrux.schlepper.impl;

import ch.xcrux.schlepper.globals.AddData;
import ch.xcrux.schlepper.globals.AddDataInterceptor;
import ch.xcrux.schlepper.globals.DataIdAndMetadataId;
import ch.xcrux.schlepper.interceptors.IThreadSwitcher;
import ch.xcrux.schlepper.interceptors.Interception;
import ch.xcrux.schlepper.interceptors.InterceptorId;
import ch.xcrux.schlepper.taskItem.ResultIndex;
import ch.xcrux.schlepper.taskItem.TaskListResults;
import ch.xcrux.schlepper.taskItem.TaskSet;
import ch.xcrux.schlepper.types.int32.Int32Get;
import ch.xcrux.schlepper.types.int32.Int32Meta;
import ch.xcrux.schlepper.types.int32.Int32Set;

import java.text.MessageFormat;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Buran.
 *
 * @author: ${USER} Date: 27.04.13 Time: 16:06
 */
public class Tests {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Store s = new Store();

        final InterceptorId addIntId = s.addInterception(new IThreadSwitcher() {
            @Override
            public void invoke(List<Interception> interceptions) {
                for (final Interception interception : interceptions) {
                    System.out.println(MessageFormat
                            .format("Interception Called. ID: {0}, " + "Value {1}",
                                    interception.getInterceptor(), interception.getRetValue()));
                }
            }
        }, new AddDataInterceptor());
        System.out.println("Added interception as ID: " + addIntId);

        TaskSet ts = new TaskSet();
        final int am = ts.add(new AddData(new Int32Meta()));
        ts.add(am, new Int32Set(345));
        final ResultIndex amr = ts.add(am, new Int32Get());

        final TaskListResults results = s.process(ts);

        DataIdAndMetadataId rv = (DataIdAndMetadataId) results.getResults().get(am);
        System.out.println("At index " + am + ", found: " + rv);
        Integer value = (Integer) results.get(amr);
        System.out.println("IntegerValue: " + value);
        System.out.println("Got: " + rv.getMetadataId().getId());

        s.shutdown();
    }
}
