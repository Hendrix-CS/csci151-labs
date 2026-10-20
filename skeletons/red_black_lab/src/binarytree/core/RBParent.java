package binarytree.core;

import java.util.Optional;

public interface RBParent<K extends Comparable<K>> extends RBChild<K> {
    int numChildren();
    void insertFixup();
    void changeChild(RBChild<K> oldChild, RBChild<K> newChild);

    Optional<RBNode<K>> getLeft();
    Optional<RBNode<K>> getRight();

    default boolean isSentinel() {
        return asNode().isEmpty();
    }
    default Optional<K> getMin() {
        return getLeft().isEmpty() ? Optional.of(getKey()) : getLeft().get().getMin();
    }

    default Optional<K> getMax() {
        return getRight().isEmpty() ? Optional.of(getKey()) : getRight().get().getMax();
    }
}
