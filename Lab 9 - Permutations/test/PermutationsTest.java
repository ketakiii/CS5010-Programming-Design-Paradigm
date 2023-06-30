package permutations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.NoSuchElementException;
import java.util.Random;
import org.junit.Test;

/**
 * This class tests the Permutation class and its methods.
 */
public class PermutationsTest {

  @Test public void testTwoParameterConstructor() {
    Permutations perm = new Permutations("abcd", 1);
    assertEquals("abcd, 1", perm.toString());
    String alphabet = "abcdefghijklmnopqrstuvwxyz";
    Random random = new Random(9999);
    for (int test = 0; test < 5000; test++) {
      int stringLength = random.nextInt(alphabet.length()) + 1;
      String input = "";
      while (input.length() < stringLength) {
        input += alphabet.charAt(random.nextInt(alphabet.length()));
      }
      int start = random.nextInt(stringLength) + 1;
      perm = new Permutations(input, start);
      assertEquals(input + ", " + start, perm.toString());
    }
  }
  
  @Test public void testTwoParameterConstructorBadStart() {
    Permutations perm;
    String alphabet = "abcdefghijklmnopqrstuvwxyz";
    Random random = new Random(9999);
    for (int test = 0; test < 5000; test++) {
      int stringLength = random.nextInt(alphabet.length()) + 1;
      String input = "";
      while (input.length() < stringLength) {
        input += alphabet.charAt(random.nextInt(alphabet.length()));
      }
      int start = - 1 * random.nextInt(stringLength);
      try {
        perm = new Permutations(input, start);
        fail("Starting parameter is too small, so exception should be thrown!");
      } catch (IllegalArgumentException e) {
        // do nothing
      }
      start = random.nextInt(stringLength) + 1 + stringLength;
      try {
        perm = new Permutations(input, start);
        fail("Starting parameter is too big, so exception should be thrown!");
      } catch (IllegalArgumentException e) {
        // do nothing
      }
    }
  }

  @Test public void testTwoParameterBadString() {
    Permutations perm;
    String alphabet = "abcdefghijklmnopqrstuvwxyz";
    Random random = new Random(9999);
    for (int test = 0; test < 5000; test++) {
      int stringLength = random.nextInt(alphabet.length()) + 1;
      String input = "";
      while (input.length() < stringLength) {
        input += alphabet.charAt(random.nextInt(alphabet.length()));
      }
      int start = random.nextInt(stringLength);
      int number = random.nextInt();
      String oldInput = input;
      input += number;
      try {
        perm = new Permutations(input, start);
        fail("Input String is not alphabetic, so exception should be thrown!");
      } catch (IllegalArgumentException e) {
        // do nothing
      }
      input = number + oldInput;
      start = random.nextInt(stringLength) + 1 + stringLength;
      try {
        perm = new Permutations(input, start);
        fail("Starting parameter is too big, so exception should be thrown!");
      } catch (IllegalArgumentException e) {
        // do nothing
      }
    }
  }

  @Test public void testOneParameterBadString() {
    Permutations perm;
    String alphabet = "abcdefghijklmnopqrstuvwxyz";
    Random random = new Random(9999);
    for (int test = 0; test < 5000; test++) {
      int stringLength = random.nextInt(alphabet.length()) + 1;
      String input = "";
      while (input.length() < stringLength) {
        input += alphabet.charAt(random.nextInt(alphabet.length()));
      }
      int number = random.nextInt();
      String oldInput = input;
      input += number;
      try {
        perm = new Permutations(input);
        fail("Input String is not alphabetic, so exception should be thrown!");
      } catch (IllegalArgumentException e) {
        // do nothing
      }
      input = number + oldInput;
      int start = random.nextInt(stringLength);
      start = random.nextInt(stringLength) + 1 + stringLength;
      try {
        perm = new Permutations(input);
        fail("Starting parameter is too big, so exception should be thrown!");
      } catch (IllegalArgumentException e) {
        // do nothing
      }
    }
  }

  @Test public void testOneParameterConstructor() {
    Permutations perm = new Permutations("abcd", 1);
    assertEquals("abcd, 1", perm.toString());
    String alphabet = "abcdefghijklmnopqrstuvwxyz";
    Random random = new Random(9999);
    for (int test = 0; test < 5000; test++) {
      int stringLength = random.nextInt(alphabet.length()) + 1;
      String input = "";
      while (input.length() < stringLength) {
        input += alphabet.charAt(random.nextInt(alphabet.length()));
      }
      int start = 1;
      perm = new Permutations(input, start);
      assertEquals(input + ", " + start, perm.toString());
    }
  }

  @Test public void testHasNext() {
    Permutations p = new Permutations("star");
    p.next();
    assertTrue(p.hasNext());
    p.next();
    assertTrue(p.hasNext());
    p.next();
    assertTrue(p.hasNext());
    p.next();
    assertTrue(p.hasNext());
    p.next();
    assertTrue(p.hasNext());
    p.next();
    assertTrue(p.hasNext());
    p.next();
    assertTrue(p.hasNext());
    p.next();
    assertTrue(p.hasNext());
    p.next();
    assertTrue(p.hasNext());
    p.next();
    assertTrue(p.hasNext());
    p.next();
    assertTrue(p.hasNext());
    p.next();
    assertTrue(p.hasNext());
    p.next();
    assertTrue(p.hasNext());
    p.next();
    assertFalse(p.hasNext());
  }

  @Test public void testNext() {
    Permutations p = new Permutations("star");
    assertEquals("s", p.next());
    assertEquals("t", p.next());
    assertEquals("a", p.next());
    assertEquals("r", p.next());
    assertEquals("st", p.next());
    assertEquals("sa", p.next());
    assertEquals("sr", p.next());
    assertEquals("ta", p.next());
    assertEquals("tr", p.next());
    assertEquals("ar", p.next());
    assertEquals("sta", p.next());
    assertEquals("str", p.next());
    assertEquals("tar", p.next());
    assertEquals("star", p.next());

    p = new Permutations("a");
    p.next();
    try {
      p.next();
      fail("There should not be a next value, but the iterator moved to the next.");
    } catch (NoSuchElementException e) {
      //do nothing
    }
  }

  @Test public void testHasPrevious() {
    Permutations p = new Permutations("star");
    assertFalse(p.hasPrevious());
    p = new Permutations("star", 2);
    assertTrue(p.hasPrevious());
  }

  @Test public void testPrevious() {
    Permutations p = new Permutations("star", 3);
    System.out.println(p.previous());
    System.out.println(p.previous());
    System.out.println(p.previous());
  }
}