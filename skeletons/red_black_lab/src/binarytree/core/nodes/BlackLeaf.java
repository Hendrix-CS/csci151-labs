package binarytree.core.nodes;

import binarytree.core.Child;
import binarytree.core.RedBlack;

public class BlackLeaf<K extends Comparable<K>> implements TreeNode<K>
{
    private TreeNode<K> parent;

    public BlackLeaf(TreeNode<K> parent)
    {
        this.parent = parent;
    }

    @Override
    public TreeNode<K> getParent()
    {
        return parent;
    }

    @Override
    public void setParent(TreeNode<K> parent)
    {
        this.parent = parent;
    }

    @Override
    public TreeNode<K> getChild(Child child)
    {
        throw new UnsupportedOperationException("Cannot get child of a leaf!");
    }

    @Override
    public Child whichChild(TreeNode<K> node)
    {
        throw new UnsupportedOperationException("Cannot get child of a leaf!");
    }

    @Override
    public void setChild(Child child, TreeNode<K> node)
    {
        throw new UnsupportedOperationException("Cannot set child of a leaf!");
    }

    @Override
    public RedBlack getColor()
    {
        return RedBlack.BLACK;
    }

    @Override
    public void setColor(RedBlack color)
    {
        throw new UnsupportedOperationException("Cannot set color of a leaf!");
    }

    @Override
    public void insert(K value)
    {
        throw new UnsupportedOperationException("Cannot insert into a leaf!");
    }

    @Override
    public K getValue()
    {
        throw new UnsupportedOperationException("Cannot get value of a leaf!");
    }

    @Override
    public void setValue(K value)
    {
        throw new UnsupportedOperationException("Cannot set value of a leaf!");
    }

    @Override
    public int size()
    {
        return 0;
    }

    @Override
    public int height()
    {
        return 0;
    }

    @Override
    public void insertFixup()
    {

    }

    @Override
    public int blackHeight()
    {
        return 0;
    }

    @Override
    public void checkTree()
    {

    }

    @Override
    public void leftRotate()
    {

    }

    @Override
    public void rightRotate()
    {

    }
}
