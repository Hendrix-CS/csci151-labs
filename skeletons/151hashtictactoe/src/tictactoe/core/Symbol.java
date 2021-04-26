package tictactoe.core;

public enum Symbol {
	EMPTY {
		@Override
		public char rep() {
			return ' ';
		}

		@Override
		public Status win() {
			throw new UnsupportedOperationException();
		}

		@Override
		public Status lose() {
			throw new UnsupportedOperationException();
		}
	}, X {
		@Override
		public char rep() {
			return 'X';
		}

		@Override
		public Status win() {
			return Status.X_WIN;
		}

		@Override
		public Status lose() {
			return Status.O_WIN;
		}
	}, O {
		@Override
		public char rep() {
			return 'O';
		}

		@Override
		public Status win() {
			return Status.O_WIN;
		}

		@Override
		public Status lose() {
			return Status.X_WIN;
		}
	};
	
	abstract public char rep();
	abstract public Status win();
	abstract public Status lose();
}
