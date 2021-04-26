package tictactoe.core;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.TreeSet;

import org.junit.Test;

public class PositionTest {
	
	Coord[] positions = new Coord[]{
			new Coord(0, 0), new Coord(0, 1), new Coord(0, 2),
			new Coord(1, 0), new Coord(1, 1), new Coord(1, 2),
			new Coord(2, 0), new Coord(2, 1), new Coord(2, 2)};
	
	public ArrayList<Position> findAllPositions() {
		ArrayList<Position> all = new ArrayList<>();
		allPositionsHelp(all, new Position(), 0);
		return all;
	}
	
	public void allPositionsHelp(ArrayList<Position> all, Position prev, int i) {
		if (i < positions.length) {
			Coord p = positions[i];
			for (Symbol sym: Symbol.values()) {
				Position copy = new Position(prev);
				copy.put(p.getX(), p.getY(), sym);
				allPositionsHelp(all, copy, i+1);
			}
		} else {
			all.add(prev);
		}
	}

	@Test
	public void testHash() {
		ArrayList<Position> all = findAllPositions();
		assertEquals(19683, all.size());
		TreeSet<Integer> values = new TreeSet<>();
		for (Position p: all) {
			assertFalse(values.contains(p.hashCode()));
			values.add(p.hashCode());
		}
		assertEquals(all.size(), values.size());
	}

}
