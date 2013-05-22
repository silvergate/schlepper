package ch.dcrux.newSchlepper.commandProcessor.impl;

import ch.dcrux.newSchlepper.commandProcessor.CommandId;
import ch.dcrux.newSchlepper.commandProcessor.IRollbackStorageGet;
import ch.dcrux.newSchlepper.commandProcessor.IRollbackStoragePut;

import java.util.HashMap;
import java.util.Map;

/**
 * Buran.
 *
 * @author: ${USER} Date: 22.05.13 Time: 18:49
 */
public class RollbackStorage implements IRollbackStorageGet, IRollbackStoragePut {

    private final Map<CommandId, Object> storageData = new HashMap<>();
    private CommandId currentCommandId;

    public void setCurrentCommandId(CommandId currentCommandId) {
        this.currentCommandId = currentCommandId;
    }

    @Override
    public Object get() {
        if (this.currentCommandId == null) {
            throw new IllegalStateException("this.currentCommandId==null");
        }
        return this.storageData.get(this.currentCommandId);
    }

    @Override
    public void store(Object value) {
        if (this.currentCommandId == null) {
            throw new IllegalStateException("this.currentCommandId==null");
        }
        this.storageData.put(this.currentCommandId, value);
    }
}
