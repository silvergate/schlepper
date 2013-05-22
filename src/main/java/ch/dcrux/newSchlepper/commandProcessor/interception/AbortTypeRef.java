package ch.dcrux.newSchlepper.commandProcessor.interception;

/**
 * Buran.
 *
 * @author: ${USER} Date: 22.05.13 Time: 09:27
 */
public class AbortTypeRef {
    private final String typeRef;

    public AbortTypeRef(String typeRef) {
        this.typeRef = typeRef;
    }

    public String getTypeRef() {
        return typeRef;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbortTypeRef that = (AbortTypeRef) o;

        if (typeRef != null ? !typeRef.equals(that.typeRef) : that.typeRef != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return typeRef != null ? typeRef.hashCode() : 0;
    }
}
