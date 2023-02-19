package questions;

/**
 * This class the MultipleChoice class extends the AbstractQuestion class and implements methods
 * it requires.
 */
public class MultipleChoice extends AbstractQuestion {

  private final String[] options;

  /**
   * Creates a multiple choice question.
   * @param questionText - question text of the question
   * @param actualAnswer - the actual answer to the quesion
   * @param options - a variable number of string parameters that are possible answers to the ques
   * @throws IllegalArgumentException - if fewer than 3 or greater than 8 options
   */
  public MultipleChoice(String questionText, String actualAnswer, String ...options)
      throws IllegalArgumentException {
    super(questionText, actualAnswer);
    if (options.length < 3) {
      throw new IllegalArgumentException("You must have at least 3 options.");
    }
    if (options.length > 5) {
      throw new IllegalArgumentException("You can only have 8 options in total");
    }
    this.options = options;
  }

  /**
   * Compares objects.
   * @param o - Question object
   * @return -1, 0, 1
   */
  @Override public int compareTo(Question o) {
    if (o instanceof Likert || o instanceof MultipleSelect) {
      return -1;
    } else if (o instanceof TrueFalse) {
      return 1;
    }
    return (int) Math.signum(this.getText().compareTo(o.getText()) * 1.0);
  }
}
