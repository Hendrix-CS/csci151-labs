package maze.searchers;

public class ArrayQueue<E> implements Queue<E> {
    private E[] stuff;
    // TODO Step 2.0
    //  Add up to 3 additional ints to represent the entry and exit points and/or size.

    public ArrayQueue() {
        stuff = (E[])(new Object[8]);
    }

    @Override
    public void add(E data) {
        // TODO Step 2.1
    }

    @Override
    public E remove() {
        // TODO Step 2.2
        return null;
    }

    @Override
    public E element() {
        // TODO Step 2.3
        return null;
    }

    @Override
    public int size() {
        // TODO Step 2.4
        return 0;
    }

    @Override
    public int capacity() {
        return stuff.length;
    }

}
