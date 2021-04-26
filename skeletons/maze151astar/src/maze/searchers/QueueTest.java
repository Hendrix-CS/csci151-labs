package maze.searchers;

import static org.junit.Assert.*;

import java.util.HashSet;
import org.junit.Test;

abstract public class QueueTest {

	public static final int NUM_ITEMS = 100;

	abstract public Queue<Integer> create();

	Queue<Integer> queue = create();

	@Test
	public void testFIFO() {
		for (int i = 0; i < NUM_ITEMS; i++) {
			queue.add(i);
			assertEquals(Integer.valueOf(0), queue.element());
		}

		for (int i = 0; i < NUM_ITEMS; i++) {
			assertEquals(Integer.valueOf(i), queue.remove());
			if (i < NUM_ITEMS - 1) {
				assertEquals(Integer.valueOf(i+1), queue.element());
			} else {
				assertEquals(0, queue.size());
			}
		}
	}

	@Test
	public void testRetrieval() {
		assertTrue(queue.isEmpty());
		HashSet<Integer> wentIn = new HashSet<>();
		for (int i = 0; i < 10000; i++) {
			wentIn.add(i);
			queue.add(i);
		}
		assertFalse(queue.isEmpty());

		while (!queue.isEmpty()) {
			int peeked = queue.element();
			assertTrue(wentIn.contains(peeked));
			int oldSize = queue.size();
			int value = queue.remove();
			assertEquals(oldSize - 1, queue.size());
			assertEquals(peeked, value);
			wentIn.remove(value);
		}
	}

	@Test
	public void testCapacity() {
		int capacityTest = 100;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < capacityTest; j++) {
				queue.add(j);
			}
			while (!queue.isEmpty()) {
				queue.remove();
			}
			assertTrue(queue.capacity() <= 2 * capacityTest);
		}
	}
}
