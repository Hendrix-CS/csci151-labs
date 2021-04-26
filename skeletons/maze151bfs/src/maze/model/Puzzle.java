package maze.model;

import javafx.scene.paint.Color;
import maze.searchers.ArrayStack;
import maze.searchers.Queue;

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

	public void tunnelRandomly() {

		ArrayStack<Position> places = new ArrayStack<>();
		places.push(new Position(0, 0));

		while(!places.isEmpty()) {
			Position spot = places.pop();
			boolean success = clear(spot);
			if (success) {
				for (Direction d: Direction.randomDirections()) {
					Position neighbor = d.getNeighbor(spot);
					if (dungeon.getStateFor(neighbor) == Cell.CLOSED) {
						places.push(neighbor);
					}
				}
			}
		}

	}

	public Trail solve(Queue<Trail> queue) {

		if (!hasExplorer() || !hasGoal()) {
			return null;
		}

		queue.add(new Trail(hero.getLocation(), null));

		while (!queue.isEmpty()) {
			Trail possible = queue.remove();
			if (possible.getEnd().equals(goal)) {
				return possible;
			}
			if (dungeon.getStateFor(possible.getEnd()) == Cell.OPEN) {
				dungeon.setStateFor(possible.getEnd(), Cell.VISITED);
				for (Direction d : Direction.values()) {
					queue.add(new Trail(d.getNeighbor(possible.getEnd()), possible));
				}
			}
		}

		return null;
	}
}
