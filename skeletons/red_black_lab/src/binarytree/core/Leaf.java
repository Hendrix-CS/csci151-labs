package binarytree.core;

import java.util.Optional;

public class Leaf<K extends Comparable<K>> implements RBChild<K> {
    private RBParent<K> parent;

    public Leaf(RBParent<K> parent) {
        this.parent = parent;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public RedBlack getColor() {
        return RedBlack.BLACK;
    }

    @Override
    public int height() {
        return 0;
    }

    @Override
    public int blackHeight() {
        return 0;
    }

    @Override
    public K getKey()
    {
        return null;
    }

    @Override
    public void insertFixup()
    {

    }

    @Override
    public void setParent(RBParent<K> parent) {
        this.parent = parent;
    }

    @Override
    public boolean hasChild(RBNode<K> candidate) {
        return false;
    }

    @Override
    public void showTree(int depth, StringBuilder shower) {

    }
}
