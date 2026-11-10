package splaytree.core;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.junit.Test;

public class SplayTreeTest {
	public final static String CHARS = "hdlbfjn";

	SplayTree<String> tree = new SplayTree<>();
	
	public void foreach(String chars, Consumer<String> innerLoop) {
		for (int i = 0; i < chars.length(); i++) {
			innerLoop.accept(chars.substring(i, i+1));
		}
	}
	
	public void foreachReversed(String chars, Consumer<String> innerLoop) {
		for (int i = chars.length() - 1; i >= 0; i--) {
			innerLoop.accept(chars.substring(i, i+1));
		}
	}
	
	public void foreachIndexed(String chars, BiConsumer<Integer,String> innerLoop) {
		for (int i = 0; i < chars.length(); i++) {
			innerLoop.accept(i, chars.substring(i, i+1));
		}
	}
	
	public static String reversed(String input) {
		String result = "";
		for (int i = input.length() - 1; i >= 0; i--) {
			result += input.charAt(i);
		}
		return result;
	}
	
	public static <T extends Comparable<T>> boolean isSorted(ArrayList<T> list) {
		for (int i = 0; i < list.size() - 1; i++) {
			if (list.get(i).compareTo(list.get(i+1)) > 0) {
				return false;
			}
		}
		return true;
	}

	@Test
	public void test1() {
		foreach(CHARS, c -> tree.insert(c));
		foreach(CHARS, c -> assertTrue(tree.contains(c)));
		foreach("ABCDEFGHIJKLMNOPQRSTUVWXYZpqrstuvwxyz", c -> assertFalse(tree.contains(c)));
	}

    @Test
    public void testChildSide() {
        TreeNode<Integer> n = new TreeNode<>(2);
        n.insert(1);
        n.insert(3);
        assertEquals(Optional.of(Side.LEFT), n.childSide(n.getLeft().get()));
        assertEquals(Optional.of(Side.RIGHT), n.childSide(n.getRight().get()));
        assertEquals(Optional.empty(), n.childSide(n));
    }

    @Test
    public void testContainsAncestry() {
        TreeNode<Integer> n = new TreeNode<>(4);
        int[] others = new int[]{2, 6, 1, 3, 5, 7};
        for (int i = 0; i < others.length; i++) {
            n.insert(others[i]);
        }

        testAncestry1(n);
        testAncestry2(n);
        testAncestry3(n);
        testAncestry4(n);
        testAncestry5(n);
        testAncestry6(n);
        testAncestry7(n);
        testNotThere(n, 0);
        testNotThere(n, 8);
    }

    public void testAncestry1(TreeNode<Integer> n) {
        ArrayList<TreeNode<Integer>> ancestry = new ArrayList<>();
        assertTrue(n.containsAncestry(1, ancestry));
        assertEquals(3, ancestry.size());
        assertEquals(ancestry.get(0), n);
        assertEquals(ancestry.get(1), n.getLeft().get());
        assertEquals(ancestry.get(2), n.getLeft().get().getLeft().get());
    }

    public void testAncestry2(TreeNode<Integer> n) {
        ArrayList<TreeNode<Integer>> ancestry = new ArrayList<>();
        assertTrue(n.containsAncestry(2, ancestry));
        assertEquals(2, ancestry.size());
        assertEquals(ancestry.get(0), n);
        assertEquals(ancestry.get(1), n.getLeft().get());
    }

    public void testAncestry3(TreeNode<Integer> n) {
        ArrayList<TreeNode<Integer>> ancestry = new ArrayList<>();
        assertTrue(n.containsAncestry(3, ancestry));
        assertEquals(3, ancestry.size());
        assertEquals(ancestry.get(0), n);
        assertEquals(ancestry.get(1), n.getLeft().get());
        assertEquals(ancestry.get(2), n.getLeft().get().getRight().get());
    }

