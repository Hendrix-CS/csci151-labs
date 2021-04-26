package strategies;


import specs.Strategy;

public class Tit4Tat implements Strategy {
	private boolean defected = false;
	
	public void opponentMove(boolean isDefecting) {
		this.defected = isDefecting;
	}
	
	public boolean isDefecting() {
		return this.defected;
	}
}
