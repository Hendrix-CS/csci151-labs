package maze.model;

public class Explorer {
	private Position location;
	private Direction heading;
	
	public Explorer(Position where) {
		location = where;
		heading = Direction.SOUTH;
	}
	
	public Position getLocation() {return location;}
	public Direction getHeading() {return heading;}
	
	public void move(Move move, Maze maze) {
		/******* TODO UNCOMMENT FOR STEP 4

		Position updated = move.newPosition(location, heading);
		if (maze.getStateFor(updated).canEnter()) {
			location = updated;
		}
		heading = move.newDirection(heading);
		
		********/
	}
	
	public Cell lookAhead(Maze maze) {
		/******* TODO UNCOMMENT FOR STEP 4

		return maze.getStateFor(heading.getNeighbor(location));
		
		********/
		// FIXME REMOVE THIS LINE
		return null;
	}
}
