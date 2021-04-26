package trie.core;

public class Association<K extends Comparable<K>, V> implements Comparable<Association<K, V>> {
	private K key;
	private V value;

	public Association(K key, V value) {
		this.key = key;
		this.value = value;
	}

	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "(" + key.toString() + ", " + value.toString() + ")";
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object other) {
		Association<K,V> otherAssoc = (Association<K,V>)other;
		return otherAssoc.getKey().equals(key);
	}

	@Override
	public int compareTo(Association<K, V> o) {
		return key.compareTo(o.key);
	}
}
