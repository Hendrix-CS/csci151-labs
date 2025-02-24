package maze.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class CellTest {

	@Test
	public void flipTest() {
		for (Cell cell: Cell.values()) {
			assertNotEquals(cell.canEnter(), cell.flipped().canEnter());
			assertEquals(cell.canEnter(), cell.flipped().flipped().canEnter());
			Cell.OPEN.getColor();
		}
	}

}
