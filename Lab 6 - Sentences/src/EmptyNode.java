package sentence;

/**
 * The WordNode class defines the methods as required for an empty string.
 */
public class EmptyNode implements Sentence {

  /**
   * Return the number of words in the sentence.
   *
   * @return the number of words in the sentence.
   */
  @Override public int getNumberOfWords() {
    return 0;
  }

  /**
   * Return the (first) longest word in the sentence.
   *
   * @return the longest word in the sentence.
   */
  @Override public String longestWord() {
    return "";
  }

  /**
   * Merge two sentences together.
   *
   * @param other the sentence to add to the end of this sentence.
   * @return the merged sentence
   */
  @Override public Sentence merge(Sentence other) {
    return other.duplicate();
  }

  /**
   * Return a duplicate of this sentence.
   *
   * @return a duplicate of this sentence
   */
  @Override public Sentence duplicate() {
    return new EmptyNode();
  }

  /**
   * Convert the sentences into a single string representation.
   *
   * @return a string representation of this sentence
   */
  @Override public String toString() {
    return "";
  }
}
