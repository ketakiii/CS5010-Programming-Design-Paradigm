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
 * This class tests the BinarySearchTreeImpl class and its methods.
 */
public class BinarySearchTreeImplTest {

  @Test public void testBinarySearchTree() {
    BinarySearchTree<String> bst = new BinarySearchTreeImpl<String>();
    assertEquals("[]", bst.toString());
  }

  @Test public void testAdd() {
    BinarySearchTree<String> bst = new BinarySearchTreeImpl<String>();
    bst.add("M");
    assertEquals("[M]", bst.inOrder());
    bst.add("G");
    assertEquals("[G M]", bst.inOrder());
    bst.add("R");
    assertEquals("[G M R]", bst.inOrder());
    bst.add("R");
    assertEquals("[G M R]", bst.inOrder());
  }

  @Test public void testInOrder() {
    BinarySearchTree<String> bst = new BinarySearchTreeImpl<String>();
    assertEquals("[]", bst.inOrder());
    bst.add("M");
    assertEquals("[M]", bst.inOrder());
    bst.add("G");
    assertEquals("[G M]", bst.inOrder());
    bst.add("R");
    assertEquals("[G M R]", bst.inOrder());
  }

  @Test public void testPreOrder() {
    BinarySearchTree<String> bst = new BinarySearchTreeImpl<String>();
    assertEquals("[]", bst.preOrder());
    bst.add("M");
    assertEquals("[M]", bst.preOrder());
    bst.add("G");
    assertEquals("[M G]", bst.preOrder());
    bst.add("R");
    assertEquals("[M G R]", bst.preOrder());
  }

  @Test public void testPostOrder() {
    BinarySearchTree<String> bst = new BinarySearchTreeImpl<String>();
    assertEquals("[]", bst.postOrder());
    bst.add("M");
    assertEquals("[M]", bst.postOrder());
    bst.add("G");
    assertEquals("[G M]", bst.postOrder());
    bst.add("R");
    assertEquals("[G R M]", bst.postOrder());
  }

  @Test public void testPresent() {
    BinarySearchTree<String> bst = new BinarySearchTreeImpl<String>();
    assertFalse(bst.present("M"));
    bst.add("M");
    assertTrue(bst.present("M"));
    bst.add("S");
    assertTrue(bst.present("S"));

    BinarySearchTree<Integer> bst1 = new BinarySearchTreeImpl<Integer>();
    Set<Integer> s = new HashSet();
    TreeNode<Integer> tn = new LeafNode<Integer>();
    Random r = new Random(678);
    for (int test = 1; test < 5000; test++) {
      Integer i = r.nextInt();
      while (!s.add(i)) {
        i = r.nextInt();
      }
      bst1.add(i);
      assertTrue(bst1.present(i));
    }
    for (Integer j : s) {
      assertTrue(bst1.present(j));
    }
    for (int test = 1; test < 1000; test++) {
      Integer i = r.nextInt();
      while (s.contains(i)) {
        i = r.nextInt();
      }
      assertFalse(bst1.present(i));
    }
  }

  @Test public void testMinimum() {
    BinarySearchTree<Integer> bst = new BinarySearchTreeImpl<Integer>();
    assertNull(bst.minimum());
    bst.add(2);
    assertEquals(Integer.valueOf(2), bst.minimum());
    bst.add(4);
    assertEquals(Integer.valueOf(2), bst.minimum());
    bst.add(1);
    assertEquals(Integer.valueOf(1), bst.minimum());
    bst.add(8);
    assertEquals(Integer.valueOf(1), bst.minimum());
    bst.add(101);
    assertEquals(Integer.valueOf(1), bst.minimum());
  }

  @Test public void testMaximum() {
    BinarySearchTree<Integer> bst = new BinarySearchTreeImpl<Integer>();
    assertNull(bst.maximum());
    bst.add(Integer.valueOf(5));
    assertEquals(Integer.valueOf(5), bst.maximum());
    bst.add(1);
    assertEquals(Integer.valueOf(5), bst.maximum());
    bst.add(3);
    assertEquals(Integer.valueOf(5), bst.maximum());
    bst.add(12);
    assertEquals(Integer.valueOf(12), bst.maximum());
  }

  @Test public void testHeight() {
    BinarySearchTree<Double> bst = new BinarySearchTreeImpl<Double>();
    assertEquals(0, bst.height());
    Random r = new Random(987);
    Double smallest;
    Double rootValue = Double.valueOf(r.nextDouble());
    bst.add(rootValue);
    smallest = rootValue;
    assertEquals(1, bst.height());
    for (int test = 2; test < 500; test++) {
      smallest = Double.valueOf(smallest - r.nextDouble());
      Double bigger = Double.valueOf(r.nextDouble() + rootValue);

      bst.add(smallest);
      assertEquals(test, bst.height());
      bst.add(bigger);
      assertEquals("root: " + rootValue + " tree : " + bst.inOrder(), test, bst.height());
    }
    BinarySearchTree<Integer> bst1 = new BinarySearchTreeImpl<Integer>();
    bst1.add(Integer.valueOf(90));
    bst1.add(Integer.valueOf(93));
    bst1.add(Integer.valueOf(88));
    bst1.add(Integer.valueOf(80));
    bst1.add(Integer.valueOf(89));
    bst1.add(Integer.valueOf(70));
    bst1.add(Integer.valueOf(6));
    System.out.println(bst1.height());
  }

  @Test public void testSize() {
    BinarySearchTree<Double> bst = new BinarySearchTreeImpl<Double>();
    assertEquals(0, bst.size());
    Random r = new Random(987);
    for (int test = 1; test < 5000; test++) {
      bst.add(Double.valueOf(r.nextDouble()));
      assertEquals(test, bst.size());
    }
  }

}