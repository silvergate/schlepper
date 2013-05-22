package ch.xcrux.schlepper.types.composite;

import ch.xcrux.schlepper.DataId;
import ch.xcrux.schlepper.changes.ChangeInfo;
import ch.xcrux.schlepper.changes.IChange;
import ch.xcrux.schlepper.changes.IRollbackableChange;
import ch.xcrux.schlepper.changes.IStoreInts;
import com.sun.istack.internal.Nullable;

/**
 * Buran.
 *
 * @author: ${USER} Date: 26.04.13 Time: 21:41
 */
public class GetValueRequest implements IChange<CompositeData, CompositeMeta, Object> {
    private final FieldIndex fieldIndex;

    public GetValueRequest(FieldIndex fieldIndex) {
        this.fieldIndex = fieldIndex;
    }

    public FieldIndex getFieldIndex() {
        return fieldIndex;
    }

    @Override
    public IRollbackableChange<CompositeData, CompositeMeta, Object> createChange() {
        return new IRollbackableChange<CompositeData, CompositeMeta, Object>() {

            @Override
            public ChangeInfo<CompositeData, Object> perform(DataId dataId, CompositeMeta metadata,
                    @Nullable CompositeData currentData, IStoreInts storeInts) {
                Object value = currentData.getData().get(fieldIndex);
                return ChangeInfo.readOnlyAndReturn(value);

            }
        };
    }
}
