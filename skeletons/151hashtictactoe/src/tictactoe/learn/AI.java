package tictactoe.learn;

import java.util.ArrayList;
import java.util.List;

import tictactoe.core.Coord;
import tictactoe.core.Game;
import tictactoe.core.Position;
import tictactoe.core.Symbol;

public class AI {
	private MoveDatabase moves;
	
	public AI() {
		moves = new MoveDatabase();
	}
	
	public Coord pickRandomMove(Position board, Symbol player) {
		ArrayList<Coord> options = board.allEmpty();
		Distribution d = makeDistribution(board, options, player);
		return options.get(d.pickRandomly());
	}
	
	public Coord pickBestMove(Position board, Symbol player) {
		ArrayList<Coord> options = board.allEmpty();
		Distribution d = makeDistribution(board, options, player);
		return options.get(d.pickBest());
	}
	
	public HashTable<Coord,Double> getMovePortions(Position board, Symbol player) {
		HashTable<Coord,Double> result = new HashTable<>();
		ArrayList<Coord> options = board.allEmpty();
		Distribution d = makeDistribution(board, options, player);
		for (int i = 0; i < options.size(); i++) {
			result.put(options.get(i), d.portion(i));
		}
		return result;
	}
	
	private Distribution makeDistribution(Position board, ArrayList<Coord> options, Symbol player) {
		Distribution d = new Distribution();
		for (Coord option: options) {
			Position future = new Position(board);
			future.put(option.getX(), option.getY(), player);
			PlayerOutcome result = new PlayerOutcome(player, option, moves.getOutcomes(future));
			d.addCandidate(result.score());
		}
		return d;
	}
	
	public void train(Game g) {
		List<Position> startToEnd = g.getAllPositions();
		for (int i = 0; i < startToEnd.size(); i++) {
			moves.recordOutcome(startToEnd.get(i), g.getStatus().getStatus(), startToEnd.size() - i - 1);
		}
	}
	
	public void selfTrain(int iterations) {
		for (int i = 0; i < iterations; i++) {
			Game g = new Game();
			while (!g.isOver()) {
				Coord move = pickRandomMove(g.getCurrentPosition(), g.currentPlayer());
				g.takeTurn(move.getX(), move.getY());
			}
			train(g);
		}
	}
}
