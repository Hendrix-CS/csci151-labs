package maze.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import javafx.scene.paint.Color;

public class PuzzleTest {
	Puzzle puzzle = new Puzzle(15, 10);

	@Before
	public void setup() {
		for (Position p: new Position[]{new Position(0, 0), new Position(1, 0), new Position(3, 0),
				new Position(4, 0), new Position(6, 0), new Position(8, 0), new Position(10, 0),
				new Position(12, 0), new Position(1, 1), new Position(2, 1), new Position(3, 1),
				new Position(3, 1), new Position(5, 1), new Position(6, 1), new Position(7, 1),
				new Position(8, 1), new Position(9, 1), new Position(10, 1), new Position(11, 1),
				new Position(12, 1), new Position(13, 1), new Position(14, 1), new Position(0, 2),
				new Position(1, 2), new Position(3, 2), new Position(4, 2), new Position(5, 2),
				new Position(7, 2), new Position(10, 2), new Position(12, 2), new Position(14, 2),
				new Position(1, 3), new Position(2, 3), new Position(3, 3), new Position(6, 3),
				new Position(7, 3), new Position(8, 3), new Position(11, 3), new Position(1, 4),
				new Position(3, 4), new Position(4, 4), new Position(5, 4), new Position(6, 4),
				new Position(8, 4), new Position(9, 4), new Position(10, 4), new Position(11, 4),
				new Position(12, 4), new Position(13, 4), new Position(14, 4), new Position(0, 5),
				new Position(1, 5), new Position(3, 5), new Position(5, 5), new Position(7, 5),
				new Position(8, 5), new Position(10, 5), new Position(12, 5), new Position(1, 6),
				new Position(1, 6), new Position(1, 6), new Position(2, 6), new Position(3, 6),
				new Position(4, 6), new Position(6, 6), new Position(8, 6), new Position(11, 6),
				new Position(12, 6), new Position(0, 7), new Position(1, 7), new Position(4, 7),
				new Position(5, 7), new Position(6, 7), new Position(7, 7), new Position(8, 7),
				new Position(9, 7), new Position(10, 7), new Position(11, 7), new Position(0, 8),
				new Position(2, 8), new Position(5, 8), new Position(7, 8), new Position(9, 8),
				new Position(11, 8), new Position(12, 8), new Position(0, 9), new Position(1, 9),
				new Position(2, 9), new Position(4, 9), new Position(5, 9), new Position(6, 9),
				new Position(7, 9), new Position(8, 9), new Position(9, 9), new Position(12, 9)}) {
			puzzle.clear(p);
		}
	}

	@Test
	public void clearFillTest() {

		/******* TODO UNCOMMENT FOR STEP 3
		assertFalse(puzzle.clear(new Position(0, 1)));
		********/

		puzzle.fill(new Position(1, 1));
		assertFalse(puzzle.canEnter(new Position(1, 1)));
		assertTrue(puzzle.clear(new Position(0, 1)));
		assertTrue(puzzle.canEnter(new Position(0, 1)));
		assertFalse(puzzle.clear(new Position(-1, 0)));
		assertFalse(puzzle.clear(new Position(12, 10)));
	}

	/********** TODO UNCOMMENT FOR STEP 4

	@Test
	public void exploreTest() {
		assertFalse(puzzle.hasExplorer());
		puzzle.placeExplorer(new Position(0, 0));
		assertTrue(puzzle.hasExplorer());
		assertEquals(Color.YELLOW, puzzle.getColorFor(puzzle.getExplorerPosition()));
		assertEquals(Direction.SOUTH, puzzle.getExplorerHeading());
		puzzle.moveExplorer(Move.FORWARD);
		assertEquals(new Position(0, 0), puzzle.getExplorerPosition());
		puzzle.moveExplorer(Move.LEFT);
		assertEquals(Direction.EAST, puzzle.getExplorerHeading());
		puzzle.moveExplorer(Move.FORWARD);
		assertEquals(new Position(1, 0), puzzle.getExplorerPosition());
	}

	public void findGoal(Puzzle puzzle) {
		for (int i = puzzle.getWidth() - 1; i >= 0; i--) {
			for (int j = puzzle.getHeight() - 1; j >= 0; j--) {
				Position p = new Position(i, j);
				if (puzzle.canEnter(p)) {
					puzzle.placeGoal(p);
					assertEquals(p, puzzle.getGoal());
					return;
				}
			}
		}
	}

	***********/
}
