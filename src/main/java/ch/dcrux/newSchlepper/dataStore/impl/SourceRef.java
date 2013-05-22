package ch.dcrux.newSchlepper.dataStore.impl;

import ch.dcrux.newSchlepper.commandProcessor.ISourceRef;

/**
 * Buran.
 *
 * @author: ${USER} Date: 22.05.13 Time: 21:56
 */
public class SourceRef implements ISourceRef {
    private final String id;

    public SourceRef(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SourceRef sourceRef = (SourceRef) o;

        if (id != null ? !id.equals(sourceRef.id) : sourceRef.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
