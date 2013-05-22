package ch.dcrux.newSchlepper.dataStore;

/**
 * Buran.
 *
 * @author: ${USER} Date: 22.05.13 Time: 22:41
 */
public class Alt2<T1, T2> {
    private final Object value;
    private final int index;

    protected Alt2(Object value, int index) {
        if ((index < 0) || (index > 1))
            throw new IllegalArgumentException("((index<0) || (index>1))");
        this.value = value;
        this.index = index;
    }

    public static <Tl1 extends Object> Alt2<Tl1, ?> one(Tl1 value) {
        return new Alt2(value, 0);
    }

    public static <Tl2 extends Object> Alt2<?, Tl2> two(Tl2 value) {
        return new Alt2(value, 1);
    }

    public T1 getOne() {
        return (T1) this.value;
    }

    public T2 getTwo() {
        return (T2) this.value;
    }

    public boolean isOne() {
        return this.index == 0;
    }

    public boolean isTwo() {
        return this.index == 1;
    }
}
