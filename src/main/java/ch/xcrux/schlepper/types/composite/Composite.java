package ch.xcrux.schlepper.types.composite;

import ch.xcrux.schlepper.DataId;
import ch.xcrux.schlepper.IFacade;
import ch.xcrux.schlepper.IStore;

/**
 * Buran.
 *
 * @author: ${USER} Date: 26.04.13 Time: 20:45
 */
public class Composite implements IFacade {
    private final DataId dataId;
    private final IStore store;

    public Composite(DataId dataId, IStore store) {
        this.dataId = dataId;
        this.store = store;
    }

    public void setValue(FieldIndex fieldIndex, Object data) {
        this.store.addChange(this.dataId, new SetValueChange(fieldIndex, data));
    }

    public Object getValue(FieldIndex fieldIndex) {
        //  return this.store.requestAndGet(new GetValueRequest(fieldIndex));
        return null;
    }
}
