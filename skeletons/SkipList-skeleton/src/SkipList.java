import java.util.Iterator;
import java.util.Random;
import java.util.Stack;

public class SkipList<E extends Comparable<E>> implements Iterable<E> {

	private SkipNode<E> front;
	private int size;

	// We need to generate random numbers to decide how high each tower should be.
	private static Random randGen = new Random();

	public SkipList() {
		// Create a dummy SkipNode which will be 
		front = new SkipNode<E>(null);
		front.addNext(null);
		size = 0;
	}

	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return (size == 0);
	}

	public int getHeight() {
		return front.getHeight();
	}

	/**
	 * Create a skip list node and insert it into the skip list. The stack of skip
	 * list nodes are the immediately previous nodes on each level (with level 0 on
	 * the top of the stack). At each level, set the next reference of the new node
	 * on that level to what the previous node on that level used to point to, and
	 * sets the previous node on that level to point to the new node. Assumes
	 * that the correct position has already been found, i.e. it just inserts the
	 * new node in the position given by the stack; it does not compare the data to
	 * anything to see whether it is in the right place.
	 * 
	 * @param data
	 *            The data to insert.
	 * @param prevs
	 *            The stack of immediately previous nodes on each level.
	 *            Precondition: the size of the stack is equal to the current height
	 *            of the skip list. In particular the stack is not empty, since we
	 *            at least need to know the previous node on level 0.
	 * @return The newly created node.
	 */
	private SkipNode<E> insertSkipListNode(E data, Stack<SkipNode<E>> prevs) {
		// TODO implement me!
	}

	/**
	 * Locate a search item, by returning a stack of nodes, one per level (level 0
	 * on the top of the stack). The node at level i either points to the node
	 * containing the search item, or to the first node on level i containing an
	 * item bigger than the search item (or null if there is no such bigger item on
	 * level i).
	 * 
	 * @param toFind
	 *            The search item.
	 * @return The stack of nodes immediately previous to the item on each level.
	 */
	public Stack<SkipNode<E>> locate(E toFind) {
		// TODO implement me!
	}

	@Override
	public String toString() {
		int maxLen = 0;
		SkipNode<E> cur = front.getNext(0);
		while (cur != null) {
			int l = cur.getData().toString().length();
			if (l > maxLen)
				maxLen = l;
			cur = cur.getNext(0);
		}

		String out = "";
		cur = front;
		int ht = cur.getHeight();
		while (cur != null) {
			E curData = cur.getData();
			out += (curData == null ? pad(maxLen, "D") : pad(maxLen, "" + curData)) + " : ";
			for (int i = 0; i < cur.getHeight(); i++) {
				SkipNode<E> n = cur.getNext(i);
				if (n == null) {
					out += pad(maxLen, "/") + " ";
				} else {
					out += pad(maxLen, "" + n.getData()) + " ";
				}
			}
			for (int i = cur.getHeight(); i < ht; i++) {
				out += pad(maxLen, "|") + " ";
			}
			out += "\n";

			cur = cur.getNext(0);
		}

		return out;
	}

	private static String pad(int n, String s) {
		while (s.length() < n) {
			s = " " + s;
		}
		return s;
	}

	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {
			private SkipNode<E> cur = front.getNext(0);
			
			@Override
			public boolean hasNext() {
				return cur != null;
			}

			@Override
			public E next() {
				E toReturn = cur.getData();
				cur = cur.getNext(0);
				return toReturn;
			}	
		};
	}

}
