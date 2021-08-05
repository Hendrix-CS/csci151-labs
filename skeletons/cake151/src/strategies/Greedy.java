package strategies;

import specs.Strategy;

public class Greedy implements Strategy {

	/**
	 * A Greedy player will ALWAYS ask for all the cake.
	 * @return true
	 */
	@Override
	public boolean askForAll() {
		return true;
	}

	/**
	 * The Greedy strategy does not care what the other
	 * player did last round, so the code for this method
	 * is empty.
	 */
	@Override
	public void rememberOtherLast(boolean askedForAll) {}

}
