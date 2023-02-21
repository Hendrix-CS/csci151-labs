package maze.searchers;

public class ArrayStack<E> implements Stack<E> {

	private int top;
	private E[] stuff;

	public ArrayStack() {
		this(8);
	}

	public ArrayStack(int initialSize) {
		// THIS METHOD IS CORRECT, DO NOT TOUCH
		stuff = (E[])(new Object[initialSize]);
		top = 0;
	}

	@Override
	public void push(E e) {
		// TODO Step 1 Implement ArrayStack
	}

	@Override
	public E pop() {
		// TODO Step 1 Implement ArrayStack
		return null;
	}

	@Override
	public E peek() {
		// TODO Step 1 Implement ArrayStack

		emptyCheck();

		return null;
	}

	@Override
	public int size() {
		// TODO Step 1 Implement ArrayStack
		return 0;
	}

	@Override
	public String toString() {
		// TODO Step 1 Implement ArrayStack
		return "";
	}

	@Override
	public int capacity() {
		return stuff.length;
	}
}
