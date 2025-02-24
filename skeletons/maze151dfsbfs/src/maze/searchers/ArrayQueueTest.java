package maze.searchers;

public class ArrayQueueTest extends QueueTest {

	@Override
	public Queue<Integer> create() {
		return new ArrayQueue<Integer>();
	}

}
