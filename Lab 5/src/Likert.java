package questions;

/**
 * This class Likert defines the methods required in this class.
 */
public class Likert extends AbstractQuestion {

  /**
   * Constructor to the Likert class.
   * @param questionText - question text
   */
  public Likert(String questionText) {
    super(questionText, null);
  }

  /**
   * The answer method checks if the answer given by the user is correct or incorrect.
   * @param answer the answer given
   * @return Correct or Incorrect answer
   */
  @Override public String answer(String answer) {
    if ("1".equals(answer) || "2".equals(answer) || "3".equals(answer)
        || "4".equals(answer) || "5".equals(answer)) {
      return Question.CORRECT;
    }
    return Question.INCORRECT;
  }

  /**
   * Compares objects.
   * @param o - Question object
   * @return -1, 0 or 1
   */
  @Override public int compareTo(Question o) {
    if (o instanceof Likert) {
      return (int) Math.signum(this.getText().compareTo(o.getText()) * 1.0);
    }
    return 1;
  }
}
