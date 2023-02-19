package questions;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * This class tests the MultipleChoice class and its methods.
 */
public class MultipleChoiceTest {

  /**
   * This method tests the getText method of the MultipleChoice class.
   */
  @Test public void testGetText() {
    Question mc;
    String expected = "The capital of Canada is:";
    mc = new MultipleChoice(expected, "3",
        "Toronto", "Vancouver", "Ottawa");
    assertEquals(expected, mc.getText());

    expected = "The capital of New York State is:";
    mc = new MultipleChoice(expected, "2", "New York City",
        "Albany", "Buffalo", "Ithaca");
  }

  /**
   * This method tests the getAnswer method of the MultipleChoice class.
   */
  @Test public void testGetAnswer() {
    Question mc;
    mc = new MultipleChoice("The capital of Canada is:", "3",
        "Toronto", "Vancouver", "Ottawa");
    String expected = mc.answer("3");
    assertEquals(Question.CORRECT, expected);

    expected = mc.answer("1");
    assertEquals(Question.INCORRECT, expected);

    expected = mc.answer("Ottawa");
    assertEquals(Question.INCORRECT, expected);
  }

  @Test public void testCompareTo() {
    Question tf1 = new TrueFalse("I am a Robot", "False");

    Question mc = new MultipleChoice("I am a ", "2", "Robot", "Human", "Cyborg");
    Question ms = new MultipleSelect("I am a ", "2 3 4", "Robot", "Human", "Computer Scientist",
        "Gamer");
    Question l = new Likert("I am a robot");

    assertEquals(-1, mc.compareTo(ms));
    assertEquals(-1, mc.compareTo(l));
    assertEquals(0, mc.compareTo(mc));
  }
}
