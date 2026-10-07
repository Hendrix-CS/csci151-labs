package maze.searchers;

public class WrappedStack<T> implements Searcher<T> {
	private Stack<T> stack;
	
	public WrappedStack(Stack<T> stack) {
		this.stack = stack;
	}

	@Override
	public int size() {
		return stack.size();
	}

	@Override
	public int capacity() {
		return stack.capacity();
	}

	@Override
	public T remove() {
		return stack.pop();
	}

	@Override
	public void add(T data) {
		stack.push(data);
	}

	@Override
	public T element() {
		return stack.peek();
	}

}
