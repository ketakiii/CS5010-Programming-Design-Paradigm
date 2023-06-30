package document;

import document.elements.BasicText;
import document.elements.BoldText;
import document.elements.Heading;
import document.elements.HyperText;
import document.elements.ItalicText;
import document.elements.Paragraph;

public class MarkdownStringVisitor implements TextElementVisitor<String> {

  @Override public String visitBasicText(BasicText text) {
    return text.getText() + "\n";
  }

  @Override public String visitBoldText(BoldText text) {
    return "**" + text.getText() + "**\n";
  }

  @Override public String visitHeading(Heading text) {
    String out = "";
    for (int i = 0; i < text.getLevel(); i++) {
      out += "#";
    }
    return out + " " + text.getText() + "\n";
  }

  @Override public String visitHyperText(HyperText text) {
    return "[" + text.getText() + "](" + text.getUrl() + ")\n";
  }

  @Override public String visitItalicText(ItalicText text) {
    return "*" + text.getText() + "*\n";
  }

  @Override public String visitParagraph(Paragraph text) {
    String html = "\n";
    for (BasicText element : text.getContent()) {
      html += element.accept(this);
    }
    return html.substring(0, html.length() - 1) + "\n";
  }
}
