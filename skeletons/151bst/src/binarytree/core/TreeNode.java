package binarytree.core;

import java.util.Optional;
import java.util.function.Consumer;

public class TreeNode<T extends Comparable<T>> {
	private Optional<TreeNode<T>> left = Optional.empty();
	private Optional<TreeNode<T>> right = Optional.empty();
	private T value;

	public TreeNode(T value) {
		this.value = value;
	}

	public T get() {
		return value;
	}
	public Optional<TreeNode<T>> getLeft() {return left;}
	public Optional<TreeNode<T>> getRight() {return right;}
	public boolean isLeaf() {return left.isEmpty() && right.isEmpty();}

	public void insert(T target) {
		// TODO: Step 1
		// Insert a new node containing target, preserving the ordering.
		//
		// Compare the target parameter to this.value to determine which direction to go.
		// If the target is less than this.value, proceed with the left child.
		// If the target is greater than this.value, proceed with the right child.
		// If the child is empty, replace it with a new Optional<TreeNode>
		// containing the target.
		// Otherwise, call insert recursively on the child.

	}

	public boolean contains(T target) {
		// TODO: Step 1
		// Return true if target is present in the tree, false otherwise
		//
		// Compare the target parameter to this.value to determine which direction to go.
		// If the target is equal to this.value, return true
		// If the target is less than this.value, proceed with the left child.
		// If the target is greater than this.value, proceed with the right child.
		// If the child is empty, return false.
		// Otherwise, call contains recursively on the child.

		return false;
	}

	public int size() {
		// TODO: Step 2
		// Return the number of nodes in the tree, which is 1 + the number of
		// nodes in the child trees.

		return 0;
	}

	public T getMin() {
		// TODO: Step 2
		// Return the smallest (left-most) value in the tree.

		return null;
	}

	public T getMax() {
		// TODO: Step 2
		// Return the largest (right-most) value in the tree.

		return null;
	}

	public int height() {
		// TODO: Step 2
		// Return the height of the tree, which is the maximum number of
		// edges between the root and any leaf.

		return 0;


	}

	public void preOrder(Consumer<T> op) {
		// TODO: Step 3
		// Perform a preorder traversal using op.accept(value)

	}

	public void postOrder(Consumer<T> op) {
		// TODO: Step 3
		// Perform a postorder traversal using op.accept(value)

	}

	public void inOrder(Consumer<T> op) {
		// TODO: Step 3
		// Perform an inorder traversal using op.accept(value)
	}

	public Optional<TreeNode<T>> remove(T target) {
		// TODO: Step 4
		// - If target is less than value:
		//   - Set the left child to be the result of calling this method recursively.
		// - If target is greater than value:
		//   - Set the right child to be the result of calling this method recursively.
		// - If target and value are equivalent:
		//   - If it is at a leaf, return Optional.empty()
		//   - If it has exactly one child, return that child
		//   - If it has two children, set the current value to the maximum value of the left child,
		//     Then set the left child to the result of a recursive call
		//     with that maximum value as the parameter.

		return null;
	}

	public Optional<TreeNode<T>> leftRotateAt(T target) {
		// TODO:
		// - If target is less than value:
		//   - Set the left child to be the result of calling this method recursively.
		// - If target is greater than value:
		//   - Set the right child to be the result of calling this method recursively.
		// - If target and value are equivalent:
		// 		perform a left rotation of this node
		//      and return the value to be held by the parent.
		// - If target is not present, just return this.
		return Optional.of(this);
	}

	public Optional<TreeNode<T>> rightRotateAt(T target) {
		// TODO:
		// - If target is less than value:
		//   - Set the left child to be the result of calling this method recursively.
		// - If target is greater than value:
		//   - Set the right child to be the result of calling this method recursively.
		// - If target and value are equivalent:
		// 		perform a right rotation of this node
		//      and return the value to be held by the parent.
		// - If target is not present, just return Optional.empty().
		return Optional.of(this);
	}
}
