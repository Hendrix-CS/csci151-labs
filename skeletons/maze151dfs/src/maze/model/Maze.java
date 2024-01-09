package maze.model;

//Create an implementation that passes all tests in MazeTest.

public class Maze {

	private Cell[][] cells;

	public Maze(int width, int height) {
		cells = new Cell[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				cells[i][j] = Cell.CLOSED;
			}
		}
	}

	public int getWidth() {
		return cells.length;
	}

	public int getHeight() {
		return cells[0].length;
	}

	public boolean inMaze(Position p) {
		return !(p.x() < 0 || p.y() < 0 || p.x() >= getWidth() || p.y() >= getHeight());
	}

	public Cell getStateFor(Position p) {
		if (inMaze(p)) {
			return cells[p.x()][p.y()];
		}
		return Cell.CLOSED;
	}

	public void setStateFor(Position p, Cell state) {
		if (inMaze(p)) {
			cells[p.x()][p.y()] = state;
		}
	}

	public boolean inRoom(Position p) {
		if (getStateFor(p).canEnter()) {
			for (Direction d : Direction.values()) {
				Direction turnOnce = d.getClockwise();
				Position fp = d.getNeighbor(p);
				Position sp = turnOnce.getNeighbor(fp);
				Position tp = turnOnce.getNeighbor(p);
				if ((getStateFor(fp).canEnter()) &&
						(getStateFor(sp).canEnter()) &&
						(getStateFor(tp).canEnter())) {
					return true;
				}
			}
		}
		return false;
	}

	public int numOpenNeighbors(Position p) {
		int open = 0;
		for (Direction d : Direction.values()) {
			Position fp = d.getNeighbor(p);
			Cell first = getStateFor(fp);
			if (first == Cell.OPEN) {
				open++;
			}
		}
		return open;
	}

	// NEW FOR THIS LAB
	public void clearVisits() {
		for (int x = 0; x < getWidth(); x++) {
			for (int y = 0; y < getHeight(); y++) {
				if (cells[x][y] == Cell.VISITED) {
					cells[x][y] = Cell.OPEN;
				}
			}
		}
	}

	// NEW FOR THIS LAB
	public int getCountOf(Cell cellType) {
		int count = 0;
		for (int x = 0; x < getWidth(); x++) {
			for (int y = 0; y < getHeight(); y++) {
				if (cells[x][y] == cellType) {count += 1;}
			}
		}
		return count;
	}

}
