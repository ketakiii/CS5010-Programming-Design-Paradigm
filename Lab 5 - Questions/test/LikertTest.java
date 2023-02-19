package questions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

/**
 * This class test the Likert class and its methods.
 */
public class LikertTest {

  /**
   * This class tests the getText method of the Likert class.
   */
  @Test public void testGetText() {
    String expected = "I am a person who likes tea.";
    Question l = new Likert(expected);
    assertEquals(expected, l.getText());
  }

  /**
   * This method tests the answer method of the Likert class.
   */
  @Test public void testAnswer() {
    Question l = new Likert("I am a person who likes tea.");
    String actual = l.answer("1");
    assertEquals(Question.CORRECT, actual);
    actual = l.answer("2");
    assertEquals(Question.CORRECT, actual);
    actual = l.answer("3");
    assertEquals(Question.CORRECT, actual);
    actual = l.answer("4");
    assertEquals(Question.CORRECT, actual);
    actual = l.answer("5");
    assertEquals(Question.CORRECT, actual);

    actual = l.answer("Hello");
    assertEquals(Question.INCORRECT, actual);
    actual = l.answer("6");
    assertEquals(Question.INCORRECT, actual);
  }

  @Test public void testCompareTo() {
    Question tf1 = new TrueFalse("I am a Robot", "False");

    Question mc = new MultipleChoice("I am a ", "2", "Robot", "Human", "Cyborg");
    Question ms = new MultipleSelect("I am a ", "2 3 4", "Robot", "Human", "Computer Scientist",
        "Gamer");
    Question l = new Likert("I am a robot");

    assertEquals(1, l.compareTo(tf1));
    assertEquals(1, l.compareTo(mc));
    assertEquals(1, l.compareTo(ms));
  }
}
