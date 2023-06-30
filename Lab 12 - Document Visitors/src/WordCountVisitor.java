package document;
import document.elements.*;

/**
 * A class to visit a document and count each word in that document.
 */
public class WordCountVisitor implements TextElementVisitor<Integer> {
  @Override public Integer visitBasicText(BasicText text) {
    return text.getText().split(" ").length;
  }

  @Override public Integer visitBoldText(BoldText text) {
    return this.visitBasicText(text);
  }

  @Override public Integer visitHeading(Heading text) {
    return this.visitBasicText(text);
  }

  @Override public Integer visitHyperText(HyperText text) {
    return this.visitBasicText(text);
  }

  @Override public Integer visitItalicText(ItalicText text) {
    return this.visitBasicText(text);
  }

  @Override public Integer visitParagraph(Paragraph text) {
    return this.visitBasicText(new BasicText(text.getText()));
  }
}
