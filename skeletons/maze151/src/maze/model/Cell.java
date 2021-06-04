package maze.model;

import javafx.scene.paint.Color;

public enum Cell {
	OPEN {
		@Override
		public Color getColor() {
			return Color.YELLOW;
		}

		@Override
		public Cell flipped() {
			return CLOSED;
		}

		@Override
		public boolean canEnter() {
			return true;
		}
	}, CLOSED {
		@Override
		public Color getColor() {
			return Color.BROWN;
		}

		@Override
		public Cell flipped() {
			return OPEN;
		}

		@Override
		public boolean canEnter() {
			return false;
		}
	}, VISITED {

		@Override
		public Color getColor() {
			return Color.AQUA;
		}

		@Override
		public Cell flipped() {
			return CLOSED;
		}

		@Override
		public boolean canEnter() {
			return true;
		}

	};

	public abstract Color getColor();
	public abstract Cell flipped();
	public abstract boolean canEnter();
}
