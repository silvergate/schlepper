package ch.dcrux.newSchlepper.dataStore.impl;

import ch.dcrux.newSchlepper.dataStore.DataId;
import ch.dcrux.newSchlepper.dataStore.DataOrUid;
import ch.dcrux.newSchlepper.dataStore.Uid;
import ch.dcrux.newSchlepper.dataStore.typeSystem.IMetadata;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Buran.
 *
 * @author: ${USER} Date: 22.05.13 Time: 19:32
 */
public class MetaStore {
    private final Map<Integer, IMetadata> data = new HashMap<>();
    private final Map<Uid, IMetadata> uidData = new HashMap<>();

    private Random random = new Random();

    @Nullable
    public IMetadata getMeta(DataOrUid dataOrUid) {
        if (dataOrUid.isUid()) {
            return this.uidData.get(dataOrUid.getUid());
        } else {
            return this.data.get(dataOrUid.getDataId().getId());
        }
    }

    @Nullable
    public IMetadata put(DataOrUid dataOrUid, IMetadata metadata) {
        if (dataOrUid.isUid()) {
            return this.uidData.put(dataOrUid.getUid(), metadata);
        } else {
            return this.data.put(dataOrUid.getDataId().getId(), metadata);
        }
    }

    @Nullable
    public IMetadata remove(DataOrUid dataOrUid) {
        if (dataOrUid.isUid()) {
            return this.uidData.remove(dataOrUid.getUid());
        } else {
            return this.data.remove(dataOrUid.getDataId().getId());
        }
    }

    public DataId createNewDataId() {
        int currentDid;
        do {
            currentDid = this.random.nextInt();
        } while (this.data.containsKey(currentDid));
        return new DataId(currentDid);
    }
}
