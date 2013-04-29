package ch.xcrux.schlepper.impl;

import ch.xcrux.schlepper.IFacadeFactory;
import ch.xcrux.schlepper.TypeId;
import ch.xcrux.schlepper.types.composite.CompositeFacadeFactory;
import ch.xcrux.schlepper.types.int32.Int32Factory;
import com.sun.istack.internal.Nullable;

import java.util.HashMap;
import java.util.Map;

/**
 * Buran.
 *
 * @author: ${USER} Date: 27.04.13 Time: 15:50
 */
public class TypeRegistry {
    private final Map<TypeId, IFacadeFactory<?, ?, ?>> types = new HashMap<>();

    private void add(IFacadeFactory<?, ?, ?> factory) {
        this.types.put(factory.getTypeId(), factory);
    }

    public void fill() {
       add(new CompositeFacadeFactory());
        add(new Int32Factory());
    }

    @Nullable
    public IFacadeFactory<?, ?, ?> getFacade(TypeId typeId) {
        return this.types.get(typeId);
    }
}
