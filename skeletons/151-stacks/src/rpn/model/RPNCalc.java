package rpn.model;

import numstack.model.IntStack;

public class RPNCalc {
	private IntStack stack;

	// TODO ADD A CONSTRUCTOR HERE

	// The constructor will initialize the stack field to be your choice of
	// an ArrayIntStack or a ListIntStack

	/**
	 * This method takes a String and uses a regular expression
	 * to determine if it is only composed of digits, thus an integer.
	 *
	 * @param intStr
	 * @return a boolean to indicate that this string is an integer
	 */
	public static boolean isInteger(String intStr) {
		return intStr.matches("-?\\d+");
	}

	/**
	 * This method brings in a composite string, splits it up
	 * based on spaces into pieces, and then processes
	 * each piece with the addTerm method.
	 *
	 * @param expr
	 */
	public void evaluate(String expr) {
		for (String term: expr.split(" ")) {
			addTerm(term);
		}
	}

	/**
	 * A METHOD FOR TESTING ONLY
	 * @return the number of values still in memory in the calculator
	 */
	public int numValuesLeft() {
		return stack.size();
	}

	/**
	 * A string representation of the calculator
	 */
	public String toString() {
		return stack.toString();
	}

	// COMPLETE THE FOLLOWING METHODS

	/**
	 * Adds the term, either an integer or an operator, to the
	 * calculator. Integers go on the stack, while operators
	 * should be applied to the two most recent integers
	 * on the stack.
	 * @param term
	 */
	public void addTerm(String term) {
		// TODO add terms here
	}

	/**
	 * This method should return the most
	 * recent integer term added to the calculator. By the end of this
	 * method, the calculator should be in EXACTLY THE SAME STATE as
	 * when the method started.
	 * @return the last integer added to the calculator
	 */
	public int getLastValue() {
		// TODO return correct value
		return 0;
	}

	/**
	 * A METHOD FOR TESTING ONLY It should return the second most
	 * recent integer term added to the calculator. By the end of this
	 * method, the calculator should be in EXACTLY THE SAME STATE as
	 * when the method started.
	 * @return the second-to-last integer added to the calculator
	 */
	public int getNextToLastValue() {
		// TODO return correct value
		return 0;
	}
}
