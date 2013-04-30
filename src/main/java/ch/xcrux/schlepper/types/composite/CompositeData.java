package ch.xcrux.schlepper.types.composite;

import ch.xcrux.schlepper.changes.IChange;
import ch.xcrux.schlepper.data.IData;

import java.util.HashMap;
import java.util.Map;

/**
 * Buran.
 *
 * @author: ${USER} Date: 26.04.13 Time: 18:30
 */
public class CompositeData implements IData {
    private final Map<FieldIndex, Object> data = new HashMap<FieldIndex, Object>();

    public Map<FieldIndex, Object> getData() {
        return data;
    }

    @Override
    public boolean applyChange(IChange change) {
        if (change instanceof SetValueChange) {
            SetValueChange svc = (SetValueChange) change;
            final Object originalValue;
            if (svc.getObject() != null) {
                originalValue = this.data.put(svc.getIndex(), svc.getObject());
                return !svc.getObject().equals(originalValue);
            } else {
                originalValue = this.data.remove(svc.getIndex());
                return (originalValue != null);
            }
        }

        throw new IllegalArgumentException("");
    }
}
