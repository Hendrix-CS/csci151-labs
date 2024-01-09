package sorting.algorithms;

import java.util.ArrayList;

public class Setting<E> {
    private final E value;
    private final int where;

    public Setting(int i, E e) {
        value = e;
        where = i;
    }

    public void set(ArrayList<E> array) {
        array.set(where, value);
    }

    public int getWhere() {return where;}
    public E getValue() {return value;}
}
