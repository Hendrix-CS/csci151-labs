package splaytree.core;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Consumer;

public class TreeNode<T extends Comparable<T>> {
	Optional<TreeNode<T>> left = Optional.empty();
	Optional<TreeNode<T>> right = Optional.empty();
	private T value;

	public TreeNode(T value) {
		this.value = value;
	}

	public T get() {
		return value;
	}

	public Optional<TreeNode<T>> getLeft() {
		return left;
	}

	public Optional<TreeNode<T>> getRight() {
		return right;
	}

	public boolean isLeaf() {
		return left.isEmpty() && right.isEmpty();
	}

	public void insert(T target) {
		int comp = target.compareTo(value);
		if (comp < 0) {
			if (left.isPresent()) {
				left.get().insert(target);
			} else {
				left = Optional.of(new TreeNode<>(target));
			}
		} else if (comp > 0) {
			if (right.isPresent()) {
				right.get().insert(target);
			} else {
				right = Optional.of(new TreeNode<>(target));
			}
		}
	}

	public boolean contains(T target) {
		int comp = target.compareTo(value);
		if (comp < 0) {
			if (left.isPresent()) {
				return left.get().contains(target);
			} else {
				return false;
			}
		} else if (comp > 0) {
			if (right.isPresent()) {
				return right.get().contains(target);
			} else {
				return false;
			}
		} else {
			return true;
		}
	}

	public int size() {
		int count = 1;
		if (left.isPresent()) {
			count += left.get().size();
		}
		if (right.isPresent()) {
			count += right.get().size();
		}
		return count;
	}

	public T getMin() {
		if (left.isPresent()) {
			return left.get().getMin();
		}
		return value;
	}

	public T getMax() {
		if (right.isPresent()) {
			return right.get().getMax();
		}
		return value;
	}

	public int height() {
		if (isLeaf()) {
			return 0;
		} else if (left.isPresent() && right.isEmpty()) {
			return 1 + left.get().height();
		} else if (right.isPresent() && left.isEmpty()) {
			return 1 + right.get().height();
		} else {
			int lh = left.get().height();
			int rh = right.get().height();
			return Math.max(lh, rh) + 1;
		}
	}

	public void preOrder(Consumer<T> op) {
		op.accept(value);
		if (left.isPresent()) {
			left.get().preOrder(op);
		}
		if (right.isPresent()) {
			right.get().preOrder(op);
		}
	}

	public void postOrder(Consumer<T> op) {
		if (left.isPresent()) {
			left.get().postOrder(op);
		}
		if (right.isPresent()) {
			right.get().postOrder(op);
		}
		op.accept(value);

	}

	public void inOrder(Consumer<T> op) {
		if (left.isPresent()) {
			left.get().inOrder(op);
		}
		op.accept(value);
		if (right.isPresent()) {
			right.get().inOrder(op);
		}
	}

	public Optional<TreeNode<T>> remove(T target) {
		int comp = target.compareTo(value);
		if (comp < 0) {
			if (left.isPresent()) {
				left = left.get().remove(target);
			}
		} else if (comp > 0) {
			if (right.isPresent()) {
				right = right.get().remove(target);
			}
		} else {
			if (isLeaf()) {
				return Optional.empty();
			} else if (left.isPresent() && right.isEmpty()) {
				return left;
			} else if (right.isPresent() && left.isEmpty()) {
				return right;
			} else {
				this.value = left.get().getMax();
				left = left.get().remove(this.value);
			}
		}
		return Optional.of(this);
	}

    public boolean containsAncestry(T target, ArrayList<TreeNode<T>> ancestry) {
        // TODO: Step 1
        // - Add yourself to ancestry
        // - If target is equal to value, return true
        // - If target is less than value
        //   - If there is a left child, call containsAncestry() recursively.
        //   - Otherwise, return false
        // - If target is greater than value
        //   - If there is a right child, call containsAncestry() recursively.
        //   - Otherwise, return false;
        return false;
    }

    public Optional<Side> childSide(TreeNode<T> potentialChild) {
        // TODO: Step 2
        // - If potentialChild is on the left, return Side.LEFT
        // - If potentialChild is on the right, return Side.RIGHT
        // - Otherwise, return Optional.empty()
        return Optional.empty();
    }

    public TreeNode<T> rotateLeft() {
        // TODO: Step 3
        // - If there is a right child
        //   - Create a local variable for a new root, set to the right child
        //   - Make the right child the left child of the new root.
        //   - Make the left child of the new root be this.
        //   - Return the new root.
        // - Otherwise, return this.
        return this;
    }

    public TreeNode<T> rotateRight() {
        // TODO: Step 3
        // - If there is a left child
        //   - Create a local variable for a new root, set to the left child
        //   - Make the left child the right child of the new root.
        //   - Make the right child of the new root be this.
        //   - Return the new root.
        // - Otherwise, return this.
        return this;
    }

    public TreeNode<T> zigzig() {
        // TODO: Step 4
        // - Perform two right rotations to move the left grandchild up to the root.
        return this;
    }

    public TreeNode<T> zigzag() {
        // TODO: Step 4
        // - Set the left child to the result of rotating it left.
        // - Rotate right
        return this;
    }

    public TreeNode<T> zagzig() {
        // TODO: Step 4
        // - Set the right child to the result of rotating it right.
        // - Rotate left.
        return this;
    }

    public TreeNode<T> zagzag() {
        // TODO: Step 4
        // - Perform two left rotations to move the right grandchild up to the root.
        return this;
    }
}
