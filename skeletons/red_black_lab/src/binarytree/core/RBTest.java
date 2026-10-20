package binarytree.core;

import org.junit.*;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

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

    public <T extends Comparable<T>> void makeTestTreeFrom(ArrayList<T> values) {
        RedBlackTree<T> t = new RedBlackTree<>();
        for (int i = 0; i < values.size(); i++) {
            t.insert(values.get(i));
            t.getRoot().get().assertParentChildSymmetry();
        }
        t.assertInvariants();
    }
}