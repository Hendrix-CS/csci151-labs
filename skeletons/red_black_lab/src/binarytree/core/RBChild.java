package binarytree.core;

import java.util.Optional;

public interface RBChild<K extends Comparable<K>> {
    boolean hasChild(RBNode<K> candidate);
    RedBlack getColor();

    void setParent(RBParent<K> parent);
    int size();
    int height();
    int blackHeight();
    K getKey();

    default Optional<RBNode<K>> asNode() {
        if (this instanceof RBNode<K> node) {
            return Optional.of(node);
        } return Optional.empty();
    }
    void showTree(int depth, StringBuilder shower);
}