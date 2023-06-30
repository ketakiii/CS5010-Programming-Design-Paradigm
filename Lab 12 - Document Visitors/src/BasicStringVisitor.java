package document;

import document.elements.*;

/**
 * A class that visits a document and generates a simple string representation of that text in
 * that document. The text of each element is added one at a time using a space between each
 * element. Additional information (the heading level if it is bold...) is ignored.
 */

public class BasicStringVisitor implements TextElementVisitor<String> {

  @Override public String visitBasicText(BasicText text) {
    return text.getText();
  }

  @Override public String visitBoldText(BoldText text) {
    return this.visitBasicText(text);
  }

  @Override public String visitHeading(Heading text) {
    return this.visitBasicText(text);
  }

  @Override public String visitHyperText(HyperText text) {
    return this.visitBasicText(text);
  }

  @Override public String visitItalicText(ItalicText text) {
    return this.visitBasicText(text);
  }

  @Override public String visitParagraph(Paragraph text) {
    return text.getText();
  }
}
