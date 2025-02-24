package maze.model;

// Create an implementation that passes all tests in MoveTest.

public enum Move {

    FORWARD {
        @Override
        public Position newPosition(Position p, Direction d) {
            return d.getNeighbor(p);
        }

        @Override
        public Direction newDirection(Direction d) {
            return d;
        }
    }, LEFT{
        @Override
        public Position newPosition(Position p, Direction d) {
            return p;
        }

        @Override
        public Direction newDirection(Direction d) {
            return d.getCounterclockwise();
        }
    }, RIGHT {
        @Override
        public Position newPosition(Position p, Direction d) {
            return p;
        }

        @Override
        public Direction newDirection(Direction d) {
            return d.getClockwise();
        }
    };

    public abstract Position newPosition(Position p, Direction d);

    public abstract Direction newDirection(Direction d);
	
}
