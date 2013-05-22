package ch.xcrux.schlepper.types.int32;

import ch.xcrux.schlepper.DataId;
import ch.xcrux.schlepper.changes.ChangeInfo;
import ch.xcrux.schlepper.changes.IChange;
import ch.xcrux.schlepper.changes.IRollbackableChange;
import ch.xcrux.schlepper.changes.IStoreInts;
import com.sun.istack.internal.Nullable;
import org.apache.commons.lang3.ObjectUtils;

/**
 * Buran.
 *
 * @author: ${USER} Date: 27.04.13 Time: 16:12
 */
public class Int32Set implements IChange<Integer, Int32Meta, Void> {
    private Integer value;

    public Int32Set(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public IRollbackableChange<Integer, Int32Meta, Void> createChange() {
        return new IRollbackableChange<Integer, Int32Meta, Void>() {
            @Override
            public ChangeInfo<Integer, Void> perform(DataId dataId, Int32Meta metadata,
                    @Nullable Integer currentData, IStoreInts storeInts) {
                if (ObjectUtils.equals(currentData, value)) {
                    return ChangeInfo.noop();
                } else {
                    return ChangeInfo.modifyData(value);
                }
            }
        };
    }
}
