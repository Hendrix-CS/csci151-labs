package tictactoe.learn;

public class HashNode<K,V> {
	private K key;
	private V value;
	private HashNode<K,V> next;
	
	public HashNode(K key, V value, HashNode<K,V> next) {
		this.key = key;
		this.value = value;
		this.next = next;
	}
	
	public HashNode(K key, V value) {
		this(key, value, null);
	}

	public K getKey() {
		return key;
	}
	
	public V getValue() {
		return value;
	}
	
	public HashNode<K,V> getNext() {
		return next;
	}
	
	public void setValue(V newValue) {
		this.value = newValue;
	}
	
	@Override
	public String toString() {
		return (key + ":" + value);
	}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof HashNode<?,?>) {
			@SuppressWarnings("unchecked")
			HashNode<K,V> that = (HashNode<K,V>)other;
			return this.key.equals(that.key);
		}
		return false;
	}
}
