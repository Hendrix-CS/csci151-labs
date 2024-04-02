package binarytree.core;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.junit.Test;

public class BinarySearchTreeTest {
	public final static String CHARS = "hdlbfjn";
	
	BinarySearchTree<String> tree = new BinarySearchTree<>();
	
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
		assertEquals(2, (int)tree.height().get());
	}

	@Test
	public void testHeightBig() {
		test1();
		foreach("pqrstuvwxyz", c -> tree.insert(c));
		assertEquals(13, (int)tree.height().get());
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
		foreachIndexed("hdbfljn", (i, c) -> assertEquals(c, pre.get(i)));
	}
	
	@Test
	public void testPostorder() {
		test1();
		ArrayList<String> post = tree.postorder();
		print("Postorder", post);
		foreachIndexed("bfdjnlh", (i, c) -> assertEquals(c, post.get(i)));
	}
	
	@Test
	public void testLevelOrder() {
		test1();
		ArrayList<ArrayList<ArrayList<String>>> levels = tree.levelOrder();
		for (int i = 0; i < levels.size(); i++) {
			System.out.print("Level " + i + ":");
			for (int j = 0; j < levels.get(i).size(); j++) {
				System.out.print(" " + levels.get(i).get(j).get(0));
			}
			System.out.println();
		}
		assertEquals(3, levels.size());
		assertEquals(1, levels.get(0).size());
		assertEquals(2, levels.get(1).size());
		assertEquals(4, levels.get(2).size());
		
		// Nodes
		assertEquals("h", levels.get(0).get(0).get(0));
		assertEquals("d", levels.get(1).get(0).get(0));
		assertEquals("l", levels.get(1).get(1).get(0));
		assertEquals("b", levels.get(2).get(0).get(0));
		assertEquals("f", levels.get(2).get(1).get(0));
		assertEquals("j", levels.get(2).get(2).get(0));
		assertEquals("n", levels.get(2).get(3).get(0));
		
		// Children
		assertEquals("d", levels.get(0).get(0).get(1));
		assertEquals("l", levels.get(0).get(0).get(2));
		assertEquals("b", levels.get(1).get(0).get(1));
		assertEquals("f", levels.get(1).get(0).get(2));
		assertEquals("j", levels.get(1).get(1).get(1));
		assertEquals("n", levels.get(1).get(1).get(2));
	}
	
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
	public void testLeftRotate() {
		tree.insert("a");
		tree.insert("b");
		tree.insert("c");
		assertEquals("a b c", tree.preorderString());
		tree.leftRotateAt("a");
		assertEquals("b a c", tree.preorderString());
	}

	@Test
	public void testLeftRotateBig() {
		tree.insert("f");
		tree.insert("b");
		tree.insert("a");
		tree.insert("d");
		tree.insert("c");
		tree.insert("e");
		tree.insert("j");
		tree.insert("h");
		tree.insert("g");
		tree.insert("i");
		tree.insert("k");
		assertEquals("f b a d c e j h g i k", tree.preorderString());
		tree.leftRotateAt("b");
		assertEquals("f d b a c e j h g i k", tree.preorderString());
	}
	
	@Test
	public void testRightRotate() {
		tree.insert("c");
		tree.insert("b");
		tree.insert("a");
		assertEquals("c b a", tree.preorderString());
		tree.rightRotateAt("c");
		assertEquals("b a c", tree.preorderString());
	}

	@Test
	public void testRightRotateBig() {
		tree.insert("f");
		tree.insert("b");
		tree.insert("a");
		tree.insert("d");
		tree.insert("c");
		tree.insert("e");
		tree.insert("j");
		tree.insert("h");
		tree.insert("g");
		tree.insert("i");
		tree.insert("k");
		assertEquals("f b a d c e j h g i k", tree.preorderString());
		tree.rightRotateAt("j");
		assertEquals("f b a d c e h g j i k", tree.preorderString());
	}
	@Test
	public void testBig() {
		for (char c = 0; c < 127; c++) {
			tree.insert("" + c);
		}
		assertEquals(tree.preorderString(), tree.inorderString());
	}
}
