package maze.searchers;

public class ArrayStack<E> implements Stack<E> {

	private int top;
	private E[] stuff;

	public ArrayStack() {
		this(8);
	}

	public ArrayStack(int initialSize) {
		top = 0;
		stuff = (E[])(new Object[initialSize]);
	}

	@Override
	public int size() {
		return top;
	}

	@Override
	public E pop() {
		emptyCheck();
		top--;
		E temp = stuff[top];
		return temp;
	}

	@Override
	public E peek() {
		emptyCheck();
		return stuff[top - 1];
	}

	@Override
	public void push(E e) {
		resize();
		stuff[top] = e;
		top++;
	}

	private void resize() {
		if (top == stuff.length) {
			E[] stuff2 = (E[])(new Object[stuff.length * 2]);
			for (int i = 0; i < stuff.length; i++) {
				stuff2[i] = stuff[i];
			}
			stuff = stuff2;
		}
	}

	@Override
	public String toString() {
		String s = "";
		for (int i = 0; i < top; i++) {
			s += stuff[i] + " ";
		}
		return s;
	}

	@Override
	public int capacity() {
		return stuff.length;
	}
}
