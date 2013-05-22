package ch.dcrux.newSchlepper.dataStore.impl;

import ch.dcrux.newSchlepper.dataStore.DataOrUid;
import ch.dcrux.newSchlepper.dataStore.Uid;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

/**
 * Buran.
 *
 * @author: ${USER} Date: 22.05.13 Time: 19:32
 */
public class DataStore {
    private final Map<Integer, Object> data = new HashMap<>();
    private final Map<Uid, Object> uidData = new HashMap<>();

    public Map<Integer, Object> getData() {
        return data;
    }

    public Map<Uid, Object> getUidData() {
        return uidData;
    }

    @Nullable
    public Object getData(DataOrUid dataOrUid) {
        if (dataOrUid.isUid()) {
            return this.uidData.get(dataOrUid.getUid());
        } else {
            return this.data.get(dataOrUid.getDataId().getId());
        }
    }

    public boolean hasData(DataOrUid dataOrUid) {
        if (dataOrUid.isUid()) {
            return this.uidData.containsKey(dataOrUid.getUid());
        } else {
            return this.data.containsKey(dataOrUid.getDataId().getId());
        }
    }

    @Nullable
    public Object put(DataOrUid dataOrUid, Object data) {
        if (dataOrUid.isUid()) {
            return this.uidData.put(dataOrUid.getUid(), data);
        } else {
            return this.data.put(dataOrUid.getDataId().getId(), data);
        }
    }

    @Nullable
    public Object remove(DataOrUid dataOrUid) {
        if (dataOrUid.isUid()) {
            return this.uidData.remove(dataOrUid.getUid());
        } else {
            return this.data.remove(dataOrUid.getDataId().getId());
        }
    }

}
