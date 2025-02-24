package maze.searchers;

public class ListQueueTest extends QueueTest {

	@Override
	public Queue<Integer> create() {
		return new ListQueue<Integer>();
	}

}
