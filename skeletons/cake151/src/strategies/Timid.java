package strategies;

import specs.Strategy;

public class Timid implements Strategy {

	/**
	 * A Timid player will NEVER ask for all the cake.
	 * @return false
	 */
	@Override
	public boolean askForAll() {
		return false;
	}

	/**
	 * The Timid strategy does not care what the other
	 * player did last round, so the code for this method
	 * is empty.
     */
	@Override
	public void rememberOtherLast(boolean askedForAll) {}

}
