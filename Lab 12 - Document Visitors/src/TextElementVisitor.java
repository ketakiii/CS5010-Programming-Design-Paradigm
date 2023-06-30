package document;

import document.elements.*;

public interface TextElementVisitor<R> {

  /**
   * Visit BasicText and perform the action required while visiting it.
   * @param text the basic text
   * @return whatever object is returned from the visiting action
   */
  R visitBasicText(BasicText text);
  
  R visitBoldText(BoldText text);

  R visitHeading(Heading text);

  R visitHyperText(HyperText text);

  R visitItalicText(ItalicText text);

  R visitParagraph(Paragraph text);

}
