package maze.model;

import javafx.scene.paint.Color;
import maze.searchers.*;

//Create an implementation that passes all tests in PuzzleTest.

public class Puzzle {
	
	private Maze dungeon;
	private Explorer hero;
	private Position goal;
	
	public Puzzle(int w, int h) {
		dungeon = new Maze(w, h);
	}
	
	public Color getColorFor(Position spot) {
		return dungeon.getStateFor(spot).getColor();
	}

	public boolean canEnter(Position spot) {
		return dungeon.getStateFor(spot).canEnter();
	}

	public void fill(Position spot) {
		dungeon.setStateFor(spot, Cell.CLOSED);
	}

	public boolean clear(Position spot) {
		if (dungeon.inMaze(spot)) {
			dungeon.setStateFor(spot, Cell.OPEN);
			if (dungeon.inRoom((spot))) {
				dungeon.setStateFor(spot, Cell.CLOSED);
				return false;
			}
			return true;
		}
		return false;
	}

	public int getWidth() {
		return dungeon.getWidth();
	}

	public int getHeight() {
		return dungeon.getHeight();
	}

	public boolean hasExplorer() {
		return hero != null;
	}

	public void placeExplorer(Position p) {
		hero = new Explorer(p);
	}

	public void moveExplorer(Move m) {
		if (hasExplorer()) {
			hero.move(m, dungeon);
		}
	}

	public Position getExplorerPosition() {
		if (hasExplorer()) {
			return hero.getLocation();
		}
		return null;
	}

	public Direction getExplorerHeading() {
		if (hasExplorer()) {
			return hero.getHeading();
		}
		return null;
	}

	public void placeGoal(Position p) {
		goal = p;
	}

	public Position getGoal() {
		return goal;
	}

	// NEW FOR THIS LAB!!
	public void clearVisits() {
		dungeon.clearVisits();
	}

	// NEW FOR THIS LAB!!
	public int getCountOf(Cell cellType) {
		return dungeon.getCountOf(cellType);
	}

	// NEW FOR THIS LAB!!
	public boolean hasGoal() {return goal != null;}


	public void tunnelRandomly(boolean perfect) {
		// TODO STEP 3 WRITE ME
	}
}
