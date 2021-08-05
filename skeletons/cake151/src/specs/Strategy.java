package specs;

public interface Strategy {
	/**
	 * This method will return your choice for the
	 * current game. Return true to ask for the
	 * whole cake, and return false to ask for half.
	 */
	public boolean askForAll();

	/**
	 * This method is called after the end of the game,
	 * to notify you of what the other player chose.
	 */
	public void rememberOtherLast(boolean askedForAll);

}
