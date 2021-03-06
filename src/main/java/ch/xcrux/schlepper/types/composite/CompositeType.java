package ch.xcrux.schlepper.types.composite;

import ch.xcrux.schlepper.DataId;
import ch.xcrux.schlepper.IFactory;
import ch.xcrux.schlepper.IStore;
import ch.xcrux.schlepper.TypeId;
import ch.xcrux.schlepper.changes.IChange;
import com.sun.istack.internal.Nullable;

/**
 * Buran.
 *
 * @author: ${USER} Date: 26.04.13 Time: 21:33
 */
public class CompositeType implements IFactory<Composite, CompositeData, CompositeMeta> {

    public static final TypeId TYPE_ID = new TypeId((byte) 2);

    @Override
    public Composite create(IStore store, DataId dataId) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isValid(@Nullable CompositeMeta metadata, @Nullable CompositeData currentData,
            @Nullable IChange change) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public TypeId getTypeId() {
        return TYPE_ID;
    }

    @Override
    public CompositeData createDataInstance() {
        return new CompositeData();
    }
}
