package sentence;

/**
 * The WordNode class defines the methods as required for a word.
 */
public class WordNode implements Sentence {

  private String word;
  private Sentence rest;

  public WordNode(String word, Sentence rest) {
    this.word = word;
    this.rest = rest;
  }

  /**
   * Return the number of words in the sentence.
   *
   * @return the number of words in the sentence.
   */
  @Override public int getNumberOfWords() {
    return 1 + this.rest.getNumberOfWords();
  }

  /**
   * Return the (first) longest word in the sentence.
   *
   * @return the longest word in the sentence.
   */
  @Override public String longestWord() {
    String longest = this.rest.longestWord();
    if (this.word.length() >= longest.length()) {
      return this.word;
    }
    return longest;
  }

  /**
   * Merge two sentences together.
   *
   * @param other the sentence to add to the end of this sentence.
   * @return the merged sentence
   */
  @Override public Sentence merge(Sentence other) {
    return new WordNode(new String(this.word), this.rest.merge(other));
  }

  /**
   * Return a duplicate of this sentence.
   *
   * @return a duplicate of this sentence
   */
  @Override public Sentence duplicate() {
    return new WordNode(new String(this.word), this.rest.duplicate());
  }

  /**
   * Convert the sentences into a single string representation.
   *
   * @return a string representation of this sentence
   */
  @Override public String toString() {
    String result = this.rest.toString();
      if (!"".equals(result) && Character.isLetterOrDigit(result.charAt(0))) {
        return this.word + " " + result;
      }
    return this.word + result;
  }
}
