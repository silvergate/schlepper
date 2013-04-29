package ch.xcrux.schlepper.impl;

import ch.xcrux.schlepper.IFactory;
import ch.xcrux.schlepper.TypeId;
import ch.xcrux.schlepper.types.composite.CompositeFactory;
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
    private final Map<TypeId, IFactory<?, ?, ?>> types = new HashMap<>();

    private void add(IFactory<?, ?, ?> factory) {
        this.types.put(factory.getTypeId(), factory);
    }

    public void fill() {
        add(new CompositeFactory());
        add(new Int32Factory());
    }

    @Nullable
    public IFactory<?, ?, ?> getFacade(TypeId typeId) {
        return this.types.get(typeId);
    }
}