    public void testAncestry4(TreeNode<Integer> n) {
        ArrayList<TreeNode<Integer>> ancestry = new ArrayList<>();
        assertTrue(n.containsAncestry(4, ancestry));
        assertEquals(1, ancestry.size());
        assertEquals(ancestry.get(0), n);
    }

    public void testAncestry5(TreeNode<Integer> n) {
        ArrayList<TreeNode<Integer>> ancestry = new ArrayList<>();
        assertTrue(n.containsAncestry(5, ancestry));
        assertEquals(3, ancestry.size());
        assertEquals(ancestry.get(0), n);
        assertEquals(ancestry.get(1), n.getRight().get());
        assertEquals(ancestry.get(2), n.getRight().get().getLeft().get());
    }

    public void testAncestry6(TreeNode<Integer> n) {
        ArrayList<TreeNode<Integer>> ancestry = new ArrayList<>();
        assertTrue(n.containsAncestry(6, ancestry));
        assertEquals(2, ancestry.size());
        assertEquals(ancestry.get(0), n);
        assertEquals(ancestry.get(1), n.getRight().get());
    }

    public void testAncestry7(TreeNode<Integer> n) {
        ArrayList<TreeNode<Integer>> ancestry = new ArrayList<>();
        assertTrue(n.containsAncestry(7, ancestry));
        assertEquals(3, ancestry.size());
        assertEquals(ancestry.get(0), n);
        assertEquals(ancestry.get(1), n.getRight().get());
        assertEquals(ancestry.get(2), n.getRight().get().getRight().get());
    }

    public void testNotThere(TreeNode<Integer> n, int notThere) {
        ArrayList<TreeNode<Integer>> ancestry = new ArrayList<>();
        assertFalse(n.containsAncestry(notThere, ancestry));
    }


	@Test
	public void testSize() {
		test1();
		assertEquals(CHARS.length(), tree.size());
	}

	@Test
	public void testMin() {
		test1();
		assertEquals("b", tree.getMin().get());
	}

	@Test
	public void testMax() {
		test1();
		assertEquals("n", tree.getMax().get());
	}

	@Test
	public void testHeight() {
		test1();
		assertEquals(5, (int)tree.height().get());
	}

	@Test
	public void testHeightBig() {
		test1();
		foreach("pqrstuvwxyz", c -> tree.insert(c));
		assertEquals(11, (int)tree.height().get());
	}

	@Test
	public void testSizeSmall() {
		tree.insert("m");
		tree.insert("a");
		assertEquals(2, tree.size());
	}

	@Test
	public void testInorder() {
		test1();
		print("Inorder", tree.inorder());
		assertTrue(isSorted(tree.inorder()));
	}
	
	public void print(String label, ArrayList<String> strs) {
		System.out.print(label + ":");
		for (String s: strs) {
			System.out.print(" " + s);
		}
		System.out.println();
	}
	
	@Test
	public void testPreorder() {
		test1();
		ArrayList<String> pre = tree.preorder();
		print("Preorder", pre);
		foreachIndexed("nljfbdh", (i, c) -> assertEquals(c, pre.get(i)));
	}
	
	@Test
	public void testPostorder() {
		test1();
		ArrayList<String> post = tree.postorder();
		print("Postorder", post);
		foreachIndexed("dbhfjln", (i, c) -> assertEquals(c, post.get(i)));
	}

    /*
	@Test
	public void testLevelOrder() {
		test1();
		ArrayList<ArrayList<ArrayList<String>>> levels = tree.levelOrder();
		System.out.println(levels);
		assertEquals(6, levels.size());
		assertEquals(1, levels.get(0).size());
		assertEquals(2, levels.get(1).size());
		assertEquals(4, levels.get(2).size());
		assertEquals(8, levels.get(3).size());
		assertEquals(16, levels.get(4).size());
        assertEquals(32, levels.get(5).size());
		// Nodes
        assertEquals("n", levels.get(0).get(0).get(0));
		assertEquals("j", levels.get(1).get(0).get(0));
		assertEquals("f", levels.get(2).get(0).get(0));
		assertEquals("l", levels.get(2).get(1).get(0));
		assertEquals("b", levels.get(3).get(0).get(0));
		assertEquals("h", levels.get(3).get(1).get(0));
		assertEquals("d", levels.get(4).get(1).get(0));

	}
	*/
	
