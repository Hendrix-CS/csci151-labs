package adventure;

import java.util.Arrays;
import java.util.HashSet;

/**
 * A class for parsing and representing commands typed by the player at the
 * prompt. Commands are generally of the form "verb noun" or "verb direction",
 * with small words like "at", "to", etc. ignored. You are welcome to use this
 * class in your project.
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
public class Command {

	private Verb verb;
	private Direction direction;
	private String noun;

	private HashSet<String> ignoredWords;
	private String verbString;

	public Command(String s) {
		ignoredWords = new HashSet<String>();
		String[] ignored = { "at", "in", "on", "to", "for", "of", "with", "the", "a", "an" };
		ignoredWords.addAll(Arrays.asList(ignored));

		String[] words = s.split("\\s+");

		verb = Verb.UNKNOWN;
		direction = Direction.UNKNOWN;
		noun = "";

		if (words.length > 0) {
			verbString = words[0];
			verb = Verb.parse(verbString);

			if (words.length > 1) {
				Direction d = Direction.parse(words[1]);
				if (d != Direction.UNKNOWN) {
					direction = d;
				} else {
					int i = 1;
					while (i < words.length && ignoredWords.contains(words[i]))
						i++;

					while (i < words.length) {
						noun += " " + words[i];
						i++;
					}
					noun = noun.trim();
				}
			}
		}
	}

	/**
	 * Get the verb typed by the player (if any), represented by a value of the
	 * enumeration type Verb.
	 */
	public Verb getVerb() {
		return verb;
	}

	public void setVerb(Verb verb) {
		this.verb = verb;
	}

	/**
	 * Get the direction typed by the player (if any), represented by a value of
	 * the enumeration type Direction. UNKNOWN if the user did not type a
	 * recognized direction.
	 */
	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	/**
	 * Get the noun typed by the player (if any), i.e. the object of the verb.
	 * Can be arbitrary.
	 */
	public String getNoun() {
		return noun;
	}

	public void setNoun(String noun) {
		this.noun = noun;
	}

	/**
	 * Get the actual string typed by the user as the first word, no matter
	 * whether it was a recognized Verb or not. This could be used, for example,
	 * to extend the game with verbs that only apply to specific items. For
	 * example, perhaps there is a shovel item that specifies a verb called
	 * "dig" that can be used with the shovel. It would be tedious to add a new
	 * value to the Verb enumeration for every single custom action. Instead, in
	 * this case, if the Verb is UNKNOWN, we could simply check whether any
	 * local items specify their own custom verbs, and then look at the
	 * Command's verbString to see if it matches.
	 */
	public String getVerbString() {
		return verbString;
	}

	public void setVerbString(String verbString) {
		this.verbString = verbString;
	}

}