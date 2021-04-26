package maze.searchers;

public interface Queue<E>{
	public int size();

	public int capacity();

	public E remove();

	public void add(E data);

	public E element();

	default public boolean isEmpty() {
		return size() == 0;
	}

	// Assertion
	default public void emptyCheck() {
		if (isEmpty()) {
			throw new IllegalStateException("Cannot access empty queue");
		}
	}
}
