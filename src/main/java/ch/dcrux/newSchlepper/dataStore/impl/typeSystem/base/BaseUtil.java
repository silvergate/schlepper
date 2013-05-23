package ch.dcrux.newSchlepper.dataStore.impl.typeSystem.base;

import ch.dcrux.newSchlepper.commandProcessor.CommandException;
import ch.dcrux.newSchlepper.commandProcessor.ResultList;
import ch.dcrux.newSchlepper.dataStore.DataId;
import ch.dcrux.newSchlepper.dataStore.DataOrUid;
import ch.dcrux.newSchlepper.dataStore.DataTarget;
import ch.dcrux.newSchlepper.dataStore.Uid;
import ch.dcrux.newSchlepper.dataStore.impl.Store;
import ch.dcrux.newSchlepper.dataStore.typeSystem.IMetadata;
import org.jetbrains.annotations.Nullable;

/**
 * Buran.
 *
 * @author: ${USER} Date: 22.05.13 Time: 23:34
 */
public class BaseUtil {
    public static DataOrUid getDataOrUid(ResultList resultList, DataTarget target)
            throws CommandException {
        if (target.isOne()) {
            return target.getOne();
        } else {
            /* Need to get it from list */
            final Uid uid = resultList.getOrNull(target.getTwo(), Uid.class);
            if (uid != null) {
                return DataOrUid.data(uid);
            }
            final DataId dataId = resultList.getOrNull(target.getTwo(), DataId.class);
            if (dataId != null) {
                return DataOrUid.data(dataId);
            }
            final DataOrUid dataOrUid = resultList.getOrNull(target.getTwo(), DataOrUid.class);
            if (dataOrUid != null) {
                return dataOrUid;
            }
            //TODO: Eigene Exception hinzufügen!
            throw new CommandException();
        }
    }

    @Nullable
    public static <T extends IMetadata> T getMetadataOrNull(Store store, ResultList resultList,
            DataTarget target, Class<T> type) throws CommandException {
        final DataOrUid dataOrUid = getDataOrUid(resultList, target);
        final IMetadata metadata = store.getMetaStore().getMeta(dataOrUid);
        if (metadata == null) {
            return null;
        }
        if (!metadata.getClass().isAssignableFrom(type)) {
            //TODO: Exception: Wrong type
            throw new CommandException();
        }
        return (T) metadata;
    }
}
