package questions;

/**
 * This class the MultipleChoice class extends the AbstractQuestion class and implements methods
 * it requires.
 * This type of question can be created by passing the text of the question, the correct answer
 * and the options. A question must have at least 3 options, but no more than 8. An answer can
 * be entered as one of the option numbers in a string. For example, "1", "3", etc.
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

  /**
   * This method checks the equalsMultipleChoice.
   * @param other - object
   * @return boolean compare to 0
   */
  @Override protected boolean equalsMultipleChoice(MultipleChoice other) {
    return this.compareTo(other) == 0;
  }

  /**
   * This method checks the equality.
   * @param obj - object
   * @return boolean
   */
  @Override public boolean equals(Object obj) {
    if (obj instanceof AbstractQuestion) {
      AbstractQuestion aques = (AbstractQuestion) obj;
      return aques.equalsMultipleChoice(this);
    }
    return false;
  }

  /**
   * This method gives us a hashcode.
   * @return hashcode
   */
  @Override public int hashCode() {
    return this.getText().length();
  }
}
