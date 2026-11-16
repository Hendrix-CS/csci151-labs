package binarytree.core;

import binarytree.core.nodes.RBNode;
import binarytree.core.nodes.TreeNode;
import org.junit.*;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;

public class RBTest {

    @Test
    public void testRightRotate() {
        TreeNode<Integer> tree = new RBNode<>(1, null);

        TreeNode<Integer> firstLeft =  new RBNode<>(0, tree);
        TreeNode<Integer> secondLeft = new RBNode<>(-1, null);

        tree.setLeft(firstLeft);
        firstLeft.setLeft(secondLeft);

        tree.rightRotate();
    }

    @Test
    public void testLeftRotate() {
        TreeNode<Integer> tree = new RBNode<>(1, null);

        TreeNode<Integer> firstRight = new RBNode<>(0, tree);
        TreeNode<Integer> secondRight = new RBNode<>(-1, null);

        tree.setLeft(firstRight);
        firstRight.setLeft(secondRight);

        tree.leftRotate();
    }

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
        TreeNode<Character> tree = makeTreeFromString("ba");
        TreeNode<Character> child = tree.getLeft();

        tree.checkTree();

        tree.setColor(RedBlack.RED);
        child.setColor(RedBlack.RED);

        try
        {
            tree.checkTree();

            throw new AssertionError("Red Red Violation was not reported!");
        }
        catch (IllegalStateException ignored)
        {
            // this is on purpose i promise
        }

        child.setColor(RedBlack.BLACK);

        tree.checkTree();
    }

    @Test
    public void zigZigInvariant() {
        testTrees(new TestCase("cba", "Zig-Zig invariant tree."));
    }

    @Test
    public void zigZagInvariant() {
        testTrees(new TestCase("cab", "Zig-Zag invariant tree."));
    }

    @Test
    public void zagZigInvariant() {
        testTrees(new TestCase("acb", "Zag-Zig invariant tree."));
    }

    @Test
    public void zagZagInvariant() {
        testTrees(new TestCase("abc", "Zag-Zag invariant tree."));
    }

    @Test
    public void allZigZagInvariants() {
        testTrees(new TestCase("cbadfe", "Test all Zig-Zag invariants on one tree."));
    }

    public void testTrees(TestCase testCase) {
        TreeNode<Character> tree = makeTreeFromString(testCase.line());
        try {
            tree.checkTree();
        }
        catch (Exception e) {
            throw new IllegalStateException("Failed on " + testCase.description(), e);
        }
    }

    @Test
    public void testRedPiblingFixup() {
        TreeNode<Character> tree = makeTreeFromString("dbfac");

        tree.checkTree();

        TreeNode<Character> root = tree;

        assertEquals(RedBlack.BLACK, root.getColor());

        assertEquals(RedBlack.BLACK, root.getLeft().getColor());
        assertEquals(RedBlack.BLACK, root.getRight().getColor());
    }

    public TreeNode<Character> makeTreeFromString(String line) {
        TreeNode<Character> tree = new RBNode<>(line.charAt(0), null);
        //line.chars().forEach(k -> tree.insert((char) k));

        for (int i = 1; i < line.length(); i++)
        {
            tree.insert(line.charAt(i));
        }

        return tree;
    }

    public <T extends Comparable<T>> void makeTestTreeFrom(ArrayList<T> values) {
        TreeNode<T> t = new RBNode<>(values.getFirst(), null);
        for (int i = 1; i < values.size(); i++) {
            t.insert(values.get(i));
            t.checkTree();
        }
        t.checkTree();
    }

    public record TestCase(String line, String description) {

    }
}