package ch.xcrux.schlepper.types.composite;

import ch.xcrux.schlepper.*;

/**
 * Buran.
 *
 * @author: ${USER} Date: 26.04.13 Time: 20:49
 */
public class SetValueChange implements IChange<CompositeData, CompositeMeta, Void> {
    private final FieldIndex index;
    private final Object object;


    public SetValueChange(FieldIndex index, Object object) {
        this.index = index;
        this.object = object;
    }

    public FieldIndex getIndex() {
        return index;
    }

    public Object getObject() {
        return object;
    }

    @Override
    public IRollbackableChange createChange() {
        return new IRollbackableChange<CompositeData, CompositeMeta, Void>() {
            @Override
            public ChangeInfo<CompositeData, Void> perform(DataId dataId, CompositeMeta metadata,
                    CompositeData currentData, IStoreInts storeInts) {
                boolean didChange;
                final Object originalValue;
                if (getObject() != null) {
                    originalValue = currentData.getData().put(getIndex(), getObject());
                    didChange = !getObject().equals(originalValue);
                } else {
                    originalValue = currentData.getData().remove(getIndex());
                    didChange = (originalValue != null);
                }
                if (!didChange) {
                    return ChangeInfo.noop();
                } else {
                    return ChangeInfo.modifyDataInner();
                }
            }
        };
    }
}
