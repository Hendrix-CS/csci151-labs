package tictactoe.learn;

import java.util.ArrayList;

public class Distribution {
	private ArrayList<Double> candidates;
	private double total;
	
	public Distribution() {
		candidates = new ArrayList<>();
		total = 0.0;
	}
	
	public void addCandidate(double value) {
		if (value < 0) {throw new IllegalArgumentException("value: " + value);}
		candidates.add(value);
		total += value;
	}
	
	public double get(int candidate) {
		return candidates.get(candidate);
	}
	
	public double portion(int candidate) {
		return get(candidate) / total;
	}
	
	public int pickBest() {
		double max = 0;
		int maxind = 0;
		for (int i = 0; i < candidates.size(); i++) {
			if (candidates.get(i) > max) {
				max = candidates.get(i);
				maxind = i;
			}
		}
		return maxind;
	}
	
	public int pickRandomly() {
		double pick = Math.random() * total;
		for (int i = 0; i < candidates.size(); i++) {
			if (pick < candidates.get(i)) {
				return i;
			} else {
				pick -= candidates.get(i);
			}
		}
		return candidates.size() - 1;
	}
}
