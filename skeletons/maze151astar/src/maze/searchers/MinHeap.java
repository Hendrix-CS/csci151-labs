package maze.searchers;

import java.util.ArrayList;
import java.util.Comparator;

public class MinHeap<E> implements Searcher<E> {
    private ArrayList<E> stuff;
    private Comparator<E> comparator;

    public MinHeap(Comparator<E> comparator) {
        stuff = new ArrayList<>();
        this.comparator = comparator;
    }

    public int size() {
        return stuff.size();
    }

    public int capacity() {
        return stuff.size();
    }

    // Exists only for unit testing purposes.
    void backdoorAdd(E value) {
        stuff.add(value);
    }

    // Exists only for unit testing purposes.
    E get(int i) {
        return stuff.get(i);
    }

    /**
     * Given an index, return the index of its parent.
     */
    static int parent(int i) {
        // TODO Step 1 Implement the correct calculation.
        return -1;
    }

    /**
     * Given an index, return the index of its left child.
     */
    static int left(int i) {
        // TODO Step 1 Implement the correct calculation.
        return -1;
    }

    /**
     * Given an index, return the index of its right child.
     */
    static int right(int i) {
        // TODO Step 1 Implement the correct calculation.
        return -1;
    }

    /**
     * Given an index, return true if it is a legal array index, and false otherwise.
     */
    boolean legal(int i) {
        // TODO Step 1 Implement the correct calculation.
        return false;
    }

    /**
     * Verify that each element is smaller than its children elements.
     * Because of our use of an ArrayList to track the elements, we
     * will always assume that the elements form a Heap that is as
     * compact as possible.
     *
     * Your solution can be simplified by using the legal(), left(), and right()
     * methods.
     */
    boolean isHeap() {
        // TODO Step 2 Implement the correct calculation.
        return false;
    }

    /**
     * Swaps the values at indices i and j.
     */
    void swap(int i, int j) {
        // TODO Step 3 Implement swapping.
    }

    /**
     * Return the root element, which will always be stored in
     * the first position of the ArrayList.
     */
    public E element() {
        // TODO Step 4 Return the correct value.
        return null;
    }

    /**
     * New elements are added to the end of the ArrayList, and then
     * filtered up repeatedly when the element is found to be less
     * than its parent. Use the Comparator object to calculate if an
     * element is greater or less than another element.
     *
     * Your solution can be simplified by using the swap() and parent() methods.
     */
    public void add(E data) {
        // TODO Step 4 implement add
    }

    /**
     * Given the index of a parent, this method checks the value at that index
     * against the values of its children. If either child is smaller than the
     * parent, the index of the smallest child is returned. If no children are
     * present, or the parent has the lowest value among the three, the parent's
     * own index is returned.
     *
     * Your solution can be simplified by using the legal(), left(),
     * and right() methods.
     */
    int indexOfLowestInFamily(int parent) {
        // TODO Step 5 Implement solution
        return -1;
    }

    /**
     * First, swap the first and last elements. Next, remove the last element,
     * and save its value to be returned at the end of the method. This could
     * cause a violation of the Heap property that all parents must
     * be smaller than their children. If the element is less than
     * only one of its children, swap these two elements. If the element
     * is less than both of its children, swap the smaller of the two
     * children with the element, so that we don't break the Heap
     * property any further. Finally, when a swap was made, repeatedly
     * check the subsequent descendants to guarantee that the Heap
     * property is always preserved.
     *
     * Your solution can be simplified by using the indexOfLowestInFamily()
     * and swap() methods.
     */
    public E remove() {
        // TODO Step 6 Implement remove().
        return null;
    }
}
