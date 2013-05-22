package ch.dcrux.newSchlepper.dataStore;

/**
 * Buran.
 *
 * @author: ${USER} Date: 22.05.13 Time: 22:41
 */
public class Alt3<T1, T2, T3> {
    private final Object value;
    private final int index;

    protected Alt3(Object value, int index) {
        if ((index < 0) || (index > 2))
            throw new IllegalArgumentException("((index<0) || (index>2))");
        this.value = value;
        this.index = index;
    }

    public static <Tl1 extends Object> Alt3<Tl1, ?, ?> one(Tl1 value) {
        return new Alt3(value, 0);
    }

    public static <Tl2 extends Object> Alt3<?, Tl2, ?> two(Tl2 value) {
        return new Alt3(value, 1);
    }

    public static <Tl3 extends Object> Alt3<?, ?, Tl3> three(Tl3 value) {
        return new Alt3(value, 2);
    }

    public T1 getOne() {
        return (T1) this.value;
    }

    public T1 getTwo() {
        return (T1) this.value;
    }

    public T1 getThree() {
        return (T1) this.value;
    }

    public boolean isOne() {
        return this.index == 0;
    }

    public boolean isTwo() {
        return this.index == 1;
    }

    public boolean isThree() {
        return this.index == 2;
    }
}
