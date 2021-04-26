package adventure;

/**
 * An enumeration to represent directions in which the player can move. You are
 * welcome to use this in your project if you wish.
 *
 * <p>
 * Copyright 2017 Brent Yorgey. This work is licensed under a
 * <a rel="license" href= "http://creativecommons.org/licenses/by/4.0/">Creative
 * Commons Attribution 4.0 International License</a>.
 * </p>
 *
 * @author Brent Yorgey
 * @version August 21, 2017
 *
 */
public enum Direction {
	NORTH, SOUTH, EAST, WEST, NORTHEAST, NORTHWEST, SOUTHEAST, SOUTHWEST, UP, DOWN, IN, OUT, UNKNOWN;

	/**
	 * Try to parse a string into a Direction in a case-insensitive way.
	 *
	 * @param s
	 *            The string to be parsed
	 * @return The parsed Direction, or UNKNOWN if the direction is not
	 *         recognized.
	 */
	public static Direction parse(String s) {
		try {
			return Direction.valueOf(s.toUpperCase().trim());
		} catch (IllegalArgumentException e) {
			return UNKNOWN;
		}
	}
}