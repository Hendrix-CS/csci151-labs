package maze.model;

public record Position(int x, int y) {

	public boolean adjacentTo(Position other) {
		for (Direction d : Direction.values()) {
			if (d.getNeighbor(this).equals(other)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof Position that) {
            return this.x == that.x && this.y == that.y;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}

}
