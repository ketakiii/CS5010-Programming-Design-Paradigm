package questions;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * This class MultipleSelect extends AbstractQuestion class and defines the method
 * as required by the class.
 */
public class MultipleSelect extends AbstractQuestion {

  private String[] options;

  /**
   * Constructor of the MultipleSelect class.
   * @param questionText - the question text
   * @param actualAnswer - the actual answer of the question
   * @param options - the options to be given (less than 8, more than 3)
   */
  public MultipleSelect(String questionText, String actualAnswer, String... options) {
    super(questionText, actualAnswer);

    if (options.length < 3) {
      throw new IllegalArgumentException("Should have at least 3 options.");
    }
    if (options.length > 8) {
      throw new IllegalArgumentException("There must be 8 or fewer options");
    }
    this.options = options;
  }

  /**
   * The class answer defines how to check for the correct answer.
   * @param answer - answer entered by the user
   * @return if Correct or Incorrect answer
   */
  @Override public String answer(String answer) {
    String[] actualList = this.actualAnswer.split(" ");
    String[] guessList = answer.split(" ");

    Set<String> actualSet = new HashSet<String>();
    Arrays.stream(actualList).forEach(element -> actualSet.add(element));
    Set<String> guessSet = new HashSet<String>();
    Arrays.stream(guessList).forEach(element -> guessSet.add(element));

    if (actualSet.equals(guessSet)) {
      return Question.CORRECT;
    } else {
      return Question.INCORRECT;
    }
  }

  /**
   * This method compares objects.
   * @param o - Question bject
   * @return 1, 0, -1
   */
  @Override public int compareTo(Question o) {
    if (o instanceof Likert) {
      return -1;
    } else if (o instanceof TrueFalse || o instanceof MultipleChoice) {
      return 1;
    }
    return (int) Math.signum(this.getText().compareTo(o.getText()) * 1.0);
  }
}

