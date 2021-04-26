package adventure;

/**
 * An enumeration to represent generic actions the player can perform.
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
public enum Verb {
	GO, LOOK, TAKE, DROP, INVENTORY, SCORE, HELP, QUIT, UNKNOWN;

	/**
	 * Try to parse a string into a Verb in a case-insensitive way.
	 *
	 * @param s  The string to be parsed
	 * @return   The parsed Verb, or UNKNOWN if the verb is not recognized.
	 */
	public static Verb parse(String s) {
		try {
			return Verb.valueOf(s.toUpperCase().trim());
		} catch (IllegalArgumentException e) {
			return UNKNOWN;
		}
	}
}