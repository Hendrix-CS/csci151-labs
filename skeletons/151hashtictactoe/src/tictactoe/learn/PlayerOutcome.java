package tictactoe.learn;

import tictactoe.core.Coord;
import tictactoe.core.Status;
import tictactoe.core.Symbol;

public class PlayerOutcome implements Comparable<PlayerOutcome> {
	private Outcomes outcomes;
	private Symbol player;
	private Coord move;
	
	public PlayerOutcome(Symbol player, Coord move, Outcomes outcomes) {
		this.outcomes = outcomes;
		this.player = player;
		this.move = move;
	}
	
	public Coord getMove() {return move;}
	
	public double winProb() {
		return (double)outcomes.getCountFor(player.win()) / outcomes.getTotalCount();
	}
	
	public double drawProb() {
		return (double)outcomes.getCountFor(Status.DRAW) / outcomes.getTotalCount();
	}
	
	public double loseProb() {
		return (double)outcomes.getCountFor(player.lose()) / outcomes.getTotalCount();
	}
	
	public double score() {
		if (outcomes == null) {
			return 0.0;
		} else {
			return winProb() * 2 + drawProb();
		}
	}

	@Override
	public int compareTo(PlayerOutcome o) {
		if (score() > o.score()) {
			return 1;
		} else if (score() < o.score()) {
			return -1;
		} else {
			return 0;
		}
	}
}
