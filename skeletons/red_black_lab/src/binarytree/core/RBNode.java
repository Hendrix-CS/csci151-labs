package binarytree.core;

import java.util.Optional;

public class RBNode<K extends Comparable<K>> implements RBParent<K> {
    private RedBlack color;
    private K key;
    private RBChild<K> left, right;
    private RBParent<K> parent;

    public RBNode(K key, RBParent<K> parent) {
        this.key = key;
        this.parent = parent;
        left = new Leaf<>(this);
        right = new Leaf<>(this);
        color = RedBlack.RED;
    }

    @Override
    public Optional<RBNode<K>> asNode() {return Optional.of(this);}

    @Override
    public boolean hasChild(RBNode<K> candidate) {
        return left == candidate || right == candidate;
    }

    public int size() {
        return 1 + left.size() + right.size();
    }

    public int numChildren() {
        return (left.isLeaf() ? 0 : 1) + (right.isLeaf() ? 0 : 1);
    }

    public K getKey() {
        return key;
    }

    public RedBlack getColor() {
        return color;
    }

    @Override
    public int height() {
        if (left.isLeaf() && right.isLeaf()) {
            return 0;
        } else {
            int lh = left.height();
            int rh = right.height();

            return Math.max(lh, rh) + 1;
        }
    }

    public void assertSearchTree() {
        if (getLeft().isPresent()) {
            assert getLeft().get().key.compareTo(key) <= 0;
            getLeft().get().assertSearchTree();
        }
        if (getRight().isPresent()) {
            assert getRight().get().key.compareTo(key) >= 0;
            getRight().get().assertSearchTree();
        }
    }

    public void assertNoRedRed() {
        assert color != RedBlack.RED || !hasRedChild();
        if (getLeft().isPresent()) {
            getLeft().get().assertNoRedRed();
        }
        if (getRight().isPresent()) {
            getRight().get().assertNoRedRed();
        }
    }

    public int blackHeight() {
        int leftHeight = left.blackHeight();
        int rightHeight = right.blackHeight();
        assert leftHeight == rightHeight;
        return leftHeight + (color == RedBlack.RED ? 0 : 1);
    }

    @Override
    public void setParent(RBParent<K> parent) {
        this.parent = parent;
    }

    public void assertParentChildSymmetry() {
        assert parent.hasChild(this);
        if (getLeft().isPresent()) {
            getLeft().get().assertParentChildSymmetry();
        }
        if (getRight().isPresent()) {
            getRight().get().assertParentChildSymmetry();
        }
    }

    public boolean isRoot() {
        return parent.asNode().isEmpty();
    }

    public boolean hasRedChild() {
        return left.getColor() == RedBlack.RED || right.getColor() == RedBlack.RED;
    }

    public boolean hasBlackChild() {
        return left.getColor() == RedBlack.BLACK || right.getColor() == RedBlack.BLACK;
    }

    public void insert(K key) {
        int comp = key.compareTo(this.key);
        if (comp == 0) {
            this.key = key;
        } else if (comp < 0) {
            if (getLeft().isEmpty()) {
                left = new RBNode<>(key, this);
                left.insertFixup();
            } else {
                getLeft().get().insert(key);
            }

            getLeft().ifPresentOrElse(node -> node.insert(key), () -> {
                left = new RBNode<>(key, this);
                left.insertFixup();
            });
        } else {
            if (getRight().isEmpty()) {
                right = new RBNode<>(key, this);
                right.insertFixup();
            } else {
                getRight().get().insert(key);
            }
        }
    }

    private Child whichChild(RBNode<K> child) {
        if (child == left) {
            return Child.LEFT;
        } else if (child == right) {
            return Child.RIGHT;
        } else {
            throw new IllegalArgumentException("Specified child not present");
        }
    }

    /*
        TODO: This is the code you need to implement!
     */

    public boolean redRedViolation() {
        // TODO: Your code here
        return false;
    }

    public void insertFixup() {
        // TODO: Your code here
    }

    private void blackPiblingFixup(RBNode<K> parent, RBNode<K> grandparent) {
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

    public Optional<K> find(K key) {
        return findNode(key).map(n -> n.key);
    }

    public Optional<RBNode<K>> findNode(K key) {
        int comp = key.compareTo(this.key);
        if (comp == 0) {
            return Optional.of(this);
        } else if (comp < 0) {
            return getLeft().isEmpty() ? Optional.empty() : getLeft().get().findNode(key);
        } else {
            return getRight().isEmpty() ? Optional.empty() : getRight().get().findNode(key);
        }
    }

    @Override
    public Optional<RBNode<K>> getLeft() {
        return left.asNode();
    }

    @Override
    public Optional<RBNode<K>> getRight() {
        return right.asNode();
    }

    @Override
    public void changeChild(RBChild<K> oldChild, RBChild<K> newChild) {
        if (left == oldChild) {
            left = newChild;
        } else if (right == oldChild) {
            right = newChild;
        } else {
            assert false;
        }
    }

    public String showTree() {
        StringBuilder shower = new StringBuilder();
        showTree(0, shower);
        return shower.toString();
    }

    public void showTree(int depth, StringBuilder shower) {
        assert depth >= 0;
        shower.repeat("\t", depth);
        shower.append(key);
        shower.append(':');
        shower.append(color);
        shower.append('\n');
        left.showTree(depth + 1, shower);
        right.showTree(depth + 1, shower);
    }
}