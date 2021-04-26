package tictactoe.learn;

import tictactoe.core.Position;
import tictactoe.core.Status;

public class MoveDatabase {
	private HashTable<Position,Outcomes> table;
	
	public final static int MULTIPLIER_OFFSET = Position.SIZE * Position.SIZE;
	
	public MoveDatabase() {
		table = new HashTable<>();
	}
	
	private Outcomes get(Position p) {
		return table.get(p).orElseGet(() -> {
			Outcomes start = new Outcomes();
			start.bump(Status.X_WIN, MULTIPLIER_OFFSET - 1);
			start.bump(Status.O_WIN, MULTIPLIER_OFFSET - 1);
			table.put(p, start);
			return start;
		});
		
	}
	
	public void recordOutcome(Position p, Status outcome, int stepsFromEnd) {
		get(p).bump(outcome, MULTIPLIER_OFFSET - stepsFromEnd);
	}
	
	public Outcomes getOutcomes(Position p) {
		return get(p);
	}
}
