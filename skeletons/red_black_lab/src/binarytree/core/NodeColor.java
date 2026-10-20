package binarytree.core;

public class NodeColor<K> {
    public NodeColor(K key, RedBlack color) {
        this.color = color;
        this.key = key;
    }

    public K key;
    public RedBlack color;
}
