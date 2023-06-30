package document;

import document.elements.BasicText;
import document.elements.BoldText;
import document.elements.ItalicText;
import document.elements.Heading;
import document.elements.HyperText;
import document.elements.Paragraph;

import java.util.List;

/**
 * Generates an HTML version of a document that this visits.
 * HTML formatting tags are added based on the element types.
 */
public class HtmlStringVisitor implements TextElementVisitor<String> {

  @Override public String visitBasicText(BasicText text) {
    return text.getText() + "\n";
  }

  @Override public String visitBoldText(BoldText text) {
    return "<b>" + text.getText() + "</b>\n";
  }

  @Override public String visitHeading(Heading text) {
    return "<h" + text.getLevel() + ">" + text.getText() + "</h" + text.getLevel() + ">\n";
  }

  @Override public String visitHyperText(HyperText text) {
    return "<a href=\"" + text.getUrl() + "\">" + text.getText() + "</a>\n";
  }

  @Override public String visitItalicText(ItalicText text) {
    return "<i>" + text.getText() + "</i>\n";
  }

  @Override public String visitParagraph(Paragraph text) {
    String html = "<p>";
    for (BasicText element : text.getContent()) {
      html += element.accept(this);
    }
    return html + "</p>\n";
  }
}
