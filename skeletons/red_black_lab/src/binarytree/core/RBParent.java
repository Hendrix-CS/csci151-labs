package binarytree.core;

import java.util.Optional;

public interface RBParent<K extends Comparable<K>> extends RBChild<K> {
    int numChildren();
    void changeChild(RBChild<K> oldChild, RBChild<K> newChild);

    Optional<RBNode<K>> getLeft();
    Optional<RBNode<K>> getRight();

    default Optional<K> getMin() {
        return getLeft().isEmpty() ? Optional.ofNullable(getKey()) : getLeft().get().getMin();
    }

    default Optional<K> getMax() {
        return getRight().isEmpty() ? Optional.ofNullable(getKey()) : getRight().get().getMax();
    }
}
