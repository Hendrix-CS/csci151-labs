package maze.model;

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

public class MazeTest {

	Maze maze;

	@Before
	public void setup() {
		maze = new Maze(4, 3);
	}

	@Test
	public void testConstructor() {
		assertEquals(4, maze.getWidth());
		assertEquals(3, maze.getHeight());
		for (int i = 0; i < maze.getWidth(); i++) {
			for (int j = 0; j < maze.getHeight(); j++) {
				Position where = new Position(i, j);
				assertFalse(maze.getStateFor(where).canEnter());
				assertTrue(maze.inMaze(where));
			}
		}

		for (int i = -maze.getWidth(); i < maze.getWidth() * 2; i++) {
			boolean iIn = i >= 0 && i < maze.getWidth();
			for (int j = -maze.getHeight(); j < maze.getHeight() * 2; j++) {
				boolean jIn = j >= 0 && j < maze.getHeight();
				Position where = new Position(i, j);
				assertFalse(maze.getStateFor(where).canEnter());
				assertEquals(iIn && jIn, maze.inMaze(where));
			}
		}
	}

	@Test
	public void testSpace() {
		HashSet<Position> openings = createOpeningsFrom(new Position(1, 1), new Position(1, 0), new Position(0, 1));
		for (int i = 0; i < maze.getWidth(); i++) {
			for (int j = 0; j < maze.getHeight(); j++) {
				Position where = new Position(i, j);
				assertEquals(openings.contains(where), maze.getStateFor(where) == Cell.OPEN);
			}
		}
	}

	HashSet<Position> createOpeningsFrom(Position... ps) {
		HashSet<Position> openings = new HashSet<>();
		for (Position p: ps) {
			openings.add(p);
			maze.setStateFor(p, Cell.OPEN);
		}
		return openings;
	}

	/******** TODO UNCOMMENT FOR STEP 3

	@Test
	public void testRoom() {
		HashSet<Position> openings = createOpeningsFrom(new Position(1, 1), new Position(1, 0), new Position(0, 1));
		for (Position open: openings) {
			assertFalse(maze.inRoom(open));
		}
		Position fourth = new Position(0, 0);
		assertFalse(maze.inRoom(fourth));
		maze.setStateFor(fourth, Cell.OPEN);
		openings.add(fourth);
		for (Position open: openings) {
			assertTrue(maze.inRoom(open));
		}
	}

	*********/

}