	public boolean containsAll(String chars) {
		for (int i = 0; i < chars.length(); i++) {
			if (!tree.contains(chars.substring(i, i+1))) {
				return false;
			}
		}
		return true;
	}

	@Test
	public void testRemove1() {
		test1();
		foreachReversed(CHARS, c -> assertRemove(c));
	}
	
	@Test
	public void testRemove1a() {
		testAddRemove(reversed(CHARS));
	}
	
	@Test
	public void testRemove2() {
		test1();
		foreach(CHARS, c -> assertRemove(c));
	}	
	
	@Test
	public void testRemove2a() {
		testAddRemove(CHARS);
	}

	@Test
	public void testRemove3() {
		test1();
		foreach("njfblhd", c -> assertRemove(c));
	}

	@Test
	public void testRemove3a() {
		testAddRemove("njfblhd");
	}

	@Test
	public void testRemove4() {
		test1();
		foreach("dbfjlnh", c -> assertRemove(c));
	}
	
	@Test
	public void testRemove4a() {
		testAddRemove("dbfjlnh");
	}

	@Test
	public void testRemove5() {
		test1();
		foreach("hh", c -> assertRemove(c));
	}
	
	public void testAddRemove(String removals) {
		test1();
		foreachIndexed(removals, (i, c) -> assertCorrectRemove(c, removals.substring(i+1)));
	}
	
	public void assertRemove(String c) {
		tree.remove(c);
		if (tree.contains(c)) {
			System.out.println("Failed to remove " + c);
		}
		assertFalse(tree.contains(c));
	}
	
	public void assertCorrectRemove(String c, String left) {
		tree.remove(c);
		if (tree.contains(c)) {
			System.out.println("Failed to remove " + c);
		}
		if (!containsAll(left)) {
			System.out.println("Deleted one or more of " + left);
		}
		assertTrue(!tree.contains(c) && containsAll(left));
	}

	@Test
	public void testBig() {
		for (char c = 0; c < 127; c++) {
			tree.insert("" + c);
		}
		assertEquals(tree.preorderString(), tree.inorderString());
	}

    @Test
    public void testRotateLeft() {
        TreeNode<Integer> n = new TreeNode<>(5);
        n.insert(3);
        n.insert(7);
        n.insert(6);
        n.insert(8);
        TreeNode<Integer> r = n.rotateLeft();
        assertEquals(Optional.of(7), Optional.of(r.get()));
        assertEquals(Optional.of(5), Optional.of(r.getLeft().get().get()));
        assertEquals(Optional.of(3), Optional.of(r.getLeft().get().getLeft().get().get()));
        assertEquals(Optional.of(6), Optional.of(r.getLeft().get().getRight().get().get()));
        assertEquals(Optional.of(8), Optional.of(r.getRight().get().get()));
    }

    @Test
    public void testRotateRight() {
        TreeNode<Integer> n = new TreeNode<>(7);
        n.insert(5);
        n.insert(6);
        n.insert(3);
        n.insert(8);
        TreeNode<Integer> r = n.rotateRight();
        assertEquals(Optional.of(5), Optional.of(r.get()));
        assertEquals(Optional.of(7), Optional.of(r.getRight().get().get()));
        assertEquals(Optional.of(3), Optional.of(r.getLeft().get().get()));
        assertEquals(Optional.of(6), Optional.of(r.getRight().get().getLeft().get().get()));
        assertEquals(Optional.of(8), Optional.of(r.getRight().get().getRight().get().get()));
    }

