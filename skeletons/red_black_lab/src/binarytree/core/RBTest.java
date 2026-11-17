package binarytree.core;

import binarytree.core.nodes.RBNode;
import binarytree.core.nodes.TreeNode;
import org.junit.*;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;

public class RBTest {

    @Test
    public void testGrandparent()
    {
        RedBlackTree<Integer> tree = new RedBlackTree<>();

        TreeNode<Integer> first = new RBNode<>(1, tree);
        TreeNode<Integer> firstLeft =  new RBNode<>(0, first);
        TreeNode<Integer> secondLeft = new RBNode<>(-1, firstLeft);

        tree.setLeft(first);
        first.setLeft(firstLeft);
        firstLeft.setLeft(secondLeft);

        if (secondLeft.getGrandparent() == null) {
            throw new IllegalStateException("Cannot get grandparent!");
        }
    }

    @Test
    public void testPibling()
    {
        RedBlackTree<Integer> tree = new RedBlackTree<>();

        TreeNode<Integer> root = new RBNode<>(1, tree);
        TreeNode<Integer> left1 = new RBNode<>(0, root);
        TreeNode<Integer> left2 = new RBNode<>(-1, left1);
        TreeNode<Integer> right1 = new RBNode<>(-1, root);
        TreeNode<Integer> right2 = new RBNode<>(-1, right1);

        root.setLeft(left1);
        root.setRight(right1);
        left1.setLeft(left2);
        right1.setRight(right2);

        if (right2.getPibling() == null || left2.getPibling() == null) {
            throw new IllegalStateException("Cannot find pibling, is null!");
        }
    }

    @Test
    public void testRightRotate() {
        RedBlackTree<Integer> tree = new RedBlackTree<>();

        TreeNode<Integer> first = new RBNode<>(1, tree);
        TreeNode<Integer> firstLeft =  new RBNode<>(0, first);
        TreeNode<Integer> secondLeft = new RBNode<>(-1, firstLeft);

        tree.setLeft(first);
        first.setLeft(firstLeft);
        firstLeft.setLeft(secondLeft);

        tree.rightRotate();
        //tree.insertFixup();

        if (tree.height() != 2) throw new IllegalStateException("Right rotate failed! Tree is height " + tree.height());
    }

    @Test
    public void testLeftRotate() {
        RedBlackTree<Integer> tree = new RedBlackTree<>();

        TreeNode<Integer> first = new RBNode<>(1, tree);
        TreeNode<Integer> firstLeft =  new RBNode<>(0, first);
        TreeNode<Integer> secondLeft = new RBNode<>(-1, firstLeft);

        tree.setRight(first);
        first.setRight(firstLeft);
        firstLeft.setRight(secondLeft);

        tree.leftRotate();

        if (tree.height() != 2) throw new IllegalStateException("Left rotate failed! Tree is height " + tree.height());
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
        RedBlackTree<Character> tree = new RedBlackTree<>();
        TreeNode<Character> first = new RBNode<>('c', tree);
        tree.changeChild(tree.getRoot(), first);
        TreeNode<Character> firstLeft = new RBNode<>('b', first);
        RBNode<Character> secondLeft = new RBNode<>('a', firstLeft);

        first.setLeft(firstLeft);
        firstLeft.setLeft(secondLeft);

        secondLeft.zigZigFixup(firstLeft, first);

        assertEquals(1, tree.height());
    }

    @Test
    public void zigZagInvariant() {
        RedBlackTree<Character> tree = new RedBlackTree<>();

        TreeNode<Character> first = new RBNode<>('a', tree);
        tree.changeChild(tree.getRoot(), first);

        TreeNode<Character> firstRight = new RBNode<>('c', first);
        RBNode<Character> secondRight = new RBNode<>('b', firstRight);

        first.setRight(firstRight);
        firstRight.setLeft(secondRight);

        secondRight.zigZagFixup(firstRight, first);

        assertEquals(Character.valueOf('b'), tree.getRoot().getValue());
        assertEquals(Character.valueOf('a'), tree.getRoot().getLeft().getValue());
        assertEquals(Character.valueOf('c'), tree.getRoot().getRight().getValue());

        assertEquals(1, tree.height());
    }

