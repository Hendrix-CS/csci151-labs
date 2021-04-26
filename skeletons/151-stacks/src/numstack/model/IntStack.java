package numstack.model;

public interface IntStack {

	public int size();

	public int pop();

	public void push(int e);

	public int peek();

	default public boolean isEmpty() {
		return size() == 0;
	}

	default public void emptyCheck() {
		if (isEmpty()) {
			throw new IllegalStateException("Cannot pop empty stack");
		}
	}
}
