package maze.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class DirectionTest {

	@Test
	public void testClockwise() {
		for (Direction d: Direction.values()) {
			assertEquals(d, d.getClockwise().getCounterclockwise());
			Direction d1 = d;
			Direction d2 = d;
			for (int i = 0; i < Direction.values().length; i++) {
				d1 = d1.getClockwise();
				d2 = d2.getCounterclockwise();
			}
			assertEquals(d, d1);
			assertEquals(d, d2);
			
			d1 = d.getClockwise();
			d2 = d.getCounterclockwise();
			assertNotEquals(d1, d2);
			assertNotEquals(d, d1);
			assertNotEquals(d, d2);
		}
	}
	
	@Test
	public void testNeighbors() {
		Position src = new Position(1, 1);
		assertEquals(new Position(1, 0), Direction.NORTH.getNeighbor(src));
		assertEquals(new Position(1, 2), Direction.SOUTH.getNeighbor(src));
		assertEquals(new Position(2, 1), Direction.EAST.getNeighbor(src));
		assertEquals(new Position(0, 1), Direction.WEST.getNeighbor(src));
	}

}