    @Test
    public void zagZigInvariant() {
        RedBlackTree<Character> tree = new RedBlackTree<>();

        TreeNode<Character> first = new RBNode<>('a', tree);
        tree.changeChild(tree.getRoot(), first);

        TreeNode<Character> firstRight = new RBNode<>('c', first);
        RBNode<Character> secondRight = new RBNode<>('b', firstRight);

        first.setRight(firstRight);
        firstRight.setLeft(secondRight);

        secondRight.zigZagFixup(firstRight, first);

        assertEquals(Character.valueOf('b'), tree.getRoot().getValue());
        assertEquals(Character.valueOf('a'), tree.getRoot().getLeft().getValue());
        assertEquals(Character.valueOf('c'), tree.getRoot().getRight().getValue());

        assertEquals(1, tree.height());
    }

    @Test
    public void testBlackPiblingFixup() {
        RedBlackTree<Character> tree = new RedBlackTree<>();

        RBNode<Character> grandparent = new RBNode<>('c', tree);
        tree.changeChild(tree.getRoot(), grandparent);

        RBNode<Character> parent = new RBNode<>('b', grandparent);
        RBNode<Character> pibling = new RBNode<>('d', grandparent);
        RBNode<Character> node = new RBNode<>('a', parent);

        grandparent.setLeft(parent);
        grandparent.setRight(pibling);
        parent.setLeft(node);

        grandparent.setColor(RedBlack.BLACK);
        parent.setColor(RedBlack.RED);
        pibling.setColor(RedBlack.BLACK);
        node.setColor(RedBlack.RED);

        node.blackPiblingFixup(parent, grandparent);

        assertEquals(Character.valueOf('b'), tree.getRoot().getValue());
        assertEquals(Character.valueOf('a'), tree.getRoot().getLeft().getValue());
        assertEquals(Character.valueOf('c'), tree.getRoot().getRight().getValue());
    }

    @Test
    public void zagZagInvariant() {
        RedBlackTree<Character> tree = new RedBlackTree<>();

        TreeNode<Character> first = new RBNode<>('a', tree);
        tree.changeChild(tree.getRoot(), first);

        TreeNode<Character> firstRight = new RBNode<>('b', first);
        RBNode<Character> secondRight = new RBNode<>('c', firstRight);

        first.setRight(firstRight);
        firstRight.setRight(secondRight);

        secondRight.zagZagFixup(firstRight, first);

        assertEquals(Character.valueOf('b'), tree.getRoot().getValue());
        assertEquals(Character.valueOf('a'), tree.getRoot().getLeft().getValue());
        assertEquals(Character.valueOf('c'), tree.getRoot().getRight().getValue());

        assertEquals(1, tree.height());
    }

    @Test
    public void testRedPiblingFixup() {
        RedBlackTree<Character> tree = new RedBlackTree<>();

        RBNode<Character> grandparent = new RBNode<>('c', tree);
        tree.changeChild(tree.getRoot(), grandparent);

        RBNode<Character> parent = new RBNode<>('b', grandparent);
        RBNode<Character> pibling = new RBNode<>('d', grandparent);
        RBNode<Character> node = new RBNode<>('a', parent);

        grandparent.setLeft(parent);
        grandparent.setRight(pibling);
        parent.setLeft(node);

        grandparent.setColor(RedBlack.BLACK);
        parent.setColor(RedBlack.RED);
        pibling.setColor(RedBlack.RED);
        node.setColor(RedBlack.RED);

        node.redPiblingFixup();

        if (parent.getColor() != RedBlack.BLACK || pibling.getColor() != RedBlack.BLACK || grandparent.getColor() != RedBlack.BLACK) {
            throw new IllegalStateException(
                    "Red-Pibling Fixup failed! Incorrect recoloring."
            );
        }
    }

    public RedBlackTree<Character> makeTreeFromString(String line) {
        RedBlackTree<Character> tree = new RedBlackTree<>();
        line.chars().forEach(k -> tree.insert((char) k));

        return tree;
    }

    public <T extends Comparable<T>> void makeTestTreeFrom(ArrayList<T> values) {
        RedBlackTree<T> t = new RedBlackTree<>();
        try {
            for (int i = 1; i < values.size(); i++) {
                t.insert(values.get(i));
                t.checkTree();
            }
            t.assertInvariants();
        }
        catch (Exception e)
        {
            t.printTree();
            throw e;
        }
    }
}