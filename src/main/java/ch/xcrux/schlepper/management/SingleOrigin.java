package ch.xcrux.schlepper.management;

import ch.xcrux.schlepper.DataId;
import ch.xcrux.schlepper.Origin;
import com.google.common.base.Optional;

/**
 * Buran.
 *
 * @author: ${USER} Date: 04.05.13 Time: 15:20
 */
public class SingleOrigin {
    private final Optional<Origin> origin;
    private final Optional<DataId> dataOrigin;

    public SingleOrigin(Optional<Origin> origin, Optional<DataId> dataOrigin) {
        this.origin = origin;
        this.dataOrigin = dataOrigin;
    }
}
