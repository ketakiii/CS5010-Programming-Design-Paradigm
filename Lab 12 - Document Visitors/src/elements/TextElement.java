package document.elements;

import document.TextElementVisitor;

/**
 * Representation for any of the text elements of a document.
 */
public interface TextElement {

  /**
   * Returns the text of the element.
   * 
   * @return the text
   */
  public String getText();

  /**
   * Accepts the types of inputs with a visitor.
   * @param visitor visitor
   * @param <R> type of input
   * @return R
   */
  <R> R accept (TextElementVisitor<R> visitor);
}
