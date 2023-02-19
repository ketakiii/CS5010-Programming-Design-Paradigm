package questions;

/**
 * This class Likert defines the methods required in this class.
 * This type of question can be created by passing the text of the question. Since this question
 * asks an opinion, there is no "correct" answer. An answer can be entered as one of the option
 * numbers, numbered from 1 in the above order. Any valid option number is a "correct" answer.
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

  /**
   * This method checks the equalsLikert.
   * @param other - object
   * @return boolean compare to 0
   */
  @Override protected boolean equalsLikert(Likert other) {
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
      return aques.equalsLikert(this);
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
