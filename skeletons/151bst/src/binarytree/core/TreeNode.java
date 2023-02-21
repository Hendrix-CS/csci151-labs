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

	public void insert(T value) {
		// TODO: Step 1
		// Insert a new node containing value, preserving the ordering.
		//
		// Compare the parameter to this.value to determine which direction to go.
		// If the parameter is less than this.value, proceed with the left child.
		// If the parameter is greater than this.value, proceed with the right child.
		// If the child is empty, replace it with a new Optional<TreeNode>
		// containing the value.
		// Otherwise, call insert recursively on the child.
		if(value.compareTo(this.value) < 0) {
			if(left.isEmpty()) {
				left = Optional.of(new TreeNode(value));
			}
			else {
				left.get().insert(value);
			}

		}
		else if(value.compareTo(this.value) > 0) {
			if(right.isEmpty()) {
				right = Optional.of(new TreeNode(value));
			}
			else {
				right.get().insert(value);
			}
		}
	}

	public boolean contains(T value) {
		// TODO: Step 1
		// Return true if value is present in the tree, false otherwise
		//
		// Compare the parameter to this.value to determine which direction to go.
		// If the parameter is equal to this.value, return true
		// If the parameter is less than this.value, proceed with the left child.
		// If the parameter is greater than this.value, proceed with the right child.
		// If the child is empty, return false.
		// Otherwise, call contains recursively on the child.
		if(value.compareTo(this.value) == 0) {
			return true;
		}
		else if(value.compareTo(this.value) < 0) {
			if(left.isPresent()) {
				return left.get().contains(value);
			}
			else {
				return false;
			}
		}
		else if(value.compareTo(this.value) > 0) {
			if(right.isPresent()) {
				return right.get().contains(value);
			}
			else {
				return false;
			}
		}
		return false;
	}

	public int size() {
		// TODO: Step 2
		// Return the number of nodes in the tree, which is 1 + the number of
		// nodes in the child trees.
		int count = 1;
		if(isLeaf()) {}
		if(left.isPresent()) {
			count += left.get().size();
		}
		if(right.isPresent()) {
			count += right.get().size();
		}

		return count;
	}

	public T getMin() {
		// TODO: Step 2
		// Return the smallest (left-most) value in the tree.
		if(left.isPresent()) {
			return left.get().getMin();
		}
		return value;
	}

	public T getMax() {
		// TODO: Step 2
		// Return the largest (right-most) value in the tree.
		if(right.isPresent()) {
			return right.get().getMax();
		}
		return value;
	}

	public int height() {
		// TODO: Step 2
		// Return the height of the tree, which is the maximum number of
		// edges between the root and any leaf.
		int height = 0;
		if(isLeaf()) {};
		if(left.isPresent() & right.isEmpty()) {
			return left.get().height() + 1;
		}
		if(right.isPresent() & left.isEmpty()) {
			return  right.get().height() + 1;
		}
		if(!isLeaf()) {
			int heightLeft = left.get().height();
			int heightRight = right.get().height();
			if(heightLeft > heightRight) {
				return heightLeft + 1;
			}
			else {
				return heightRight + 1;
			}



		}

		return height;


	}

	public void preOrder(Consumer<T> op) {
		// TODO: Step 3
		// Perform a preorder traversal using op.accept(value)
		op.accept(value);
		if(left.isPresent()) {
			left.get().preOrder(op);
		}
		if(right.isPresent()) {
			right.get().preOrder(op);
		}
	}

	public void postOrder(Consumer<T> op) {
		// TODO: Step 3
		// Perform a postorder traversal using op.accept(value)
		if(left.isPresent()) {
			left.get().postOrder(op);
		}
		if(right.isPresent()) {
			right.get().postOrder(op);
		}
		op.accept(value);


	}

	public void inOrder(Consumer<T> op) {
		// TODO: Step 3
		// Perform an inorder traversal using op.accept(value)
		if(left.isPresent()) {
			left.get().inOrder(op);
		}
		op.accept(value);
		if(right.isPresent()) {
			right.get().inOrder(op);
		}

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
		if(contains(target)) {
			if(target.compareTo(this.value) < 0) {
				left = left.get().remove(target);
			}
			if(target.compareTo(this.value) > 0) {
				right = right.get().remove(target);
			}
			else if(target.compareTo(this.value) == 0) {
				if(isLeaf()) {
					return Optional.empty();
				}
				if(left.isPresent() & right.isEmpty()) {
					return left;
				}
				if(right.isPresent() & left.isEmpty()) {
					return right;
				}
				else {
					this.value = left.get().getMax();
					left = left.get().remove(this.value);
				}
			}
		}
		return Optional.of(this);
	}

	public TreeNode<T> leftRotateAt(T value) {
		// TODO:
		// - If value is present in the tree, perform a left rotation of
		//   the node containing it, and return its new parent node.
		// - If value is not present, just return this.
		return this;
	}

	public TreeNode<T> rightRotateAt(T value) {
		// TODO:
		// - If value is present in the tree, perform a right rotation of
		//   the node containing it, and return its new parent node.
		// - If value is not present, just return this.
		return this;
	}
}
