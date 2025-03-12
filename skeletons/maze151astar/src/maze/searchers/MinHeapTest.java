package maze.searchers;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

import static org.junit.Assert.*;

public class MinHeapTest {

	public final static int NUM_ITEMS = 1000;

	private Comparator<Integer> c = Integer::compareTo;

	private MinHeap<Integer> heap;

	@Before
	public void setup() {
		heap = new MinHeap<>(c);
	}

	@Test
	public void testParent() {
		assertEquals(0, MinHeap.parent(1));
		assertEquals(0, MinHeap.parent(2));
		assertEquals(1, MinHeap.parent(3));
		assertEquals(1, MinHeap.parent(4));
		assertEquals(2, MinHeap.parent(5));
		assertEquals(2, MinHeap.parent(6));
		assertEquals(3, MinHeap.parent(7));
		assertEquals(3, MinHeap.parent(8));
	}

	@Test
	public void testLeft() {
		assertEquals(1, MinHeap.left(0));
		assertEquals(3, MinHeap.left(1));
		assertEquals(5, MinHeap.left(2));
		assertEquals(7, MinHeap.left(3));
		assertEquals(9, MinHeap.left(4));
		assertEquals(11, MinHeap.left(5));
		assertEquals(13, MinHeap.left(6));
	}

	@Test
	public void testRight() {
		assertEquals(2, MinHeap.right(0));
		assertEquals(4, MinHeap.right(1));
		assertEquals(6, MinHeap.right(2));
		assertEquals(8, MinHeap.right(3));
		assertEquals(10, MinHeap.right(4));
		assertEquals(12, MinHeap.right(5));
		assertEquals(14, MinHeap.right(6));
	}

	@Test
	public void testLegal() {
		assertFalse(heap.legal(-1));
		assertFalse(heap.legal(0));
		for (int i = 0; i < 10; i++) {
			heap.backdoorAdd(i);
			assertTrue(heap.legal(i));
			assertFalse(heap.legal(i+1));
		}
	}

	@Test
	public void testIsHeap1() {
		assertTrue(heap.isHeap());
		for (int i = 0; i < 10; i++) {
			heap.backdoorAdd(i);
			assertTrue(heap.isHeap());
		}
	}

	@Test
	public void testIsHeap2() {
		assertTrue(heap.isHeap());
		heap.backdoorAdd(2);
		heap.backdoorAdd(1);
		assertFalse(heap.isHeap());
	}

	@Test
	public void testIsHeap3() {
		assertTrue(heap.isHeap());
		heap.backdoorAdd(1);
		heap.backdoorAdd(2);
		heap.backdoorAdd(0);
		assertFalse(heap.isHeap());
	}

	@Test
	public void testIsHeap4() {
		assertTrue(heap.isHeap());
		heap.backdoorAdd(0);
		heap.backdoorAdd(3);
		heap.backdoorAdd(1);
		heap.backdoorAdd(2);
		assertFalse(heap.isHeap());
	}

	@Test
	public void testSwap() {
		heap.backdoorAdd(10);
		heap.backdoorAdd(20);
		heap.backdoorAdd(30);
		heap.swap(0, 2);
		assertEquals(30, (int) heap.get(0));
		assertEquals(20, (int) heap.get(1));
		assertEquals(10, (int) heap.get(2));

		heap.swap(1, 2);
		assertEquals(30, (int) heap.get(0));
		assertEquals(10, (int) heap.get(1));
		assertEquals(20, (int) heap.get(2));
	}

	@Test
	public void testAdd1() {
		int[] adds = new int[]{10, 9, 11};
		int[] peeks = new int[]{10, 9, 9};
		assertEquals(adds.length, peeks.length);

		for (int i = 0; i < adds.length; i++) {
			heap.add(adds[i]);
			assertEquals(i + 1, heap.size());
			assertEquals(peeks[i], (int) heap.element());
			assertTrue(heap.isHeap());
			allPresentInHeap(adds, i);
		}
	}

	public void allPresentInHeap(int[] values, int limit) {
		for (int i = 0; i <= limit; i++) {
			assertTrue(presentInHeap(values[i]));
		}
	}

	public boolean presentInHeap(int value) {
		for (int i = 0; i < heap.size(); i++) {
			if (value == heap.get(i)) {
				return true;
			}
		}
		return false;
	}

	@Test
	public void testAdd2() {
		Random rand = new Random();
		for (int i = 0; i < NUM_ITEMS; i++) {
			int oldSize = heap.size();
			int adding = rand.nextInt();
			heap.add(adding);
			assertTrue(heap.isHeap());
			assertEquals(oldSize + 1, heap.size());
		}
	}

	@Test
	public void testSmallestChild1() {
		assertTrue(heap.isHeap());
		heap.backdoorAdd(0);
		heap.backdoorAdd(1);
		heap.backdoorAdd(2);
		assertTrue(heap.isHeap());
		assertFalse(heap.hasSmallerChild(0));
		assertEquals(1, heap.smallestChildOf(0));
	}

	@Test
	public void testSmallestChild2() {
		assertTrue(heap.isHeap());
		heap.backdoorAdd(1);
		heap.backdoorAdd(0);
		heap.backdoorAdd(2);
		assertFalse(heap.isHeap());
		assertTrue(heap.hasSmallerChild(0));
		assertEquals(1, heap.smallestChildOf(0));
	}

	@Test
	public void testSmallestChild3() {
		assertTrue(heap.isHeap());
		heap.backdoorAdd(1);
		heap.backdoorAdd(2);
		heap.backdoorAdd(0);
		assertFalse(heap.isHeap());
		assertTrue(heap.hasSmallerChild(0));
		assertEquals(2, heap.smallestChildOf(0));
	}

	@Test
	public void testSmallestChild4() {
		assertTrue(heap.isHeap());
		heap.backdoorAdd(1);
		heap.backdoorAdd(0);
		assertFalse(heap.isHeap());
		assertTrue(heap.hasSmallerChild(0));
		assertEquals(1, heap.smallestChildOf(0));
	}

	@Test
	public void testSmallestChild5() {
		assertTrue(heap.isHeap());
		heap.backdoorAdd(1);
		assertTrue(heap.isHeap());
		assertFalse(heap.hasSmallerChild(0));
	}

	@Test
	public void testSmallestChild6() {
		assertTrue(heap.isHeap());
		heap.backdoorAdd(2);
		heap.backdoorAdd(1);
		heap.backdoorAdd(0);
		assertFalse(heap.isHeap());
		assertTrue(heap.hasSmallerChild(0));
		assertEquals(2, heap.smallestChildOf(0));
	}

	@Test
	public void testRemove() {
		testAdd2();

		ArrayList<Integer> sortedResults = new ArrayList<>();
		while (!heap.isEmpty()) {
			int oldSize = heap.size();
			int removing = heap.remove();
			sortedResults.add(removing);
			assertTrue(heap.isHeap());
			assertEquals(oldSize - 1, heap.size());
		}

		for (int i = 1; i < sortedResults.size(); i++) {
			assertTrue(sortedResults.get(i-1).compareTo(sortedResults.get(i)) <= 0);
		}
	}
}
