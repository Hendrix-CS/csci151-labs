package strategies;


import specs.Strategy;

public class Evil implements Strategy {

	@Override
	public void opponentMove(boolean isDefecting) {}

	@Override
	public boolean isDefecting() {
		return true;
	}

}
