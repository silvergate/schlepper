package ch.dcrux.newSchlepper.impl.dataStore;

import ch.dcrux.newSchlepper.dataStore.DataOrUid;
import ch.dcrux.newSchlepper.dataStore.Uid;
import ch.dcrux.newSchlepper.impl.dataStore.javaSpecific.IData;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

/**
 * Buran.
 *
 * @author: ${USER} Date: 22.05.13 Time: 19:32
 */
public class DataStore {
    private final Map<Integer, IData> data = new HashMap<>();
    private final Map<Uid, IData> uidData = new HashMap<>();

    public Map<Integer, IData> getData() {
        return data;
    }

    public Map<Uid, IData> getUidData() {
        return uidData;
    }

    @Nullable
    public IData getData(DataOrUid dataOrUid) {
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
    public IData put(DataOrUid dataOrUid, IData data) {
        if (dataOrUid.isUid()) {
            return this.uidData.put(dataOrUid.getUid(), data);
        } else {
            return this.data.put(dataOrUid.getDataId().getId(), data);
        }
    }

    @Nullable
    public IData remove(DataOrUid dataOrUid) {
        if (dataOrUid.isUid()) {
            return this.uidData.remove(dataOrUid.getUid());
        } else {
            return this.data.remove(dataOrUid.getDataId().getId());
        }
    }

}
