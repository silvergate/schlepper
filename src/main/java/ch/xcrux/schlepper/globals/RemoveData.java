package ch.xcrux.schlepper.globals;

import ch.xcrux.schlepper.DataId;
import ch.xcrux.schlepper.changes.ChangeInfo;
import ch.xcrux.schlepper.changes.IChange;
import ch.xcrux.schlepper.changes.IRollbackableChange;
import ch.xcrux.schlepper.changes.IStoreInts;
import ch.xcrux.schlepper.meta.IMetadata;
import com.sun.istack.internal.Nullable;

/**
 * Buran.
 *
 * @author: ${USER} Date: 27.04.13 Time: 13:48
 */
public class RemoveData implements IChange<Object, IMetadata, Void> {

    @Override
    public IRollbackableChange<Object, IMetadata, Void> createChange() {
        return new IRollbackableChange<Object, IMetadata, Void>() {
            @Override
            public ChangeInfo<Object, Void> perform(DataId dataId, IMetadata metadata,
                    @Nullable Object currentData, IStoreInts storeInts) {
                return null;  //To change body of implemented methods use File | Settings | File
                // Templates.
            }
        };
    }
}
