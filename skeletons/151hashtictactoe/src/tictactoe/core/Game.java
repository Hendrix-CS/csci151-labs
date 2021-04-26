package tictactoe.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {
	private Symbol mover;
	private ArrayList<Position> positions;
	
	public Game() {
		mover = Symbol.X;
		positions = new ArrayList<>();
		positions.add(new Position());
	}
	
	public Position getCurrentPosition() {
		return positions.get(positions.size() - 1);
	}
	
	public List<Position> getAllPositions() {
		return Collections.unmodifiableList(positions);
	}
	
	public boolean available(int x, int y) {
		return getCurrentPosition().get(x, y) == Symbol.EMPTY;
	}
	
	public ArrayList<Coord> allAvailable() {
		return getCurrentPosition().allEmpty();
	}
	
	public ArrayList<Coord> allPositions() {
		return getCurrentPosition().allPositions();
	}
	
	public boolean isOver() {
		return getStatus().getStatus().isOver();
	}
	
	public void takeTurn(int x, int y) {
		if (isOver()) {
			throw new IllegalStateException("Game is already over");
		}
		if (available(x, y)) {
			Position updated = new Position(getCurrentPosition());
			updated.put(x, y, mover);
			positions.add(updated);
			mover = (mover == Symbol.X) ? Symbol.O : Symbol.X;
		} else {
			throw new IllegalStateException(String.format("Position (%d,%d) not available", x, y));
		}
	}
	
	public Result getStatus() {
		return getCurrentPosition().getStatus();
	}
	
	public Symbol get(int x, int y) {
		return getCurrentPosition().get(x, y);
	}
	
	public Symbol currentPlayer() {
		return mover;
	}
}
