package document;

import document.elements.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DocumentTest {

  private BasicText basicText;
  private BoldText boldText;
  private Heading heading1;
  private Heading heading2;
  private HyperText hyperText;
  private ItalicText italicText;
  private Paragraph p;
  private Document doc;

  @Before
  public void setUp() {
    basicText = new BasicText("Basic Text Sample");
    boldText = new BoldText("Bold Text Sample");
    heading1 = new Heading("Heading Sample 1", 1);
    heading2 = new Heading("Heading Sample 2", 2);
    hyperText = new HyperText("Here is a Hyper Text", "northeastern.edu");
    italicText = new ItalicText("Italic text is exciting");
    p = new Paragraph();
    p.add(basicText);
    p.add(boldText);
    p.add(heading1);
    p.add(heading2);
    p.add(hyperText);
    p.add(italicText);

    doc = new Document();
  }

  /**
   * Test all the TextElementTypes to make sure the WordCountVisitor properly returns the
   * number of words in that TextElement.
   */
  @Test public void testWordCountVisitor() {
    doc.add(basicText);
    assertEquals(3, doc.countWords());
    doc.add(boldText);
    assertEquals(6, doc.countWords());
    doc.add(heading1);
    assertEquals(9, doc.countWords());
    doc.add(heading2);
    assertEquals(12, doc.countWords());
    doc.add(hyperText);
    assertEquals(17, doc.countWords());
    doc.add(italicText);
    assertEquals(21, doc.countWords());
    doc.add(p);
    assertEquals(42, doc.countWords());
    }

    @Test public void testTextElementVisitor() {
    String expected = "";
    TextElementVisitor<String> visitor = new BasicStringVisitor();
    doc.add(basicText);
    expected += basicText.getText();
    assertEquals(expected, doc.toText(visitor));

    doc.add(boldText);
    expected += " " + boldText.getText();
    assertEquals(expected, doc.toText(visitor));

    doc.add(heading1);
    expected += " " + heading1.getText();
    assertEquals(expected, doc.toText(visitor));

    doc.add(heading2);
    expected += " " + heading2.getText();
    assertEquals(expected, doc.toText(visitor));

    doc.add(hyperText);
    expected += " " + hyperText.getText();
    assertEquals(expected, doc.toText(visitor));

    doc.add(italicText);
    expected += " " + italicText.getText();
    assertEquals(expected, doc.toText(visitor));

    doc.add(p);
    expected += " " + p.getText();
    assertEquals(expected, doc.toText(visitor));
    }

    @Test public void testHtmlVisitor() {
      String expected = "";
      TextElementVisitor visitor = new HtmlStringVisitor();

      doc.add(basicText);
      expected += basicText.getText() + "\n";
      assertEquals(expected, doc.toText(visitor));

      doc.add(boldText);
      expected += " <b>" + boldText.getText() + "</b>\n";
      assertEquals(expected, doc.toText(visitor));

      doc.add(heading1);
      expected += " <h1>" + heading1.getText() + "</h1>\n";
      assertEquals(expected, doc.toText(visitor));

      doc.add(heading2);
      expected += " <h2>" + heading2.getText() + "</h2>\n";
      assertEquals(expected, doc.toText(visitor));

      doc.add(hyperText);
      expected += " <a href=" + hyperText.getUrl() + ">" + hyperText.getText() + "</a>\n";
      assertEquals(expected, doc.toText(visitor));

      doc.add(italicText);
      expected += " <i>" + italicText.getText() + "</i>\n";
      assertEquals(expected, doc.toText(visitor));

      doc.add(p);
      expected += " <p>" + expected + "</p>\n";
      assertEquals(expected, doc.toText(visitor));
    }

    @Test public void testMarkdownVisitor() {
      String expected = "";
      TextElementVisitor visitor = new MarkdownStringVisitor();

      doc.add(basicText);
      expected += basicText.getText() + "\n";
      assertEquals(expected, doc.toText(visitor));

      doc.add(boldText);
      expected += " **" + boldText.getText() + "**\n";
      assertEquals(expected, doc.toText(visitor));

      doc.add(heading1);
      expected += " #" + heading1.getText() + "\n";
      assertEquals(expected, doc.toText(visitor));

      doc.add(heading2);
      expected += " ##" + heading2.getText() + "\n";
      assertEquals(expected, doc.toText(visitor));

      doc.add(hyperText);
      expected += " [" + hyperText.getText() + "](" + hyperText.getUrl() + ")\n";
      assertEquals(expected, doc.toText(visitor));

      doc.add(italicText);
      expected += " *" + italicText.getText() + "*\n";
      assertEquals(expected, doc.toText(visitor));

      doc.add(p);
      expected += " \n" + expected + "\n";
      assertEquals(expected, doc.toText(visitor));
    }
}