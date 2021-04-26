package trie.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

public class SortedArray<T extends Comparable<T>> implements Iterable<T> {
	private ArrayList<T> elements = new ArrayList<>();

	/******
	 * Wrapper method to get the ith element from elements
	 *
	 * @param i
	 *            the index into elements
	 * @return the element T at spot i of elements
	 */
	public T get(int i) {
		return elements.get(i);
	}

	/******
	 * Wrapper method to find the size of elements
	 *
	 * @return the size of elements
	 */
	public int size() {
		return elements.size();
	}

	/******
	 * Perform a binary search to find the index of the given element.
	 *
	 * @param elt
	 *            The element to search for.
	 * @return The index of the element, or -1 if not found.
	 */
	private Optional<Integer> findIndex(T elt) {
		int lo = 0, hi = elements.size();
		while (lo < hi) {
			int mid = (lo + hi) / 2;
			if (elt.equals(elements.get(mid))) {
				return Optional.of(mid);
			} else if (elt.compareTo(elements.get(mid)) < 0) {
				hi = mid;
			} else {
				lo = mid + 1;
			}
		}
		return Optional.empty();
	}

	/******
	 * Check if the array contains the given element, using a binary search.
	 *
	 * @param elt
	 *            The element to search for.
	 * @return True iff the element is contained in the array.
	 */
	public boolean contains(T elt) {
		return findIndex(elt).isPresent();
	}

	/******
	 * Find an element which is .equal to the given element and return it, or
	 * null if not found.
	 *
	 * @param elt
	 *            The element to search for.
	 * @return An element .equal to the given element, or null if none are
	 *         present
	 */
	public Optional<T> find(T elt) {
		return findIndex(elt).map(i -> elements.get(i));
	}

	/******
	 * Insert an item into the list, preserving the sorted property.
	 *
	 * @param item
	 *            the element to be inserted
	 */
	public void insert(T item) {
		int i = elements.size() - 1;
		elements.add(item);
		while (i >= 0 && item.compareTo(get(i)) < 0) {
			elements.set(i + 1, elements.get(i));
			i--;
		}
		elements.set(i + 1, item);
	}

	/******
	 * Wrapper method to remove the ith element from elements
	 *
	 * @param i
	 *            the index for removal
	 */
	public void remove(int i) {
		elements.remove(i);
	}

	/******
	 * Wrapper method to remove a given value from elements
	 *
	 * @param value
	 *            the item to remove
	 */
	public void remove(T item) {
		elements.remove(item);
	}

	@Override
	public Iterator<T> iterator() {
		return elements.iterator();
	}
}
