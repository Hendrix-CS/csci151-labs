package maze.model;

public class Trail {
	private final Position end;
	private final Trail prev;

	public Trail(Position p, Trail prev) {
		this.end = p;
		this.prev = prev;
	}

	public Position getEnd() {return end;}
	public Trail getPrev() {return prev;}

	/******** TODO UNCOMMENT FOR STEP 4

	public boolean solves(Puzzle puzzle) {
		if (!end.equals(puzzle.getGoal())) {
			return false;
		}
		Trail trail = this;
		while (trail.getPrev() != null) {
			if (!trail.getEnd().adjacentTo(trail.getPrev().getEnd())) {
				return false;
			}
			trail = trail.getPrev();
		}
		return trail.getEnd().equals(puzzle.getExplorerPosition());
	}

	 public int getLength() {
	 if (prev == null) {return 1;}
	 else {return 1 + prev.getLength();}
	 }

	*********/
}
