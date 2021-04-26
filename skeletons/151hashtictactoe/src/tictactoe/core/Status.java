package tictactoe.core;

public enum Status {
	X_WIN {
		@Override
		public boolean isOver() {
			return true;
		}
	}, O_WIN {
		@Override
		public boolean isOver() {
			return true;
		}
	}, DRAW {
		@Override
		public boolean isOver() {
			return true;
		}
	}, CONTINUE {
		@Override
		public boolean isOver() {
			return false;
		}
	};
	
	abstract public boolean isOver();
}
