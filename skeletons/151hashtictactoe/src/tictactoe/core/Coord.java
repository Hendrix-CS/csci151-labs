package tictactoe.core;

public class Coord {
	private int x, y;
	
	public Coord(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {return x;}
	public int getY() {return y;}
	
	public String toString() {
		return "(" + x + "," + y + ")";
	}
	
	public boolean equals(Object other) {
		if (other instanceof Coord) {
			Coord that = (Coord)other;
			return this.x == that.x && this.y == that.y;
		} else {
			return false;
		}
	}
	
	public int hashCode() {
		return x * Position.SIZE + y;
	}
}

