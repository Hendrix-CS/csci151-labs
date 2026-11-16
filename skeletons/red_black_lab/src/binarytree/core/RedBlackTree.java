package binarytree.core;

import binarytree.core.nodes.RBNode;
import binarytree.core.nodes.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Queue;
import java.util.function.Function;

public class RedBlackTree<K extends Comparable<K>> implements TreeNode<K> {
    private final TreeNode<K> root = new RBNode<>(null, null);

    public RedBlackTree()
    {
        root.setColor(RedBlack.BLACK);
    }

    @Override
    public TreeNode<K> getParent() {
        return root.getParent();
    }

    @Override
    public void setParent(TreeNode<K> parent) {
        root.setParent(parent);
    }

    @Override
    public TreeNode<K> getChild(Child child) {
        return root.getChild(child);
    }

    @Override
    public Child whichChild(TreeNode<K> node) {
        return root.whichChild(node);
    }

    @Override
    public void setChild(Child child, TreeNode<K> node) {
        root.setChild(child, node);
    }

    @Override
    public RedBlack getColor() {
        return root.getColor();
    }

    @Override
    public void setColor(RedBlack color) {
        root.setColor(color);
    }

    @Override
    public void insert(K value) {
        if (root.getValue() == null) {
            root.setValue(value);
        }
        else {
            root.insert(value);
        }

        root.insertFixup();
    }

    @Override
    public K getValue() {
        return root.getValue();
    }

    @Override
    public void setValue(K value) {
        root.setValue(value);
    }

    @Override
    public int size() {
        return root.size();
    }

    @Override
    public int height() {
        return root.height();
    }

    @Override
    public void insertFixup() {
        root.insertFixup();
    }

    @Override
    public int blackHeight() {
        return root.blackHeight();
    }

    @Override
    public void checkTree() {
        root.checkTree();
    }

    @Override
    public void leftRotate() {
        root.leftRotate();
    }

    @Override
    public void rightRotate() {
        root.rightRotate();
    }

    @Override
    public boolean isEmpty() {
        return root.getValue() == null && root.isEmpty();
    }

    public void assertInvariants() {
        if (!root.isEmpty()) {
            root.checkTree();

            if (root.getColor() != RedBlack.BLACK)
            {
                throw new IllegalStateException("Root node must be black!");
            }

            int height = root.height();
            int max = (int) (2 * Math.log(root.size()));

            if (height >= max)
            {
                throw new IllegalStateException("Tree is unbalanced!");
            }

            if (root.blackHeight() > height)
            {
                throw new IllegalStateException("Black Height is higher than height of tree!");
            }
        }
    }

    private class TreeQueueEntry {
        TreeNode<K> node;
        int level;

        TreeQueueEntry(TreeNode<K> node, int level) {
            this.node = node;
            this.level = level;
        }
    }

    public ArrayList<ArrayList<ArrayList<NodeColor<K>>>> levelOrder() {
        ArrayList<ArrayList<ArrayList<NodeColor<K>>>> levels = new ArrayList<>();
        if (isEmpty()) return levels;

        levels.add(new ArrayList<>());
        Queue<TreeQueueEntry> q = new ArrayDeque<>();
        q.add(new TreeQueueEntry(root, 0));

        for (;;) {
            TreeQueueEntry node = q.remove();
            if (node.level != levels.size() - 1) {
                if (allSentinels(levels.getLast())) {
                    levels.removeLast();
                    return levels;
                }
                levels.add(new ArrayList<>());
            }
            levels.getLast().add(createEntryFrom(node, q));
        }
    }

    private static <K> boolean allSentinels(ArrayList<ArrayList<NodeColor<K>>> entries) {
        for (ArrayList<NodeColor<K>> entry: entries) {
            for (NodeColor<K> key : entry) {
                if (key.key() != null) {
                    return false;
                }
            }
        }
        return true;
    }

    private ArrayList<NodeColor<K>> createEntryFrom(TreeQueueEntry node, Queue<TreeQueueEntry> q) {
        ArrayList<NodeColor<K>> entry = new ArrayList<>();
        if (!node.node.isLeaf()) {
            entry.add(new NodeColor<>(node.node.getValue(), node.node.getColor()));
        }
        addChildOf(entry, node, q, n -> n.node.getLeft());
        addChildOf(entry, node, q, n -> n.node.getRight());
        return entry;
    }

    private void addChildOf(ArrayList<NodeColor<K>> entry, TreeQueueEntry node, Queue<TreeQueueEntry> q, Function<TreeQueueEntry, TreeNode<K>> getter) {
        TreeNode<K> child = getter.apply(node);
        if (!child.isLeaf()) {
            q.add(new TreeQueueEntry(child, node.level + 1));
            entry.add(new NodeColor<>(child.getValue(), child.getColor()));
        } else {
            q.add(new TreeQueueEntry(new RBNode<>(null, node.node), node.level + 1));
        }
    }
}
