package sentence;

/**
 * The WordNode class defines the methods as required for a punctuation.
 */
public class PunctuationNode implements Sentence {

  private String punctuation;
  protected Sentence rest;

  public PunctuationNode(String punctuation, Sentence rest) {
    this.punctuation = punctuation;
    this.rest = rest;
  }

  /**
   * Return the number of words in the sentence. Punctuation does not count as a
   * word.
   *
   * @return the number of words in the sentence.
   */
  @Override public int getNumberOfWords() {
    return this.rest.getNumberOfWords();
  }

  /**
   * Return the (first) longest word in the sentence.
   *
   * @return the longest word in the sentence.
   */
  @Override public String longestWord() {
    return this.rest.longestWord();
  }

  /**
   * Merge two sentences together.
   *
   * @param other the sentence to add to the end of this sentence.
   * @return the merged sentence
   */
  @Override public Sentence merge(Sentence other) {
    return new PunctuationNode(new String(this.punctuation), this.rest.merge(other));
  }

  /**
   * Return a duplicate of this sentence.
   *
   * @return a duplicate of this sentence
   */
  @Override public Sentence duplicate() {
    return new PunctuationNode(new String(this.punctuation), this.rest.duplicate());
  }

  /**
   * Convert the sentences into a single string representation.
   *
   * @return a string representation of this sentence
   */
  @Override public String toString() {
    String result = this.rest.toString();
      if (!"".equals(result) && Character.isLetterOrDigit(result.charAt(0))) {
        return this.punctuation + " " + result;
      }
    return this.punctuation + result;
  }
}
