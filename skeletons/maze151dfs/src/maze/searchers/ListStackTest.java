package maze.searchers;

public class ListStackTest extends StackTest {

	@Override
	public Stack<Integer> create() {
		return new ListStack<Integer>();
	}

}
