package numstack.model;

import static org.junit.Assert.*;

import org.junit.Test;

abstract public class IntStackTest {
	
	abstract public IntStack create();
	
	IntStack stack = create();
	
	public void checkEmpty() {
		assertTrue(stack.isEmpty());
		assertEquals(0, stack.size());
	}

	@Test(expected=IllegalStateException.class)
	public void testEmpty1() {
		checkEmpty();
		stack.peek();
	}

	@Test(expected=IllegalStateException.class)
	public void testEmpty2() {
		checkEmpty();
		stack.pop();
	}

	@Test
	public void testPush() {
		stack.push(55);
		assertFalse(stack.isEmpty());
		assertEquals(55, stack.peek());
		assertEquals(1, stack.size());
		
		stack.push(44);
		assertEquals(44, stack.peek());
		assertEquals(2, stack.size());
	}
	
	@Test
	public void testPop() {
		testPush();
		stack.pop();
		assertEquals(1, stack.size());
		assertEquals(55, stack.peek());
		stack.pop();
		assertEquals(0, stack.size());
	}
	
	@Test
	public void testThatBug() {
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		assertEquals(4, stack.size());
		assertEquals(4, stack.peek());
		assertEquals("1 2 3 4 ", stack.toString());
		
		stack.pop();
		assertEquals("1 2 3 ", stack.toString());
		stack.pop();
		assertEquals("1 2 ", stack.toString());
		stack.pop();
		assertEquals("1 ", stack.toString());
		stack.pop();
		assertEquals("", stack.toString());
		
		assertEquals(0, stack.size());
	}
	
	@Test
	public void testString() {
		stack.push(1);
		stack.push(2);
		stack.push(3);
		assertEquals("1 2 3 ", stack.toString());
	}
	
	public final static int NUM_ITEMS = 100;
	
	@Test
	public void testMajorPush() {
		String target = "";
		for (int i = 0; i < NUM_ITEMS ; i++) {
			stack.push(i);
			target += Integer.toString(i) + " ";
		}
		
		assertEquals(NUM_ITEMS, stack.size());
		assertEquals(target, stack.toString());
		int expected = NUM_ITEMS - 1;
		while (!stack.isEmpty()) {
			assertEquals(expected, stack.peek());
			stack.pop();
			expected -= 1;
		}
	}
}
