package tictactoe.learn;

import org.junit.Test;

public class DistributionTest {

	@Test
	public void test() {
		Distribution d = new Distribution();
		
		final int numBuckets = 4;
		
		for (int i = 1; i <= numBuckets; i += 1) {
			d.addCandidate(i);
		}
		
		int[] counts = new int[numBuckets];
		for (int i = 0; i < 10000; i++) {
			counts[d.pickRandomly()] += 1;
		}
		
		for (int i = 0; i < counts.length; i++) {
			System.out.println(i + ":" + counts[i]);
		}
	}

}
