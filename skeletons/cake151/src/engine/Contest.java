package engine;

import specs.Payoffs;
import specs.Score;
import specs.Strategy;


public class Contest {
	private Strategy one, two;
	private int oneScore, twoScore;
	private Payoffs payoffs;
	
	public Contest(Strategy one, Strategy two, Payoffs p) {
		this.one = one;
		this.two = two;
		this.payoffs = p;
	}
	
	public void round() {
		boolean def1 = one.askForAll();
		boolean def2 = two.askForAll();
		one.rememberOtherLast(def2);
		two.rememberOtherLast(def1);
		Score s = payoffs.score(def1, def2);
		oneScore += s.getOne();
		twoScore += s.getTwo();
	}
	
	public void rounds(int n) {
		for (int i = 0; i < n; ++i) {
			round();
		}
	}
	
	public int oneScore() {return oneScore;}
	
	public int twoScore() {return twoScore;}
}
