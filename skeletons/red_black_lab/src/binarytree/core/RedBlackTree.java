package binarytree.core;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Queue;
import java.util.function.Function;

public class RedBlackTree<K extends Comparable<K>> implements RBParent<K> {
    private RBParent<K> root = new Leaf<>(this);

    public RedBlackTree() {}

    public int size() {
        return root.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public Optional<K> getMin() {
        return root.getMin();
    }

    public Optional<K> getMax() {
        return root.getMax();
    }

    public int height() {
        return root.height();
    }

    public Optional<RBNode<K>> getRoot() {return root.asNode();}

    public void insert(K key) {
        if (root.asNode().isEmpty()) {
            root = new RBNode<>(key, this);
            root.insertFixup();
        } else {
            root.asNode().get().insert(key);
        }
    }

    public Optional<K> find(K key) {
        return root.asNode().flatMap(n -> n.find(key));
    }

    @Override
    public boolean hasChild(RBNode<K> candidate) {
        return root == candidate;
    }

    @Override
    public void changeChild(RBChild<K> oldChild, RBChild<K> newChild) {
        if (root == oldChild) {
            root = newChild;
        } else {
            throw new IllegalArgumentException("That wasn't the root.");
        }
    }

    @Override
    public Optional<RBNode<K>> asNode() {
        return Optional.empty();
    }

    public String showTree() {
        if (root.asNode().isPresent()) {
            return root.asNode().get().showTree();
        } else {
            return "";
        }
    }

    public void assertInvariants() {
        if (root.asNode().isPresent()) {
            root.asNode().get().assertSearchTree();
            assert root.getColor() == RedBlack.BLACK;
            root.asNode().get().assertNoRedRed();
            int height = root.height();
            assert height <= (int)(2 * Math.log(root.size())) + 1;
            assert root.blackHeight() <= height;
        }
    }

    private class TreeQueueEntry {
        RBNode<K> node;
        int level;

        TreeQueueEntry(RBNode<K> node, int level) {
            this.node = node;
            this.level = level;
        }
    }

    public ArrayList<ArrayList<ArrayList<NodeColor<K>>>> levelOrder() {
        ArrayList<ArrayList<ArrayList<NodeColor<K>>>> levels = new ArrayList<>();
        if (root.asNode().isEmpty()) {return levels;}

        levels.add(new ArrayList<>());
        Queue<TreeQueueEntry> q = new ArrayDeque<>();
        q.add(new TreeQueueEntry(root.asNode().get(), 0));

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
            for (NodeColor<K> key: entry) {
                if (key.key() != null) {
                    return false;
                }
            }
        }
        return true;
    }

    private ArrayList<NodeColor<K>> createEntryFrom(TreeQueueEntry node, Queue<TreeQueueEntry> q) {
        ArrayList<NodeColor<K>> entry = new ArrayList<>();
        if (node.node.asNode().isPresent()) {
            entry.add(new NodeColor<>(node.node.asNode().get().getKey(), node.node.getColor()));
        }
        addChildOf(entry, node, q, n -> n.node.getLeft());
        addChildOf(entry, node, q, n -> n.node.getRight());
        return entry;
    }

    private void addChildOf(ArrayList<NodeColor<K>> entry, TreeQueueEntry node, Queue<TreeQueueEntry> q, Function<TreeQueueEntry,Optional<RBNode<K>>> getter) {
        Optional<RBNode<K>> child = getter.apply(node);
        if (child.isPresent()) {
            q.add(new TreeQueueEntry(child.get(), node.level + 1));
            entry.add(new NodeColor<>(child.get().getKey(), child.get().getColor()));
        } else {
            q.add(new TreeQueueEntry(new RBNode<>(null, node.node), node.level + 1));
        }
    }
}