package sentence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.jupiter.api.Test;

/**
 * The SentenceTest class tests the methods of the classes that implement the Sentence interface
 * and override its methods.
 */
class SentenceTest {

  /**
   * Tests if the numberOfWords returns the number of words in the sentence.
   */
  @Test public void testNumberOfWords() {
    Sentence s = new EmptyNode();
    assertEquals(0, s.getNumberOfWords());

    s = new PunctuationNode("!", s);
    s = new PunctuationNode("!", s);
    assertEquals(0, s.getNumberOfWords());

    s = new WordNode("morning", s);
    assertEquals(1, s.getNumberOfWords());

    s = new WordNode("good", s);
    assertEquals(2, s.getNumberOfWords());

    s = new PunctuationNode(",", s);
    s = new WordNode("Hello", s);
    assertEquals(3, s.getNumberOfWords());

  }

  /**
   * Tests if the toString converts the sentences into a single string representation.
   */
  @Test public void testToString() {
    Sentence s = new EmptyNode();
    assertEquals("", s.toString());
    s = new PunctuationNode("!", s);
    assertEquals("!", s.toString());
    s = new WordNode("morning", s);
    assertEquals("morning!", s.toString());
    s = new WordNode("good", s);
    assertEquals("good morning!", s.toString());
    s = new PunctuationNode(",", s);
    assertEquals(", good morning!", s.toString());
    s = new WordNode("Hello", s);
    assertEquals("Hello, good morning!", s.toString());

    s = new WordNode("Bye", new EmptyNode());
    assertEquals("Bye", s.toString());

    Sentence e = new EmptyNode();
    e = new WordNode("there", e);
    e = new WordNode("hey", e);
    assertEquals("hey there", e.toString());

    Sentence e1 = new EmptyNode();
    e1 = new WordNode("there", e1);
    e1 = new PunctuationNode(",", e1);
    e1 = new WordNode("hey", e1);
    assertEquals("hey, there", e1.toString());
  }

  /**
   * Tests if the longestWord returns the (first) longest word in the sentence.
   */
  @Test public void testLongestWord() {
    Sentence s = new EmptyNode();
    assertEquals("", s.longestWord());
    s = new PunctuationNode("!", s);
    assertEquals("", s.longestWord());
    s = new WordNode("morning", s);
    assertEquals("morning", s.longestWord());
    s = new WordNode("good", s);
    assertEquals("morning", s.longestWord());
    s = new PunctuationNode(",", s);
    assertEquals("morning", s.longestWord());
    s = new WordNode("Hello", s);
    assertEquals("morning", s.longestWord());

    s = new EmptyNode();
    s = new WordNode("good", s);
    assertEquals("good", s.longestWord());
    s = new WordNode("is", s);
    assertEquals("good", s.longestWord());
    s = new WordNode("Tomorrow", s);
    assertEquals("Tomorrow", s.longestWord());

    s = new EmptyNode();
    s = new WordNode("run", s);
    assertEquals("run", s.longestWord());
    s = new WordNode("can", s);
    assertEquals("can", s.longestWord());
    s = new WordNode("Sam", s);
    assertEquals("Sam", s.longestWord());

  }

  /**
   * Tests if the duplicate method returns a duplicate of this sentence.
   */
  @Test public void testDuplicate() {
    Sentence s = new EmptyNode();
    assertEquals(s.toString(), s.duplicate().toString());
    assertEquals(s.toString(), s.duplicate().toString());
    s = new PunctuationNode(",", s);
    assertNotEquals(s, s.duplicate());
    s = new WordNode("now", s);
    assertNotEquals(s, s.duplicate());
    s = new WordNode("clone", s);
    assertNotEquals(s, s.duplicate());
    s = new WordNode("a", s);
    assertNotEquals(s, s.duplicate());
    s = new WordNode("I'm", s);
    assertNotEquals(s, s.duplicate());
    s = new WordNode("think", s);
    assertNotEquals(s, s.duplicate());
    s = new WordNode("I", s);
    Sentence d = s.duplicate();
    assertNotEquals(s, d);
    assertEquals(s.toString(), d.toString());

    Sentence a = new EmptyNode();
    a = new WordNode("Sam", a);
    a = new WordNode("there", a);
    a = new WordNode("hey", a);
    Sentence b = a.duplicate();
    assertEquals(a.toString(), b.toString());
  }

  /**
   * Tests if the merge method merges two sentences together.
   */
  @Test public void testMerge() {
    Sentence s = new EmptyNode();
    s = new PunctuationNode(".", s);
    s = new WordNode("morning", s);
    s = new WordNode("Good", s);

    Sentence t = new EmptyNode();
    t = new PunctuationNode("!", t);
    t = new WordNode("later", t);
    t = new WordNode("you", t);
    t = new WordNode("See", t);
    Sentence m = s.merge(t);
    //System.out.println(m);
    Sentence td = t.duplicate();
    assertEquals(s.toString() + " " + t.toString(), m.toString());
    s = new PunctuationNode("?", s);
    assertNotEquals(s.toString() + " " + td.toString(), m.toString());

    Sentence s1 = new EmptyNode();
    Sentence s2 = new EmptyNode();
    s2 = new PunctuationNode("!", s2);
    s2 = new WordNode("there", s2);
    s2 = new WordNode("Hey", s2);
    Sentence m1 = s1.merge(s2);
    assertEquals(s1.toString() + s2.toString(), m1.toString());

    Sentence t1 = new EmptyNode();
    t1 = new PunctuationNode("!", t1);
    t1 = new WordNode("there", t1);
    t1 = new PunctuationNode(",", t1);
    t1 = new WordNode("Hey", t1);
    Sentence t2 = new EmptyNode();
    t2 = new WordNode("Sam", t2);
    Sentence t3 = t1.merge(t2);
    assertEquals(t1.toString() + " " + t2.toString(), t3.toString());

    Sentence a = new EmptyNode();
    a = new PunctuationNode(".", a);
    a = new WordNode("time", a);
    a = new WordNode("saves", a);
    a = new WordNode("Testing", a);
    a = new WordNode("and", a);
    a = new WordNode("Designing", a);

    Sentence b = new EmptyNode();
    b = new WordNode("Awesome", b);
    b = new WordNode("is", b);
    b = new WordNode("Northeastern", b);

    Sentence c = a.merge(b);
    assertEquals(a.toString() + " " + b.toString(), c.toString());
  }


}