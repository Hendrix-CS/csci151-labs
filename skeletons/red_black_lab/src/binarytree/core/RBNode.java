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
        getLeft().ifPresent(node -> {
            if (node.getKey().compareTo(key) > 0)
            {
                throw new IllegalStateException("Left node is greater than it's parent!");
            }

            node.assertSearchTree();
        });

        getRight().ifPresent(node -> {
            if (node.getKey().compareTo(key) < 0)
            {
                throw new IllegalStateException("Right node is less than it's parent!");
            }

            node.assertSearchTree();
        });
    }

    public void assertNoRedRed() {
        if (color == RedBlack.RED && hasRedChild()) {
            throw new IllegalStateException("Red node has a red child!");
        }

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
        return parent instanceof RedBlackTree<K>;
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
            getLeft().ifPresentOrElse(node -> node.insert(key), () -> {
                left = new RBNode<>(key, this);
                left.insertFixup();
            });
        } else {
            getRight().ifPresentOrElse(node -> node.insert(key), () -> {
                right = new RBNode<>(key, this);
                right.insertFixup();
            });
        }
    }

    private Child whichChild(RBNode<K> child) {
        if (child == left) {
            return Child.LEFT;
        } else if (child == right) {
            return Child.RIGHT;
        } else {
            throw new IllegalArgumentException("Specified child not present in this node!");
        }
    }

    // needed for a test
    public void setColor(RedBlack color)
    {
        this.color = color;
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

        return switch (comp)
        {
            case -1 -> getLeft().flatMap(node -> node.findNode(key));
            case 1 -> getRight().flatMap(node -> node.findNode(key));
            default -> Optional.of(this);
        };
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
            throw new IllegalArgumentException("Tried to update an oldChild that is not present in the specified node!");
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