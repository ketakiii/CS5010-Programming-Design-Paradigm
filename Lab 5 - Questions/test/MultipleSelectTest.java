package questions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

/**
 * This class is to test the Multiple Select class and its methods.
 */
public class MultipleSelectTest {

  /**
   * A test to check if the Multiple Select works correctly with invalid options as well.
   */
  @Test public void testInvalidOptions() {
    Question ms = new MultipleSelect("Which of these teams played in New York:",
        "1 3", "Nets", "Raptors", "Knicks", "Rangers", "Celtics");
    String expected = ms.answer("1 3 4");
    assertEquals(Question.INCORRECT, expected);

    String expected1 = ms.answer("1 3");
    assertEquals(Question.CORRECT, expected1);

    expected = ms.answer("1 1 3");
    assertEquals(Question.CORRECT, expected);

    String actual = ms.answer("1");
    assertEquals(Question.INCORRECT, actual);
  }

  @Test public void testCompareTo() {
    Question tf1 = new TrueFalse("I am a Robot", "False");

    Question mc = new MultipleChoice("I am a ", "2", "Robot", "Human", "Cyborg");
    Question ms = new MultipleSelect("I am a ", "2 3 4", "Robot", "Human", "Computer Scientist",
        "Gamer");
    Question l = new Likert("I am a robot");

    assertEquals(-1, ms.compareTo(l));
    assertEquals(1, ms.compareTo(tf1));
    assertEquals(1, ms.compareTo(mc));
  }
}
