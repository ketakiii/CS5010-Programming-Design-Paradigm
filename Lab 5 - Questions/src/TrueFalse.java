package questions;

import java.util.Locale;

/**
 * The TrueFalse class extends the AbstractQuestion class and defines other methods related.
 * This type of question can be created by passing the text of the question and the correct answer.
 * The only valid answer to this type of question is a "True" or "False".
 */
public class TrueFalse extends AbstractQuestion {

  /**
   * Constructor of the TrueFalse class.
   * @param questionText - contains the question text
   * @param actualAnswer - contains the actual answer
   */
  public TrueFalse(String questionText, String actualAnswer) {
    super(questionText, actualAnswer);
  }

  /**
   * Compares the two objects.
   * @param o - given question object
   * @return 1, 0 or -1
   */
  @Override public int compareTo(Question o) {
    if (o instanceof TrueFalse) {
      return (int) Math.signum(this.getText().compareTo(o.getText()) * 1.0);
    }
    return -1;
  }

  /**
   * This method checks the equalsTrueFalse.
   * @param other - object
   * @return boolean compare to 0
   */
  @Override protected boolean equalsTrueFalse(TrueFalse other) {
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
      return aques.equalsTrueFalse(this);
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
