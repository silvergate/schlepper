package ch.xcrux.schlepper.impl;

import ch.xcrux.schlepper.DataId;
import ch.xcrux.schlepper.IChange;
import ch.xcrux.schlepper.IStoreChange;
import ch.xcrux.schlepper.ResultIndex;
import com.sun.istack.internal.Nullable;

/**
 * Buran.
 *
 * @author: ${USER} Date: 30.04.13 Time: 00:42
 */
public class ProcessedTaskItem {
    private final ResultIndex resultIndex;
    @Nullable
    private final DataId dataId;
    @Nullable
    private final IStoreChange<?> storeChange;
    @Nullable
    private final IChange<?, ?, ?> change;

    private ProcessedTaskItem(ResultIndex resultIndex, DataId dataId, IStoreChange<?> storeChange,
            IChange<?, ?, ?> change) {
        this.resultIndex = resultIndex;
        this.dataId = dataId;
        this.storeChange = storeChange;
        this.change = change;
    }

    public static ProcessedTaskItem change(ResultIndex resultIndex, DataId dataId,
            IChange<?, ?, ?> change) {
        return new ProcessedTaskItem(resultIndex, dataId, null, change);
    }

    public static ProcessedTaskItem change(ResultIndex resultIndex, IStoreChange change) {
        return new ProcessedTaskItem(resultIndex, null, change, null);
    }

    public ResultIndex getResultIndex() {
        return resultIndex;
    }

    @Nullable
    public DataId getDataId() {
        return dataId;
    }

    @Nullable
    public IStoreChange<?> getStoreChange() {
        return storeChange;
    }

    @Nullable
    public IChange<?, ?, ?> getChange() {
        return change;
    }
}
