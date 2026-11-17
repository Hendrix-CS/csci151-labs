package binarytree.core.nodes;

import binarytree.core.Child;
import binarytree.core.RedBlack;
import binarytree.core.RedBlackTree;

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

    default boolean hasChild(TreeNode<K> node) {
        return getLeft() == node || getRight() == node;
    }

    default boolean isRoot() {
        return getParent() instanceof RedBlackTree<K>;
    }

    default boolean isLeaf() {
        return this instanceof NullNode<K>;
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

    default TreeNode<K> getGrandparent() {
        throw new UnsupportedOperationException("Cannot get grandparent!");
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

    default TreeNode<K> getPibling() {
        throw new UnsupportedOperationException("Cannot get pibling!");
    }

    default void printTree() {
        printTree(this, "", true);
    }

    default void printTree(TreeNode<K> node, String prefix, boolean isTail) {
        if (node.isLeaf()) {
            return;
        }

        if (!node.getRight().isLeaf()) {
            printTree(node.getRight(), prefix + (isTail ? "│   " : "    "), false);
        }

        System.out.println(prefix + (isTail ? "└── " : "┌── ")
                + node.getValue() + "(" + node.getColor() + ")");

        if (!node.getLeft().isLeaf()) {
            printTree(node.getLeft(), prefix + (isTail ? "    " : "│   "), true);
        }
    }
}
