package ch.dcrux.newSchlepper.dataStore;

/**
 * Buran.
 *
 * @author: ${USER} Date: 01.05.13 Time: 08:13
 */
public class DataOrUid {
    private final DataId dataId;
    private final Uid uid;

    protected DataOrUid(DataId dataId, Uid uid) {
        this.dataId = dataId;
        this.uid = uid;
    }

    public static DataOrUid data(DataId dataId) {
        return new DataOrUid(dataId, null);
    }

    public static DataOrUid data(Uid uid) {
        return new DataOrUid(null, uid);
    }

    public DataId getDataId() {
        return dataId;
    }

    public Uid getUid() {
        return uid;
    }

    public boolean isUid() {
        return this.uid != null;
    }
}
