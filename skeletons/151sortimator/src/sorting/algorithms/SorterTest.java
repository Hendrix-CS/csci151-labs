package sorting.algorithms;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import sorting.gui.ClassFinder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.function.*;

public class SorterTest {

    ArrayList<Supplier<Sorter<Integer>>> sorters;

    @Before
    public void setup() {
        ClassFinder<Sorter<Integer>> sorterFinder = new ClassFinder<>(Sorter.class, "sorting.algorithms");
        sorters = new ArrayList<>();
        for (String name : sorterFinder.getTypeNames()) {
            sorters.add(() -> {
                try {
                    return sorterFinder.newInstanceOf(name);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    return null;
                }
            });
        }
    }

    public ArrayList<Integer> generateScrambled(int range, int duplicates) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < range; i++) {
            for (int j = 0; j < duplicates; j++) {
                result.add(i);
            }
        }
        Collections.shuffle(result);
        return result;
    }

    public boolean isSorted(ArrayList<Integer> target) {
        for (int i = 1; i < target.size(); i++) {
            if (target.get(i) < target.get(i - 1)) {
                return false;
            }
        }
        return true;
    }

    public void testSorter(Sorter<Integer> sorter, ArrayList<Integer> target) {
        ArrayList<Integer> copy = copy(target);
        sorter.sortAlgorithm(copy);
        ArrayList<Integer> sortedcopy = copy(copy);
        boolean match = true;
        for (Integer i : target) {
            if (!sortedcopy.remove(i)) {
                match = false;
            }
        }
        if (sortedcopy.size() > 0) {
            match = false;
        }
        if (!match) {
            System.out.println("Algorithm: " + sorter.getClass().getName());
            System.out.print("Original: ");
            show(target);
            System.out.print("Failure:  ");
            show(copy);
            fail("Sorted is different than original.");
        }
        if (!isSorted(copy)) {
            System.out.println("Algorithm: " + sorter.getClass().getName());
            System.out.print("Original: ");
            show(target);
            System.out.print("Failure:  ");
            show(copy);
            fail("Failed to sort");
        }
        testSettings(sorter, target);
    }

    public void testSettings(Sorter<Integer> sorter, ArrayList<Integer> target) {
        ArrayList<Integer> copy = copy(target);
        for (Setting<Integer> s : sorter) {
            s.set(copy);
        }
        assertTrue(isSorted(copy));
    }

    public static <T extends Comparable<T>> void show(ArrayList<T> target) {
        for (T t : target) {
            System.out.print(t + " ");
        }
        System.out.println();
    }

    public void testAll(ArrayList<Integer> target) {
        for (Supplier<Sorter<Integer>> supplier : sorters) {
            Sorter<Integer> sorter = supplier.get();
            testSorter(sorter, target);
        }
    }

    public static ArrayList<Integer> copy(ArrayList<Integer> src) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        result.addAll(src);
        return result;
    }

    @Test
    public void test1() {
        ArrayList<Integer> target = generateScrambled(10, 1);
        testAll(target);
    }

    @Test
    public void test2() {
        ArrayList<Integer> target = generateScrambled(10, 2);
        testAll(target);
    }

    @Test
    public void test3() {
        ArrayList<Integer> target = generateScrambled(30, 3);
        testAll(target);
    }
}
