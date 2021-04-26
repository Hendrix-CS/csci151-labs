package maze.searchers;

public class ArrayStackTest extends StackTest {

	@Override
	public Stack<Integer> create() {
		return new ArrayStack<Integer>();
	}

}
