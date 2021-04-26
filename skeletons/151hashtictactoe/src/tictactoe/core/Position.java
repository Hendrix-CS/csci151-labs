package tictactoe.core;

import java.util.ArrayList;

public class Position {
	private Symbol[][] board;
	
	public static final int SIZE = 3;
	
	public Position() {
		board = new Symbol[SIZE][SIZE];
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				put(i, j, Symbol.EMPTY);
			}
		}
	}
	
	public Position(Position original) {
		this();
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				put(i, j, original.get(i, j));
			}
		}
	}
	
	public Symbol get(int x, int y) {
		return board[x][y];
	}
	
	public void put(int x, int y, Symbol piece) {
		board[x][y] = piece;
	}
	
	public int numEmpty() {
		return allEmpty().size();
	}
	
	public ArrayList<Coord> allEmpty() {
		ArrayList<Coord> empty = new ArrayList<>();
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				if (get(x, y) == Symbol.EMPTY) {
					empty.add(new Coord(x, y));
				}
			}
		}
		return empty;
	}
	
	public ArrayList<Coord> allPositions() {
		ArrayList<Coord> positions = new ArrayList<>();
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				positions.add(new Coord(x, y));
			}
		}
		return positions;
	}
	
	public Result getStatus() {
		ArrayList<Coord[]> paths = new ArrayList<>();
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (j + 2 < SIZE) {
					paths.add(new Coord[]{new Coord(i, j), new Coord(i, j + 1), new Coord(i, j + 2)});
				}
				if (i + 2 < SIZE) {
					paths.add(new Coord[]{new Coord(i, j), new Coord(i + 1, j), new Coord(i + 2, j)});
				}
				if (i + 2 < SIZE && j + 2 < SIZE) {
					paths.add(new Coord[]{new Coord(i, j), new Coord(i + 1, j + 1), new Coord(i + 2, j + 2)});
					paths.add(new Coord[]{new Coord(i + 2, j), new Coord(i + 1, j + 1), new Coord(i, j + 2)});
				}				
			}
		}
		
		for (Coord[] coords: paths) {
			
			if (check(Symbol.X, coords)) {
				return new Result(Status.X_WIN, coords[0], coords[2]);
			}

			if (check(Symbol.O, coords)) {
				return new Result(Status.O_WIN, coords[0], coords[2]);
			}
		}
		return numEmpty() == 0 ? new Result(Status.DRAW) : new Result(Status.CONTINUE);
	}
	
	public boolean check(Symbol sym, Coord... coords) {
		for (Coord coord: coords) {
			if (get(coord.getX(), coord.getY()) != sym) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof Position) {
			Position p = (Position)other;
			for (int y = 0; y < SIZE; y++) {
				for (int x = 0; x < SIZE; x++) {
					if (get(x, y) != p.get(x, y)) {
						return false;
					}
				}
			}
		} else {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				result.append(get(x, y).rep());
				result.append(" ");
			}
			result.append("\n");
		}
		return result.toString();
	}
	
	@Override
	public int hashCode() {
		int base = Symbol.values().length;
		int result = 0;
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				result *= base;
				result += board[x][y].ordinal();
			}
		}
		return result;
	}
}
