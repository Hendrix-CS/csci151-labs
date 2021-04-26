package adventure;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.yaml.snakeyaml.Yaml;

/**
 * This class demonstrates how to read a YAML file using the SnakeYAML library
 * and process the resulting objects. It also demonstrates the use of the
 * Command class.
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
public class AdventureDemo {

	public static void main(String[] args) {

		// If anything goes wrong inside the 'try' (e.g. the file is not found),
		// it will jump to the 'catch' part.
		try {

			InputStream input = new FileInputStream(new File("Hendrix.yml"));

			// Create a Yaml object to parse the .yaml file
			Yaml yaml = new Yaml();

			// Parse the .yaml file and loop over the resulting objects.
			for (Object thing : yaml.loadAll(input)) {

				// We happen to know that the YAML file contains key-value
				// mappings, so the returned Objects will in fact be Maps, and
				// we can cast them as such. Eclipse shows us a warning here
				// (yellow underline) because this code might crash if we call
				// it on the wrong sort of .yaml file and 'thing' is not
				// actually a Map.
				printItems((Map<String, Object>) thing);
			}

			Scanner in = new Scanner(System.in);
			Command com;

			do {
				System.out.print("? ");

				// Read the user's input and parse it using a Command object.
				com = new Command(in.nextLine());

				// Now we can query the Command object to find out what the user
				// typed. This example only pays attention to the verb (the
				// first word typed).
				if (com.getVerb().equals(Verb.UNKNOWN)) {
					System.out.println("What?");
				} else {
					System.out.println("OK, you want to " + com.getVerb() + ".");
				}
			} while (com.getVerb() != Verb.QUIT);

			in.close();

		} catch (IOException e) {
			// This is what to do if anything goes wrong, e.g. the file
			// Hendrix.yaml is not found. Just print the error and quit.
			System.out.println(e);
			System.exit(1);
		}
	}

	/**
	 * Take a key-value mapping representing a location in the world. Look
	 * through it and print any items it contains.
	 *
	 * @param location
	 *            The key-value mapping representing the location.
	 */
	private static void printItems(Map<String, Object> location) {

		// Extract the list of items, which is stored under the key "items".
		// Each item is itself a key-value mapping.
		List<Map<String, Object>> itemList = (List<Map<String, Object>>) location.get("items");

		// If the location does not have a key called "items", then the call to
		// get("items") above will return null.
		if (itemList == null) {
			System.out.println("Location " + location.get("name") + " has no items.");
		} else {
			System.out.println("Items at location " + location.get("name") + ":");

			// For each item, get the values associated to the keys "name" and
			// "portable".
			for (Map<String, Object> itemAttributes : itemList) {
				System.out.print("  " + itemAttributes.get("name"));

				// Cast the "portable" value to a Boolean (not boolean) since
				// Boolean is an object type and can be null. If there is no
				// "portable" key then the result will be null and we can check
				// for that case. If we had used boolean instead, it would crash
				// when the key "portable" does not exist.
				Boolean portable = (Boolean) itemAttributes.get("portable");

				// Items are portable by default.  They are non-portable only
				// if the "portable" key exists and has the value false.
				if (portable != null && !portable) {
					System.out.println(" (non-portable)");
				} else {
					System.out.println();
				}
			}
		}
	}
}