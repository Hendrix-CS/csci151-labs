package binarytree.core.nodes;

import binarytree.core.Child;
import binarytree.core.RedBlack;

public interface TreeNode<K extends Comparable<K>>
{
    TreeNode<K> getParent();
    void setParent(TreeNode<K> parent);
    TreeNode<K> getChild(Child child);
    Child whichChild(TreeNode<K> node);
    void setChild(Child child, TreeNode<K> node);
    RedBlack getColor();
    void setColor(RedBlack color);
    void insert(K value);
    K getValue();
    void setValue(K value);
    int size();
    int height();
    void insertFixup();
    int blackHeight();
    void checkTree();
    void leftRotate();
    void rightRotate();

    default TreeNode<K> getLeft() {
        return getChild(Child.LEFT);
    }

    default TreeNode<K> getRight() {
        return getChild(Child.RIGHT);
    }

    default boolean hasLeft() {
        return !getLeft().isLeaf();
    }

    default boolean hasRight() {
        return !getRight().isLeaf();
    }

    default void setLeft(TreeNode<K> left) {
        setChild(Child.LEFT, left);
    }

    default void setRight(TreeNode<K> right) {
        setChild(Child.RIGHT, right);
    }

    default boolean hasRedChild() {
        return getLeft().getColor() == RedBlack.RED || getRight().getColor() == RedBlack.RED;
    }

    default boolean hasBlackChild() {
        return getLeft().getColor() == RedBlack.BLACK;
    }

    default boolean hasChild(TreeNode<K> node) {
        return getLeft() == node || getRight() == node;
    }

    default boolean isRoot() {
        return getParent() == null;
    }

    default boolean isLeaf() {
        return this instanceof BlackLeaf;
    }

    default int numChildren() {
        return (getLeft().isLeaf() ? 0 : 1) + (getRight().isLeaf() ? 0 : 1);
    }

    default void changeChild(TreeNode<K> oldChild, TreeNode<K> newChild) {
        if (oldChild == getLeft()) {
            setLeft(newChild);
        }
        else if (oldChild == getRight()) {
            setRight(newChild);
        }
        else {
            throw new IllegalStateException("Cannot change child because oldChild is not a child of this node!");
        }
    }

    default boolean isEmpty() {
        return !hasLeft() && !hasRight();
    }

    default K getMin() {
        if (getLeft().isLeaf()) {
            return getValue();
        }
        else {
            return getLeft().getValue();
        }
    }

    default K getMax() {
        if (getRight().isLeaf()) {
            return getValue();
        }
        else {
            return getRight().getValue();
        }
    }
}
