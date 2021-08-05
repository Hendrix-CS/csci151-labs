package strategies;

import specs.Strategy;

public class Mimic implements Strategy {

	// State variable to record what the other player did on the last turn.
	// We start assuming the best, that they did not ask for the whole cake.
	private boolean otherAskedForAll = false;

	/**
	 * A MIMIC player will save the incoming boolean in the above
	 * state variable for use in their reply next round.
	 */
	@Override
	public void rememberOtherLast(boolean askedForAll) {
		this.otherAskedForAll = askedForAll;
	}

	/**
	 * The MIMIC will respond back with the other player's last choice.
	 * If they just asked for it all, then we will now ask for it all.
	 * If they were nice, then we will be nice.
	 * @return the other's last choice
	 */
	@Override
	public boolean askForAll() {
		return this.otherAskedForAll;
	}
}
