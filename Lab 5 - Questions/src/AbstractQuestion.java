package questions;

import java.util.Locale;

/**
 * This class AbstractQuestion is an abstract class that implements the Question interface
 * and defines a few common methods to other classes.
 */
public abstract class AbstractQuestion implements Question {

  protected String actualAnswer;
  private String questionText;

  /**
   * Constructor to the AbstractQuestion class.
   * @param questionText - the question text
   * @param actualAnswer - the actual answer to the question
   */
  public AbstractQuestion(String questionText, String actualAnswer) {
    this.questionText = questionText;
    this.actualAnswer = actualAnswer;
  }

  /**
   * This method defines the answer method common to the question classes.
   * @param answer the answer given
   * @return if the answer is correct or not
   */
  @Override public String answer(String answer) {
    if (answer.toLowerCase().equals(this.actualAnswer.toLowerCase())) {
      return Question.CORRECT;
    }
    return Question.INCORRECT;
  }

  /**
   * This method gets the question text.
   * @return the question text
   */
  @Override public String getText() {
    return this.questionText;
  }

  /**
   * Compares objects.
   * @param o - Question object
   * @return -1, 0 or 1
   */
  @Override public abstract int compareTo(Question o);

  protected boolean equalsLikert(Likert other) {
    return false;
  }

  protected boolean equalsTrueFalse(TrueFalse other) {
    return false;
  }

  protected boolean equalsMultipleSelect(MultipleSelect other) {
    return false;
  }

  protected boolean equalsMultipleChoice(MultipleChoice other) {
    return false;
  }
}
