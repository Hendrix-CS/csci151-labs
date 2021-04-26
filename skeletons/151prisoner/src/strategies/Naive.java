package strategies;


import specs.Strategy;

public class Naive implements Strategy {

	@Override
	public void opponentMove(boolean isDefecting) {}

	@Override
	public boolean isDefecting() {
		return false;
	}

}
