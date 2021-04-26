package trie.core;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

public class SortedArrayTest {

	@Test
	public void testMany() {
		for (int i = 0; i < 100; i++) {	
			testRandom();
		}
	}
	
	public void testRandom() {
		Random rand = new Random();
		SortedArray<Integer> nums = new SortedArray<>();
		for (int i = 0; i < 1000; i++) {
			nums.insert(rand.nextInt());
			assertSorted(nums);
		}
		
		while (nums.size() > 0) {
			nums.remove(0);
			assertSorted(nums);
		}
	}

	void assertSorted(SortedArray<Integer> nums) {
		for (int i = 1; i < nums.size(); i++) {
			assertTrue(nums.get(i-1).compareTo(nums.get(i)) <= 0);
		}
	}
}
