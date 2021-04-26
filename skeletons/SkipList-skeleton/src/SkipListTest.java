import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Random;

import org.junit.Test;

public class SkipListTest {

	@Test
	public void testAdd() {
		SkipList<String> list = new SkipList<>();
		list.add("d");
		list.add("a");
		list.add("g");
		list.add("b");
		list.add("f");
		list.add("c");
		list.add("e");
		String result = "";
		for (String s: list) {
			result += s;
		}
		assertEquals("abcdefg", result);
	}

	// Takes a few seconds to run.
	@Test
	public void testHeight() {
		for (int n = 0; n < 30; n++) {
			SkipList<Integer> list = new SkipList<>();
			for (int i = 0; i < 65536; i++) {
				list.add((int)(Integer.MAX_VALUE * Math.random()));
			}
			assertTrue(13 <= list.getHeight() && list.getHeight() <= 25);
			int last = Integer.MIN_VALUE;
			for (int value: list) {
				assertTrue(value >= last);
				last = value;
			}
		}
	}
	
	@Test
	public void testRandom() {
		SkipList<Integer> skip = new SkipList<>();
		
		Random rand = new Random();
		HashSet<Integer> contents = new HashSet<>();
		int lim = 1000000;
		for (int i = 0; i < lim; i++) {
			int n = rand.nextInt();
			skip.add(n);
			contents.add(n);
		}

		for (Integer n : contents) {
			assertNotNull(n + " was not found when calling lookup", skip.lookup(n));
		}
		
		for (int i = 0; i < lim; i++) {
			int n = rand.nextInt();
			Integer res = skip.lookup(n);
			if (contents.contains(n) && res == null) {
				fail(n + " should be in the skiplist but lookup returned null");
			} else if (contents.contains(n)) {
				assertEquals(Integer.valueOf(n), res);
			} else {
				assertFalse(n + " shouldn't be in the skiplist", !contents.contains(n) && res != null);
			}
		}
	}
}
