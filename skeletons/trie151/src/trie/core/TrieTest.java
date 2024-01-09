package trie.core;

import static org.junit.Assert.*;
 
import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

public class TrieTest {
	Trie trie = new Trie();
	String[] strings = new String[]{"an", "and", "any", "are", "args", "as", "ash", "ask", "boil", "bore", "bubble", "hello", "help", "zoo"};
	
	public void setupRegular() {
		for (String s: strings) {
			trie.add(s);
		}
	}
	
	public ArrayList<String> toArrayList() {
		ArrayList<String> inputs = new ArrayList<>();
		for (String s: strings) {
			inputs.add(s);
		}
		return inputs;
	}
	
	public void printInorder() {
		System.out.println("contents: ");
		for (String s: trie.inorder()) {
			System.out.println(s);
		}
	}
	
	@Test
	public void testGetChildWith() {
		setupRegular();
		assertTrue(trie.getChildWith('a').isPresent());
		assertTrue(trie.getChildWith('b').isPresent());
		assertTrue(trie.getChildWith('h').isPresent());
		assertTrue(trie.getChildWith('z').isPresent());
		assertFalse(trie.getChildWith('c').isPresent());
		assertFalse(trie.getChildWith('d').isPresent());
		assertFalse(trie.getChildWith('g').isPresent());
		assertFalse(trie.getChildWith('y').isPresent());
	}
	
	@Test
	public void testContains() {
		setupRegular();
		for (String s: strings) {
			assertTrue(trie.contains(s));
		}
		assertEquals(strings.length, trie.size());
	}
	
	@Test
	public void testNotContains() {
		setupRegular();
		for (String s: new String[]{"arg", "a", "ashen", "bail", "bo", "bor", "cat", "he"}) {
			assertFalse(trie.contains(s));
		}
	}
	
	public void setupShuffled() {
		trie = new Trie();
		ArrayList<String> inputs = toArrayList();
		Collections.shuffle(inputs);
		for (String s: inputs) {
			trie.add(s);
		}
	}

	@Test
	public void testOrdering() {
		for (int j = 0; j < 100; j++) {
			setupShuffled();
			ArrayList<String> inorder = trie.inorder();
			assertEquals(strings.length, inorder.size());
			for (int i = 0; i < inorder.size(); i++) {
				assertEquals(strings[i], inorder.get(i));
			}
		}
	}

	@Test
	public void testSuccessorsA() {
		testSuccessors("a");
	}

	@Test
	public void testSuccessorsB() {
		testSuccessors("b");
	}

	@Test
	public void testSuccessorsH() {
		testSuccessors("h");
	}
	
	@Test
	public void testSuccessorsAN() {
		testSuccessors("an");
	}
	
	@Test
	public void testSuccessorsHEL() {
		testSuccessors("hel");
	}
	
	public void testSuccessors(String prefix) {
		setupRegular();
		ArrayList<String> succ = trie.successorsTo(prefix);
		for (String s: strings) {
			if (s.startsWith(prefix)) {
				assertTrue(succ.contains(s));
			}
		}
	}
	
	@Test
	public void bigRemoveTest() {
		setupRegular();
		ArrayList<String> inputs = toArrayList();
		while (!inputs.isEmpty()) {
			String target = inputs.remove(0);
			testRemove(target, inputs);
		}
	}
	
	public void testRemove(String toRemove, ArrayList<String> remainder) {
		int oldSize = trie.size();
		trie.remove(toRemove);
		assertEquals(oldSize - 1, trie.size());
		assertFalse(trie.contains(toRemove));
		for (String remain: remainder) {
			assertTrue(trie.contains(remain));
		}
	}
	
	@Test
	public void bogusRemoveTest() {
		setupRegular();
		int oldSize = trie.size();
		trie.remove("asp");
		assertEquals(oldSize, trie.size());
	}
}
