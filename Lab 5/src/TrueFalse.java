package questions;

import java.util.Locale;

/**
 * The TrueFalse class extends the AbstractQuestion class and defines other methods related.
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
}
