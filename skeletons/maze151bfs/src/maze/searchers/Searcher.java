package maze.searchers;

public interface Searcher<E> {
    default public boolean isEmpty() {return size() == 0;}

    public int size();

    public int capacity();

    public E remove();

    public void add(E data);

    public E element();

    // Assertion
    default public void emptyCheck() {
        if (isEmpty()) {
            throw new IllegalStateException("Cannot access from empty searcher");
        }
    }
}
