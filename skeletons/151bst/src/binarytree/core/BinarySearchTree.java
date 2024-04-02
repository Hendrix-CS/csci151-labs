package binarytree.core;

import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Optional;
import java.util.Queue;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public class BinarySearchTree<T extends Comparable<T>> {
	private Optional<TreeNode<T>> root;
	
	public BinarySearchTree() {
		root = Optional.empty();
	}
	
	public ArrayList<T> inorder() {
		return traverse((node, op) -> node.inOrder(op));
	}
	
	public ArrayList<T> preorder() {
		return traverse((node, op) -> node.preOrder(op));
	}
	
	public ArrayList<T> postorder() {
		return traverse((node, op) -> node.postOrder(op));
	}
	
	public ArrayList<T> traverse(BiConsumer<TreeNode<T>, Consumer<T>> traversal) {
		ArrayList<T> result = new ArrayList<>();
		root.ifPresent(t -> traversal.accept(t, v -> result.add(v)));
		return result;
	}
	
	public String stringTraverse(BiConsumer<TreeNode<T>, Consumer<T>> traversal) {
		StringBuilder result = new StringBuilder();
		root.ifPresent(t -> traversal.accept(t, v -> result.append(v.toString() + " ")));
		return result.toString().trim();
	}
	
	public String preorderString() {
		return stringTraverse((node, op) -> node.preOrder(op));
	}
	
	public String inorderString() {
		return stringTraverse((node, op) -> node.inOrder(op));
	}
	
	public String postorderString() {
		return stringTraverse((node, op) -> node.postOrder(op));
	}
	
	public int size() {
		return root.isPresent() ? root.get().size() : 0;
	}
	
	public Optional<Integer> height() {
		return root.isPresent() ? Optional.of(root.get().height()) : Optional.empty();
	}
	
	public Optional<T> getMin() {
		return root.isPresent() ? Optional.of(root.get().getMin()) : Optional.empty();
	}
	
	public Optional<T> getMax() {
		return root.isPresent() ? Optional.of(root.get().getMax()) : Optional.empty();
	}
	
	public boolean contains(T value) {
		return root.isPresent() ? root.get().contains(value) : false;
	}
	
	public void insert(T value) {
		if (root.isPresent()) {
			root.get().insert(value);
		} else {
			root = Optional.of(new TreeNode<>(value));
		}
	}
	
	public void remove(T value) {
		if (root.isPresent()) {
			root = root.get().remove(value);
		}
	}

	public void leftRotateAt(T value) {
		if (root.isPresent()) {
			root = root.get().leftRotateAt(value);
		}
	}
	
	public void rightRotateAt(T value) {
		if (root.isPresent()) {
			root = root.get().rightRotateAt(value);
		}
	}

	private class TreeQueueEntry {
		TreeNode<T> node;
		int level;
		
		TreeQueueEntry(TreeNode<T> node, int level) {
			this.node = node;
			this.level = level;
		}
	}
	
	public ArrayList<ArrayList<ArrayList<T>>> levelOrder() {
		ArrayList<ArrayList<ArrayList<T>>> levels = new ArrayList<>();
		if (!root.isPresent()) {return levels;}
		
		levels.add(new ArrayList<>());
		Queue<TreeQueueEntry> q = new ArrayDeque<>();
		q.add(new TreeQueueEntry(root.get(), 0));
		
		for (;;) {
			TreeQueueEntry node = q.remove();
			if (node.level != levels.size() - 1) {
				if (allSentinels(levels.get(levels.size() - 1))) {
					levels.remove(levels.size() - 1);
					return levels;
				}
				levels.add(new ArrayList<>());
			}
			levels.get(levels.size() - 1).add(createEntryFrom(node, q));
		}
	}
	
	private ArrayList<T> createEntryFrom(TreeQueueEntry node, Queue<TreeQueueEntry> q) {
		ArrayList<T> entry = new ArrayList<>();
		if (node.node.get() != null) {
			entry.add(node.node.get());
		}
		addChildOf(entry, node, q, n -> n.node.getLeft());
		addChildOf(entry, node, q, n -> n.node.getRight());
		return entry;
	}
	
	private void addChildOf(ArrayList<T> entry, TreeQueueEntry node, Queue<TreeQueueEntry> q, Function<TreeQueueEntry,Optional<TreeNode<T>>> getter) {
		if (getter.apply(node).isPresent()) {
			q.add(new TreeQueueEntry(getter.apply(node).get(), node.level + 1));
			entry.add(getter.apply(node).get().get());
		} else {
			q.add(new TreeQueueEntry(new TreeNode<>(null), node.level + 1));
		}
	}
	
	private static <T> boolean allSentinels(ArrayList<ArrayList<T>> entries) {
		for (ArrayList<T> entry: entries) {
			if (entry.size() > 0) {
				return false;
			}
		}
		return true;
	}
}
