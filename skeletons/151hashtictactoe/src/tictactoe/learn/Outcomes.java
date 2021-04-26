package tictactoe.learn;

import java.util.EnumMap;

import tictactoe.core.Status;

public class Outcomes {
	private EnumMap<Status,Integer> counts;
	
	public Outcomes() {
		counts = new EnumMap<>(Status.class);
	}
	
	public void bump(Status s, int degree) {
		if (!counts.containsKey(s)) {
			counts.put(s, degree);
		} else {
			counts.put(s, counts.get(s) + degree);
		}
	}
	
	public int getCountFor(Status s) {
		return counts.containsKey(s) ? counts.get(s) : 0;
	}
	
	public int getTotalCount() {
		int total = 0;
		for (Status s: Status.values()) {
			total += getCountFor(s);
		}
		return total;
	}
}
