package maze.searchers;

public interface Stack<E> {
	public int size();

	public int capacity();

	public E pop();

	public void push(E data);

	public E peek();

	default public boolean isEmpty() {
		return size() == 0;
	}

	// Assertion
	default public void emptyCheck() {
		if (isEmpty()) {
			throw new IllegalStateException("Cannot access from empty stack");
		}
	}
}
