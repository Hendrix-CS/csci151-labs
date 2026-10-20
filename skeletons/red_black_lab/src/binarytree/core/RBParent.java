package binarytree.core;

import java.util.Optional;

public interface RBParent<K extends Comparable<K>> {
    boolean hasChild(RBNode<K> candidate);

    void changeChild(RBChild<K> oldChild, RBChild<K> newChild);

    Optional<RBNode<K>> asNode();
}