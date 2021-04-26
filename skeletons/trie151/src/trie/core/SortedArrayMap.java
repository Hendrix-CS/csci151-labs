package trie.core;

import java.util.Iterator;
import java.util.Optional;

/**
 * A mapping from keys to values, stored using a sorted array of Associations.
 * get is O(lg n), add and remove are O(n).
 *
 * @param <K>
 *            The type of keys
 * @param <V>
 *            The type of values
 */
public class SortedArrayMap<K extends Comparable<K>, V> implements Iterable<Association<K, V>> {

	private SortedArray<Association<K, V>> data;

	public SortedArrayMap() {
		data = new SortedArray<>();
	}

	/**
	 * Add a new key,value association to the map. If the given key already
	 * exists, its associated value will be replaced.
	 *
	 * @param key
	 *            The key to add or replace
	 * @param value
	 *            The value to associate with the key
	 */
	public void add(K key, V value) {
		Association<K, V> newAssoc = new Association<>(key, value);
		Optional<Association<K, V>> theAssoc = data.find(newAssoc);
		if (theAssoc.isPresent()) {
			theAssoc.get().setValue(value);
		} else {
			data.insert(newAssoc);
		} 
	}

	/**
	 * Remove the mapping for a given key, if it exists.
	 *
	 * @param key
	 *            The key to remove.
	 */
	public void remove(K key) {
		data.remove(new Association<K, V>(key, null));
	}

	/**
	 * Get the value associated to a given key.
	 *
	 * @param key
	 *            The key to look for
	 * @return The value associated to the key, or null if not found
	 */
	public Optional<V> get(K key) {
		return data.find(new Association<K, V>(key, null)).map(a -> a.getValue());
	}

	@Override
	public Iterator<Association<K, V>> iterator() {
		return data.iterator();
	}
	
	public int size() {
		return data.size();
	}
}
