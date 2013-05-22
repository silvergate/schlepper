package ch.dcrux.newSchlepper.dataStore;

import java.util.UUID;

/**
 * Buran.
 *
 * @author: ${USER} Date: 30.04.13 Time: 01:38
 */
public class Uid {
    private final UUID uid;

    public Uid(UUID uid) {
        this.uid = uid;
    }

    public UUID getUid() {
        return uid;
    }

    public static Uid c(String uid) {
        return new Uid(UUID.fromString(uid));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Uid uid = (Uid) o;

        if (!this.uid.equals(uid.uid)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return uid.hashCode();
    }
}
