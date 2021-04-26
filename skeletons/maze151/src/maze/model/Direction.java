package maze.model;

// Implement all methods for each Enum value.

public enum Direction {
	NORTH {
		@Override
		public Direction getClockwise() {
			// TODO STEP 3 Complete this method
			return null;
		}

		@Override
		public Direction getCounterclockwise() {
			// TODO STEP 3 Complete this method
			return null;			
		}

		@Override
		public double getRotation() {
			// TODO STEP 3 Complete this method
			return 0.0;			
		}
	},
	SOUTH {
		@Override
		public Direction getClockwise() {
			// TODO STEP 3 Complete this method
			return null;			
		}

		@Override
		public Direction getCounterclockwise() {
			// TODO STEP 3 Complete this method
			return null;			
		}

		@Override
		public double getRotation() {
			// TODO STEP 3 Complete this method
			return 0.0;			
		}
	},
	EAST {
		@Override
		public Direction getClockwise() {
			// TODO STEP 3 Complete this method
			return null;			
		}

		@Override
		public Direction getCounterclockwise() {
			// TODO STEP 3 Complete this method
			return null;			
		}

		@Override
		public double getRotation() {
			// TODO STEP 3 Complete this method
			return 0.0;			
		}
	},
	WEST {
		@Override
		public Direction getClockwise() {
			// TODO STEP 3 Complete this method
			return null;			
		}

		@Override
		public Direction getCounterclockwise() {
			// TODO STEP 3 Complete this method
			return null;			
		}

		@Override
		public double getRotation() {
			// TODO STEP 3 Complete this method
			return 0.0;			
		}
	};
	
	public Position getNeighbor(Position src) {
		return new Position(src.getX() - (int)(Math.sin(Math.toRadians(getRotation()))),
				src.getY() + (int)(Math.cos(Math.toRadians(getRotation()))));
	}
	
	public abstract Direction getClockwise();
	public abstract Direction getCounterclockwise();
	public abstract double getRotation();
}
