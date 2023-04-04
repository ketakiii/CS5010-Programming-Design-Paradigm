package bst;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import org.junit.Test;

/**
 * This class tests the TreeNode class and its methods.
 */
public class TreeNodeTest {

  @Test public void testAddNode() {
    TreeNode<String> tn = new LeafNode<String>();
    assertEquals("", tn.inOrder());
    tn = tn.addNode("M");
    assertEquals("M", tn.inOrder());
    tn = tn.addNode("G");
    assertEquals("GM", tn.inOrder());
    tn = tn.addNode("R");
    assertEquals("GMR", tn.inOrder());
  }

  @Test public void testPreOrder() {
    TreeNode<String> tn = new LeafNode<String>();
    assertEquals("", tn.preOrder());
    tn = tn.addNode("M");
    assertEquals("M", tn.preOrder());
    tn = tn.addNode("G");
    assertEquals("MG", tn.preOrder());
    tn = tn.addNode("R");
    assertEquals("MGR", tn.preOrder());
  }

  @Test public void testPostOrder() {
    TreeNode<String> tn = new LeafNode<String>();
    assertEquals("", tn.postOrder());
    tn = tn.addNode("M");
    assertEquals("M", tn.postOrder());
    tn = tn.addNode("G");
    assertEquals("GM", tn.postOrder());
    tn = tn.addNode("R");
    assertEquals("GRM", tn.postOrder());
  }

  @Test public void testMinimum() {
    TreeNode<String> tn = new LeafNode<String>();
    assertNull(tn.minimum());
    tn = tn.addNode("M");
    assertEquals("M", tn.minimum());
    tn = tn.addNode("G");
    assertEquals("G", tn.minimum());
    tn = tn.addNode("R");
    assertEquals("G", tn.minimum());
    tn = tn.addNode("A");
    assertEquals("A", tn.minimum());

    TreeNode<Integer> tn1 = new LeafNode<Integer>();
    Integer expected = Integer.valueOf(5);
    tn1 = tn1.addNode(expected);
    assertEquals(expected, tn1.minimum());
    tn1 = tn1.addNode(Integer.valueOf(12));
    assertEquals(expected, tn1.minimum());
    expected = Integer.valueOf(4);
    tn1 = tn1.addNode(expected);
    assertEquals(expected, tn1.minimum());
    expected = Integer.valueOf(2);
    assertEquals(expected, tn1.minimum());
  }

  @Test public void testMaximum() {
    TreeNode<Integer> tn = new LeafNode<Integer>();
    tn = tn.addNode(Integer.valueOf(6));
    assertEquals(Integer.valueOf(6), tn.maximum());
    tn = tn.addNode(Integer.valueOf(3));
    assertEquals(Integer.valueOf(6), tn.maximum());
    tn = tn.addNode(Integer.valueOf(30));
    assertEquals(Integer.valueOf(30), tn.maximum());
    tn = tn.addNode(Integer.valueOf(20));
    assertEquals(Integer.valueOf(30), tn.maximum());
  }

  @Test public void testSize() {
    TreeNode<String> tn = new LeafNode<String>();
    assertEquals(0, tn.size());
    tn = tn.addNode("M");
    assertEquals(1, tn.size());
    tn = tn.addNode("R");
    assertEquals(2, tn.size());
    tn = tn.addNode("A");
    assertEquals(3, tn.size());
    tn = tn.addNode("D");
    assertEquals(4, tn.size());

    TreeNode<Double> tn1 = new LeafNode<Double>();
    Random random = new Random(678);
    for (int test = 1; test < 5000; test++) {
      double rd = random.nextDouble() * 1000000 - 500000;
      tn1 = tn1.addNode(Double.valueOf(rd));
      assertEquals(test, tn1.size());
    }
  }

  @Test public void testHeight() {
    TreeNode<Integer> tn = new LeafNode<Integer>();
    assertEquals(0, tn.height());
    tn = tn.addNode(Integer.valueOf(42));
    assertEquals(1, tn.height());
    tn = tn.addNode(Integer.valueOf(100));
    assertEquals(2, tn.height());
    tn = tn.addNode(Integer.valueOf(35));
    assertEquals(2, tn.height());
    tn = tn.addNode(Integer.valueOf(5));
    assertEquals(3, tn.height());
    tn = tn.addNode(Integer.valueOf(105));
    assertEquals(3, tn.height());
    tn = tn.addNode(Integer.valueOf(26));
    assertEquals(4, tn.height());

  }

  @Test public void testPresent() {
    Set<Integer> s = new HashSet();
    TreeNode<Integer> tn = new LeafNode<Integer>();
    Random r = new Random(678);
    for (int test = 1; test < 5000; test++) {
      Integer i = r.nextInt();
      while (!s.add(i)) {
        i = r.nextInt();
      }
      tn = tn.addNode(i);
      assertTrue(tn.present(i));
    }

    for (Integer j : s) {
      assertTrue(tn.present(j));
    }

    for (int test = 1; test < 1000; test++) {
      Integer i = r.nextInt();
      while (s.contains(i)) {
        i = r.nextInt();
      }
      assertFalse(tn.present(i));
    }
  }

}