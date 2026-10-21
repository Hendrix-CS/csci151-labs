package binarytree.core;

import org.junit.*;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;

public class RBTest {
    @Test
    public void smallRandomTrees() {
        for (int test = 0; test < 30; test++) {
            ArrayList<Character> chars = new ArrayList<>();
            for (char c = 'a'; c <= 'z'; c++) {
                chars.add(c);
            }
            Collections.shuffle(chars);
            System.out.println(chars);
            makeTestTreeFrom(chars);
        }
    }

    @Test
    public void bigRandomTrees() {
        for (int test = 0; test < 30; test++) {
            ArrayList<Integer> values = new ArrayList<>();
            for (int i = 0; i < 256; i++) {
                values.add(i);
            }
            Collections.shuffle(values);
            makeTestTreeFrom(values);
        }
    }

    @Test
    public void testRedRedViolation() {
        RedBlackTree<Character> tree = makeTreeFromString("ba");
        RBNode<Character> root = tree.getRoot().orElseThrow();
        RBNode<Character> child = root.getLeft().orElseThrow();

        assertFalse(root.redRedViolation());
        assertFalse(child.redRedViolation());

        root.setColor(RedBlack.RED);
        child.setColor(RedBlack.RED);
        assertTrue(child.redRedViolation());

        child.setColor(RedBlack.BLACK);
        assertFalse(child.redRedViolation());
    }

    @Test
    public void zigZigInvariant()
    {
        testTrees(new TestCase("cba", "Zig-Zig invariant tree."));
    }

    @Test
    public void zigZagInvariant()
    {
        testTrees(new TestCase("cab", "Zig-Zag invariant tree."));
    }

    @Test
    public void zagZigInvariant()
    {
        testTrees(new TestCase("acb", "Zag-Zig invariant tree."));
    }
    @Test
    public void zagZagInvariant()
    {
        testTrees(new TestCase("abc", "Zag-Zag invariant tree."));
    }

    @Test
    public void allZigZagInvariants()
    {
        testTrees(new TestCase("cbadfe", "Test all Zig-Zag invariants on one tree."));
    }

    public void testTrees(TestCase testCase)
    {
        RedBlackTree<Character> tree = makeTreeFromString(testCase.line());
        try
        {
            tree.assertInvariants();
        }
        catch (Exception e)
        {
            throw new IllegalStateException("Failed on " + testCase.description(), e);
        }
    }

    @Test
    public void testRedPiblingFixup() {
        RedBlackTree<Character> tree = makeTreeFromString("dbfac");

        tree.assertInvariants();

        RBNode<Character> root = tree.getRoot().get();

        assertEquals(RedBlack.BLACK, root.getColor());

        assertEquals(RedBlack.BLACK, root.getLeft().orElseThrow().getColor());
        assertEquals(RedBlack.BLACK, root.getRight().orElseThrow().getColor());
    }

    public RedBlackTree<Character> makeTreeFromString(String line)
    {
        RedBlackTree<Character> tree = new RedBlackTree<>();
        line.chars().forEach(k -> tree.insert((char) k));

        return tree;
    }

    public <T extends Comparable<T>> void makeTestTreeFrom(ArrayList<T> values) {
        RedBlackTree<T> t = new RedBlackTree<>();
        for (int i = 0; i < values.size(); i++) {
            t.insert(values.get(i));
            t.getRoot().orElseThrow().assertParentChildSymmetry();
        }
        t.assertInvariants();
    }

    public record TestCase(String line, String description)
    {

    }
}