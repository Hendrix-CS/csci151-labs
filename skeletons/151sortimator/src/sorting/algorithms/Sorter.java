package sorting.algorithms;

import java.util.ArrayList;
import java.util.Iterator;

abstract public class Sorter<E extends Comparable<E>> implements Iterable<Setting<E>> {
    private ArrayList<Setting<E>> settings = new ArrayList<>();

    public void set(ArrayList<E> array, int i, E value) {
        settings.add(new Setting<E>(i, value));
        array.set(i, value);
    }

    @Override
    public Iterator<Setting<E>> iterator() {return settings.iterator();}

    public void sort(ArrayList<E> array) {
        settings = new ArrayList<>();
        sortAlgorithm(array);
    }

    abstract protected void sortAlgorithm(ArrayList<E> array);

    @Override
    public String toString() {
        String name = getClass().getName();
        return name.substring(name.lastIndexOf('.') + 1);
    }
}
