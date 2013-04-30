package ch.xcrux.schlepper.types.int32;

import ch.xcrux.schlepper.*;
import ch.xcrux.schlepper.changes.ChangeInfo;
import ch.xcrux.schlepper.changes.IChange;
import ch.xcrux.schlepper.changes.IRollbackableChange;
import ch.xcrux.schlepper.changes.IStoreInts;
import com.sun.istack.internal.Nullable;

/**
 * Buran.
 *
 * @author: ${USER} Date: 27.04.13 Time: 17:25
 */
public class Int32Get implements IChange<Integer, Int32Meta, Integer> {

    @Override
    public IRollbackableChange<Integer, Int32Meta, Integer> createChange() {
        return new IRollbackableChange<Integer, Int32Meta, Integer>() {
            private Integer oldData;

            @Override
            public ChangeInfo<Integer, Integer> perform(DataId dataId, Int32Meta metadata,
                    @Nullable Integer currentData, IStoreInts storeInts) {
                return ChangeInfo.readOnlyAndReturn(currentData);
            }
        };
    }

}
