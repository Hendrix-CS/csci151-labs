package binarytree.core.nodes;

import binarytree.core.Child;
import binarytree.core.RedBlack;

public class RBNode<K extends Comparable<K>> implements TreeNode<K> {
    private RedBlack color;
    private K value;
    private TreeNode<K> left, right;
    private TreeNode<K> parent;

    public RBNode(K value, TreeNode<K> parent) {
        this.value = value;
        this.parent = parent;

        left = new BlackLeaf<>(this);
        right = new BlackLeaf<>(this);
        color = RedBlack.RED;
    }

    @Override
    public TreeNode<K> getParent() {
        return parent;
    }

    @Override
    public void setParent(TreeNode<K> parent) {
        this.parent = parent;
    }

    @Override
    public TreeNode<K> getChild(Child child) {
        return switch (child) {
            case LEFT -> left;
            case RIGHT -> right;
        };
    }

    @Override
    public Child whichChild(TreeNode<K> node) {
        if (node == left) {
            return Child.LEFT;
        }
        else if (node == right) {
            return Child.RIGHT;
        }

        throw new IllegalStateException("Node does not belong to parent!");
    }

    @Override
    public void setChild(Child child, TreeNode<K> node) {
        if (node == null) {
            throw new IllegalStateException("Cannot set child to null!");
        }

        switch (child) {
            case LEFT -> left = node;
            case RIGHT -> right = node;
        }
    }

    @Override
    public RedBlack getColor() {
        return color;
    }

    @Override
    public void setColor(RedBlack color) {
        this.color = color;
    }

    @Override
    public void insert(K value)
    {
        int compare = value.compareTo(this.value);

        if (compare == 0) {
            this.value = value;
        }
        else if (compare < 0) {
            if (!this.left.isLeaf()) {
                this.left.insert(value);
            }
            else {
                this.left = new RBNode<>(value, this);
                this.left.insertFixup();
            }
        }
        else {
            if (!this.right.isLeaf()) {
                this.right.insert(value);
            }
            else {
                this.right = new RBNode<>(value, this);
                this.right.insertFixup();
            }
        }
    }

    @Override
    public K getValue() {
        return value;
    }

    @Override
    public void setValue(K value) {
        this.value = value;
    }

    @Override
    public int size() {
        return 1 + left.size() + right.size();
    }

    @Override
    public int height() {
        if (left.isLeaf() && right.isLeaf()) {
            return 0;
        }
        else {
            return 1 + Math.max(left.height(), right.height());
        }
    }

    @Override
    public int blackHeight() {
        int leftHeight = left.blackHeight();
        int rightHeight = right.blackHeight();

        if (leftHeight != rightHeight) {
            throw new IllegalStateException("Both sides of the tree's black height must be the same!");
        }

        return leftHeight + (color == RedBlack.RED ? 0 : 1);
    }

    private void parentChildSymmetry() {
        if (!this.parent.hasChild(this)) {
            throw new IllegalStateException("Node is not one of it's parent's children!");
        }
    }

    private void searchTree() {
        if (!left.isLeaf()) {
            if (left.getValue().compareTo(value) > 0) {
                throw new IllegalStateException("Left is greater than value!");
            }
        }

        if (!right.isLeaf()) {
            if (right.getValue().compareTo(value) < 0) {
                throw new IllegalStateException("Right is less than value!");
            }
        }
    }

    @Override
    public void checkTree() {
        if (!isRoot()) {
            parentChildSymmetry();
        }
        searchTree();

        if (redRedViolation()) {
            throw new IllegalStateException("A red node cannot have a child who is red!");
        }

        left.checkTree();
        right.checkTree();
    }

    /*
        TODO: This is the code you need to implement!
     */

    public boolean redRedViolation() {
        return /*getColor() == RedBlack.RED && getParent().getColor() == RedBlack.RED;*/ false;
    }

    public void insertFixup() {
        // TODO: Your code here
    }

    private void blackPiblingFixup(TreeNode<K> parent, TreeNode<K> grandparent) {
        // TODO: Your code here
    }

    private void zigZigFixup(RBNode<K> parent, RBNode<K> grandparent) {
        // TODO: Your code here
    }

    private void zigZagFixup(RBNode<K> parent, RBNode<K> grandparent) {
        // TODO: Your code here
    }

    private void zagZigFixup(RBNode<K> parent, RBNode<K> grandparent) {
        // TODO: Your code here
    }

    private void zagZagFixup(RBNode<K> parent, RBNode<K> grandparent) {
        // TODO: Your code here
    }

    private void redPiblingFixup() {
        // TODO: Your code here
    }

    public void leftRotate() {
        // TODO: Your code here
    }

    public void rightRotate() {
        // TODO: Your code here
    }
}
