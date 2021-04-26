package games;

public class Position {
	private int x, y;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {return x;}
	public int getY() {return y;}

	
	@Override
	public boolean equals(Object other) {
		if (other instanceof Position) {
			Position that = (Position)other;
			return this.x == that.x && this.y == that.y;
		} else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}
	
	@Override
	public int hashCode() {
		return (23 * 31 + x) * 31 + y;
	}
}