    @Test
    public void testZigZig() {
        TreeNode<Integer> n = new TreeNode<>(6);
        n.insert(4);
        n.insert(7);
        n.insert(2);
        n.insert(1);
        n.insert(3);
        n.insert(5);
        TreeNode<Integer> r = n.zigzig();
        assertEquals(Optional.of(2), Optional.of(r.get()));
        assertEquals(Optional.of(1), Optional.of(r.getLeft().get().get()));
        assertEquals(Optional.of(4), Optional.of(r.getRight().get().get()));
        assertEquals(Optional.of(3), Optional.of(r.getRight().get().getLeft().get().get()));
        assertEquals(Optional.of(6), Optional.of(r.getRight().get().getRight().get().get()));
        assertEquals(Optional.of(5), Optional.of(r.getRight().get().getRight().get().getLeft().get().get()));
        assertEquals(Optional.of(7), Optional.of(r.getRight().get().getRight().get().getRight().get().get()));
    }

    @Test
    public void testZagZag() {
        TreeNode<Integer> n = new TreeNode<>(2);
        n.insert(1);
        n.insert(4);
        n.insert(3);
        n.insert(6);
        n.insert(5);
        n.insert(7);
        TreeNode<Integer> r = n.zagzag();
        assertEquals(Optional.of(6), Optional.of(r.get()));
        assertEquals(Optional.of(4), Optional.of(r.getLeft().get().get()));
        assertEquals(Optional.of(7), Optional.of(r.getRight().get().get()));
        assertEquals(Optional.of(2), Optional.of(r.getLeft().get().getLeft().get().get()));
        assertEquals(Optional.of(1), Optional.of(r.getLeft().get().getLeft().get().getLeft().get().get()));
        assertEquals(Optional.of(3), Optional.of(r.getLeft().get().getLeft().get().getRight().get().get()));
        assertEquals(Optional.of(5), Optional.of(r.getLeft().get().getRight().get().get()));
    }

    @Test
    public void testZigZag() {
        TreeNode<Integer> n = new TreeNode<>(6);
        n.insert(2);
        n.insert(7);
        n.insert(1);
        n.insert(4);
        n.insert(3);
        n.insert(5);
        TreeNode<Integer> r = n.zigzag();
        assertEquals(Optional.of(4), Optional.of(r.get()));
        assertEquals(Optional.of(2), Optional.of(r.getLeft().get().get()));
        assertEquals(Optional.of(6), Optional.of(r.getRight().get().get()));
        assertEquals(Optional.of(1), Optional.of(r.getLeft().get().getLeft().get().get()));
        assertEquals(Optional.of(3), Optional.of(r.getLeft().get().getRight().get().get()));
        assertEquals(Optional.of(5), Optional.of(r.getRight().get().getLeft().get().get()));
        assertEquals(Optional.of(7), Optional.of(r.getRight().get().getRight().get().get()));
    }

    @Test
    public void testZagZig() {
        TreeNode<Integer> n = new TreeNode<>(2);
        n.insert(1);
        n.insert(6);
        n.insert(4);
        n.insert(3);
        n.insert(5);
        n.insert(7);
        TreeNode<Integer> r = n.zagzig();
        assertEquals(Optional.of(4), Optional.of(r.get()));
        assertEquals(Optional.of(2), Optional.of(r.getLeft().get().get()));
        assertEquals(Optional.of(6), Optional.of(r.getRight().get().get()));
        assertEquals(Optional.of(1), Optional.of(r.getLeft().get().getLeft().get().get()));
        assertEquals(Optional.of(3), Optional.of(r.getLeft().get().getRight().get().get()));
        assertEquals(Optional.of(5), Optional.of(r.getRight().get().getLeft().get().get()));
        assertEquals(Optional.of(7), Optional.of(r.getRight().get().getRight().get().get()));
    }
}
