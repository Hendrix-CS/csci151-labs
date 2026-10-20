package binarytree.core;

import java.util.Optional;

public interface RBChild<K extends Comparable<K>> extends RBParent<K> {
    int size();
    int numChildren();
    RedBlack getColor();
    int height();
    int blackHeight();
    void setParent(RBParent<K> parent);
    void insertFixup();
    void showTree(int depth, StringBuilder shower);
    Optional<K> getMin();
    Optional<K> getMax();

    Optional<RBNode<K>> getLeft();
    Optional<RBNode<K>> getRight();

    default boolean isSentinel() {
        return asNode().isEmpty();
    }
}
