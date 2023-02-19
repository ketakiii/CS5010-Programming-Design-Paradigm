package questions;

import static org.junit.Assert.assertEquals;

import java.sql.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.junit.Test;
import questions.Question;
import questions.TrueFalse;

/**
 * This class is to test the TrueFalse class and its methods .
 */
public class TrueFalseTest {

  /**
   * This method tests the getText method of the TrueFalse class.
   */
  @Test public void testGetText() {
    Question tf;
    String expected = "This is a true/false question.";
    tf = new TrueFalse(expected, "true");
    assertEquals(expected, tf.getText());

    String expected1 = "It is Feb!";
    tf = new TrueFalse(expected1, "true");
    assertEquals(expected1, tf.getText());

    String expected2 = "It is not Feb!";
    tf = new TrueFalse(expected2, "true");
    assertEquals(expected2, tf.getText());

    Random random = new Random(54321);
    for (int test = 0; test < 5000; test++) {
      String expected3 = "" + random.nextInt();
      tf = new TrueFalse(expected3, "false");
      assertEquals(expected3, tf.getText());
    }
  }

  /**
   * This method tests the answer method of the TrueFalse class.
   */
  @Test public void testAnswer() {
    Question tf;
    String questionText = "This is a true/false question.";
    tf = new TrueFalse(questionText, "true");
    String isCorrect = tf.answer("False");
    assertEquals(Question.INCORRECT, isCorrect);

    isCorrect = tf.answer("True");
    assertEquals(Question.CORRECT, isCorrect);

    isCorrect = tf.answer("Happy");
    assertEquals(Question.INCORRECT, isCorrect);
  }

  /**
   * This method tests the compareTo method of the TrueFalse.
   */
  @Test public void testCompareTo() {
    Question tf1 = new TrueFalse("I am a Robot", "False");

    Question mc = new MultipleChoice("I am a ", "2", "Robot",
        "Human", "Cyborg");
    Question ms = new MultipleSelect("I am a ", "2 3 4", "Robot",
        "Human", "Computer Scientist", "Gamer");
    Question l = new Likert("I am a robot");

    assertEquals(-1, tf1.compareTo(mc));
    assertEquals(-1, tf1.compareTo(ms));
    assertEquals(-1, tf1.compareTo(l));

    Question tf2 = new TrueFalse("I am a Person", "True");
    Question tf3 = new TrueFalse("I am a Robot", "False");

    assertEquals(1, tf1.compareTo(tf2));
    assertEquals(0, tf1.compareTo(tf3));
    assertEquals(0, tf3.compareTo(tf1));
    assertEquals(-1, tf2.compareTo(tf1));

    assertEquals(1, mc.compareTo(tf1));
    assertEquals(1, ms.compareTo(tf1));
    assertEquals(1, l.compareTo(tf1));

    Question[] questions = {tf3, l, tf1, mc, ms, tf2};
    Arrays.sort(questions);
    Question[] expected = {tf2, tf3, tf1, mc, ms, l};
    assertEquals(expected, questions);
  }

  @Test public void testSort() {
    Question[] questionsList = new Question[4];
    Question l = new Likert("I am a robot");
    questionsList[0] = l;
    Question ms = new MultipleSelect("I am a ", "2 3 4", "Robot", "Human", "Computer Scientist",
        "Gamer");
    questionsList[1] = ms;
    Question mc = new MultipleChoice("I am a ", "2", "Robot", "Human", "Cyborg");
    questionsList[2] = mc;
    Question tf1 = new TrueFalse("I am a Robot", "False");
    questionsList[3] = tf1;
    Arrays.sort(questionsList);
    assertEquals(tf1, questionsList[0]);
    assertEquals(mc, questionsList[1]);
    assertEquals(ms, questionsList[2]);
    assertEquals(l, questionsList[3]);
  }
}
