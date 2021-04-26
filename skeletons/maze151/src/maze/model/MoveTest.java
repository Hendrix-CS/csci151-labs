package maze.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class MoveTest {

	/**************  TODO UNCOMMENT FOR STEP 4
	
	@Test
	public void testForward() {
		Position start = new Position(0, 0);
		Position next = Move.FORWARD.newPosition(start, Direction.NORTH);
		assertEquals(new Position(0, -1), next);
		next = Move.FORWARD.newPosition(next, Direction.SOUTH);
		assertEquals(start, next);
		next = Move.FORWARD.newPosition(next, Direction.EAST);
		assertEquals(new Position(1, 0), next);
		next = Move.FORWARD.newPosition(next, Direction.WEST);
		assertEquals(start, next);
	}

	@Test
	public void testLeft() {
		Direction heading = Direction.SOUTH;
		heading = Move.LEFT.newDirection(heading);
		assertEquals(Direction.EAST, heading);
		heading = Move.LEFT.newDirection(heading);
		assertEquals(Direction.NORTH, heading);
		heading = Move.LEFT.newDirection(heading);
		assertEquals(Direction.WEST, heading);
		heading = Move.LEFT.newDirection(heading);
		assertEquals(Direction.SOUTH, heading);
	}

	@Test
	public void testRight() {
		Direction heading = Direction.SOUTH;
		heading = Move.RIGHT.newDirection(heading);
		assertEquals(Direction.WEST, heading);
		heading = Move.RIGHT.newDirection(heading);
		assertEquals(Direction.NORTH, heading);
		heading = Move.RIGHT.newDirection(heading);
		assertEquals(Direction.EAST, heading);
		heading = Move.RIGHT.newDirection(heading);
		assertEquals(Direction.SOUTH, heading);
	}
	
	@Test
	public void noChange() {
		Position p = new Position(1, 1);
		for (Direction d: Direction.values()) {
			assertEquals(d, Move.FORWARD.newDirection(d));
			assertEquals(p, Move.LEFT.newPosition(p, d));
			assertEquals(p, Move.RIGHT.newPosition(p, d));
		}
	}
	
	
	******************/
}
