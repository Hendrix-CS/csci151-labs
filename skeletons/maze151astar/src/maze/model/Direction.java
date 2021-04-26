package maze.model;

// Implement all methods for each Enum value.

import java.text.Normalizer;

public enum Direction {
	NORTH {
		@Override
		public Direction getClockwise() {
			return EAST;
		}

		@Override
		public Direction getCounterclockwise() {
			return WEST;
		}

		@Override
		public double getRotation() {
			return 180.0;
		}
	},
	SOUTH {
		@Override
		public Direction getClockwise() {
			return WEST;
		}

		@Override
		public Direction getCounterclockwise() {
			return EAST;
		}

		@Override
		public double getRotation() {
			return 0.0;			
		}
	},
	EAST {
		@Override
		public Direction getClockwise() {
			return SOUTH;
		}

		@Override
		public Direction getCounterclockwise() {
			return NORTH;
		}

		@Override
		public double getRotation() {
			return -90.0;
		}
	},
	WEST {
		@Override
		public Direction getClockwise() {
			return NORTH;
		}

		@Override
		public Direction getCounterclockwise() {
			return SOUTH;
		}

		@Override
		public double getRotation() {
			return 90.0;
		}
	};
	
	public Position getNeighbor(Position src) {
		return new Position(src.getX() - (int)(Math.sin(Math.toRadians(getRotation()))),
				src.getY() + (int)(Math.cos(Math.toRadians(getRotation()))));
	}
	
	public abstract Direction getClockwise();
	public abstract Direction getCounterclockwise();
	public abstract double getRotation();
	
	public static Direction[] randomDirections() {
		Direction[] result = new Direction[Direction.values().length];
		for (int i = 0; i < result.length; i++) {
			result[i] = Direction.values()[i];
		}
		for (int i = 0; i < result.length; i++) {
			int target = (int)(Math.random() * (result.length - i)) + i;
			Direction temp = result[i];
			result[i] = result[target];
			result[target] = temp;
		}
		return result;
	}
}
