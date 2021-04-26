package tictactoe.learn;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class HashTableTest {
	HashTable<String,Integer> table = new HashTable<>();
	
	String[] keys = new String[]{"this", "is", "a", "test", "to", "check", "how", "it", 
			"works", "in", "practice", "with", "many", "different", "keys", "included", 
			"within", "the", "table"};
	
	@Test
	public void testIndex() {
		assertEquals(14, table.index("this"));
		assertEquals(10, table.index("is"));
		assertEquals(1, table.index("a"));
		assertEquals(2, table.index("test"));
		assertEquals(11, table.index("to"));
		assertEquals(8, table.index("check"));
		assertEquals(0, table.index("how"));
		assertEquals(11, table.index("it"));
		assertEquals(2, table.index("works"));
		assertEquals(5, table.index("in"));
		assertEquals(5, table.index("practice"));
		assertEquals(6, table.index("with"));
		assertEquals(15, table.index("many"));
		assertEquals(9, table.index("different"));
		assertEquals(4, table.index("keys"));
		assertEquals(12, table.index("included"));
		assertEquals(5, table.index("within"));
		assertEquals(1, table.index("the"));
		assertEquals(14, table.index("table"));
	}

	@Test
	public void testPutGet() {
		for (String key: keys) {
			assertFalse(table.get(key).isPresent());
			int oldSize = table.size();
			table.put(key, key.length());
			assertEquals(oldSize + 1, table.size());
			assertTrue(table.get(key).isPresent());
		}
		
		assertEquals(keys.length, table.size());
		
		for (String key: keys) {
			assertTrue(table.get(key).isPresent());
			assertTrue(key.length() == table.get(key).get());
		}
	}
	
	@Test
	public void testDuplicate() {
		testPutGet();
		for (String key: keys) {
			int oldSize = table.size();
			table.put(key, key.length() * 2);
			assertEquals(oldSize, table.size());
			assertTrue(table.get(key).isPresent());
			assertTrue(key.length() * 2 == table.get(key).get());
		}
		
		assertEquals(keys.length, table.size());
		
		for (String key: keys) {
			assertTrue(table.get(key).isPresent());
			assertTrue(key.length() * 2 == table.get(key).get());
		}
	}
	
	@Test
	public void testCapacityIncrease() {
		assertEquals(16, table.capacity());
		assertEquals(0, table.size());
		for (int i = 0; i < 11; i++) {
			table.put(keys[i], keys[i].length());
		}
		assertEquals(16, table.capacity());
		assertEquals(11, table.size());
		
		for (int i = 11; i < keys.length; i++) {
			table.put(keys[i], keys[i].length());
		}
		assertEquals(32, table.capacity());
		assertEquals(keys.length, table.size());
	}

	@Test
	public void testKeys() {
		testPutGet();
		ArrayList<String> allKeys = table.allKeys();
		assertEquals(keys.length, allKeys.size());
		for (String key: keys) {
			assertTrue(table.allKeys().contains(key));
		}
	}
	
	@Test
	public void testVeryBad() {
		HashTable<VeryBad,Integer> vbTable = new HashTable<>();
		for (String key: keys) {
			vbTable.put(new VeryBad(key), key.length());
		}
		assertEquals(keys.length, vbTable.size());
	}
}
