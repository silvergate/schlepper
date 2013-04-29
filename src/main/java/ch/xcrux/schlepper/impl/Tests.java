package ch.xcrux.schlepper.impl;

import ch.xcrux.schlepper.TaskListResults;
import ch.xcrux.schlepper.TaskSet;
import ch.xcrux.schlepper.globals.AddData;
import ch.xcrux.schlepper.globals.DataIdAndMetadataId;
import ch.xcrux.schlepper.types.int32.Int32Get;
import ch.xcrux.schlepper.types.int32.Int32Meta;
import ch.xcrux.schlepper.types.int32.Int32Set;

import java.util.concurrent.ExecutionException;

/**
 * Buran.
 *
 * @author: ${USER} Date: 27.04.13 Time: 16:06
 */
public class Tests {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Store s = new Store();

        TaskSet ts = new TaskSet();
        final int am = ts.add(new AddData(new Int32Meta()));
        ts.add(am, new Int32Set(345)) ;
        final int amr = ts.add(am, new Int32Get());

        final TaskListResults results = s.process(ts);

        DataIdAndMetadataId rv = (DataIdAndMetadataId)results.getResults().get(am);
        System.out.println("At index " + am + ", found: " + rv);
        Integer value = (Integer)results.getResults().get(amr);
        System.out.println("IntegerValue: " + value);
        System.out.println("Got: " + rv.getMetadataId().getId());
    }
}
