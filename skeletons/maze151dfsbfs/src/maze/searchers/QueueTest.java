package maze.searchers;

import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.*;

abstract public class QueueTest {

	public static final int NUM_ITEMS = 100;

	abstract public Queue<Integer> create();

	Queue<Integer> queue = create();
	
	public void checkEmpty() {
		assertTrue(queue.isEmpty());
		assertEquals(0, queue.size());
	}

	@Test(expected=IllegalStateException.class)
	public void testEmpty1() {
		checkEmpty();
		queue.element();
	}

	@Test(expected=IllegalStateException.class)
	public void testEmpty2() {
		checkEmpty();
		queue.remove();
	}

	@Test
	public void testAdd() {
		queue.add(55);
		assertFalse(queue.isEmpty());
		assertEquals(55, (int)queue.element());
		assertEquals(1, queue.size());
		
		queue.add(44);
		assertEquals(55, (int)queue.element());
		assertEquals(2, queue.size());
	}
	
	@Test
	public void testRemove() {
		testAdd();
		assertEquals(55, (int)queue.remove());
		assertEquals(1, queue.size());
		assertEquals(44, (int)queue.element());
		assertEquals(44, (int)queue.remove());
		assertEquals(0, queue.size());
	}

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
	public void testWrapAround() {
		// Add items
		for (int i = 0; i < 17; i++) {
			queue.add(i);
		}

		// Spin them around
		for (int i = 0; i < 101; i++) {
			queue.add(queue.remove());
		}

		// Add more items
		int first = queue.element();
		for (int i = 0; i < NUM_ITEMS; i++) {
			queue.add(i + 17);
		}

		// Did the resize work?
		assertEquals(Integer.valueOf(first), queue.element());
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
