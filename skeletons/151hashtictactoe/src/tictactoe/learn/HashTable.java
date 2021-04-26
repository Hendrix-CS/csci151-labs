package tictactoe.learn;

import java.util.ArrayList;
import java.util.Optional;

@SuppressWarnings("unchecked")
public class HashTable<K,V> {
	private HashNode<K,V>[] table;
	private int size;
	private final double MAX_LOAD = 0.75;
    
    public HashTable() {
		initTable(16);
    }
	
	private void initTable(int size) {
		table = (HashNode<K,V>[])new HashNode[size];
		this.size = 0;
	}
	
	public int size() {
		return size;
	}
	
	int capacity() {
		return table.length;
	}
	
	public double load() {
		return (double)size() / capacity();
	}

	/**
	 * @param key
	 * @return Index associated with key, given the size of the table.
	 */
	int index(K key) {
		// TODO Step 1
		return -1;
	}

	/**
	 * Stores a value in the HashTable for the given key.
	 * Implement the separate chaining method of resolving
	 * collisions in the table.
	 * @param key
	 * @param value
	 */
	public void put(K key, V value) {
		// TODO Step 2

		// TODO Later add Step 5 for resizing
	}

	/**
	 * Retrieves the value stored in the table when given a key, if present.
	 */
	public Optional<V> get(K key) {
		// TODO Step 3
		return Optional.empty();
	}
	
	/**
	 * Returns a list of all the keys found in the HashTable.
	 * @return
	 */
	public ArrayList<K> allKeys() {
		ArrayList<K> all = new ArrayList<>();
		// TODO Step 4
		return all;
	}
	
}
