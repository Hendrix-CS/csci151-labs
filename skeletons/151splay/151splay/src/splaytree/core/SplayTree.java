package splaytree.core;

import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Optional;
import java.util.Queue;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;


public class SplayTree<T extends Comparable<T>> {
	private Optional<TreeNode<T>> root;
    private int comparisons = 0;
    private int numContains = 0;
	
	public SplayTree() {
		root = Optional.empty();
	}

    private void reset() {
        comparisons = 0;
        numContains = 0;
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
		if (root.isPresent()) {
            ArrayList<TreeNode<T>> ancestry = new ArrayList<>();
            if (root.get().containsAncestry(value, ancestry)) {
                comparisons += ancestry.size();
                numContains += 1;
                root = Optional.of(splayed(ancestry));
                return true;
            }
		}
		return false;
	}

    public static <T extends Comparable<T>> TreeNode<T> splayed(ArrayList<TreeNode<T>> nodes) {
        // TODO: Step 5
        // - Loop backwards through the array of nodes, counting by two
        //   - Check the side of the current node of its parent.
        //   - Check the side of the parent node of the grandparent.
        //   - Depending on the combination of sides, invoke the proper rotation:
        //     - Left child, left grandchild? ZigZig
        //     - Left child, right grandchild? ZigZag
        //     - Right child, left grandchild? ZagZig
        //     - Right child, right grandchild? ZagZag
        //   - Change the grandparent's entry in the nodes array to the result of rotation.
        //   - If the grandparent is not the root, change the appropriate side of its parent
        //     to point to the newly rotated node.
        // - If we have not reached the root
        //   - We need to perform either a left or right rotation to get the current node to
        //     the root.
        //   - If it is a left child of the root, rotate right.
        //   - if it is a right child of the root, rotate left.
        //
        // - When finished with all of the above, return element zero of nodes.
        return nodes.getFirst();
    }

    public double amortizedComparisons() {
        if (numContains > 0) {
            return (double)comparisons / (double)numContains;
        } else {
            return 0.0;
        }
    }
	
	public void insert(T value) {
		if (root.isPresent()) {
			root.get().insert(value);
		} else {
			root = Optional.of(new TreeNode<>(value));
		}
        reset();
	}
	
	public void remove(T value) {
		if (root.isPresent()) {
			root = root.get().remove(value);
            reset();
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
