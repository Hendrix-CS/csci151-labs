package sorting.algorithms;

import java.util.ArrayList;

public class GnomeSorter<E extends Comparable<E>> extends Sorter<E> {

    @Override
    protected void sortAlgorithm(ArrayList<E> array) {
        int i = 0;
        while (i < array.size() - 1) {
            if (array.get(i).compareTo(array.get(i+1)) > 0) {
                E temp = array.get(i);
                set(array, i, array.get(i+1));
                set(array, i+1, temp);
                i = Math.max(0, i-1);
            } else {
                i += 1;
            }
        }
    }
}
