package document;

import document.elements.TextElement;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that represents a document. It contains a list of the elements of the
 * document in the order that they appear in the document. This class is
 * provided as a starting point for the Visitor lab in CS 5010.
 */
public class Document {

  private List<TextElement> content;

  /** Default constructor initializes the fields of the class. */
  public Document() {
    content = new ArrayList<>();
  }

  /**
   * Add an element to the document. Elements are added in order.
   * 
   * @param e the element to add
   */
  public void add(TextElement e) {
    content.add(e);
  }

  public int countWords() {
    TextElementVisitor<Integer> visitor = new WordCountVisitor();
    int total = 0;
    for (TextElement e : content) {
      total += e.accept(visitor);
    }
    return total;
  }

  public String toText(TextElementVisitor<?> visitor) {
    String html = "";

    for (TextElement e : content) {
      if (visitor instanceof BasicStringVisitor) {
        html += e.accept(visitor) + " ";
      } else {
        html += e.accept(visitor);
      }
    }
    if (html.length() == 0) {
      return html;
    }
    return html.trim();
  }
}
