package ch.dcrux.newSchlepper.dataStore.typeSystem;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Buran.
 *
 * @author: ${USER} Date: 28.05.13 Time: 17:47
 */
public class AltConstraints {
    private final Collection<IMetaTypeAndConstraint> constraints = new ArrayList<>();

    public Collection<IMetaTypeAndConstraint> getConstraints() {
        return constraints;
    }
}
