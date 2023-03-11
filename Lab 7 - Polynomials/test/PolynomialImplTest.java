package polynomial;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.fail;

import java.util.Random;
import org.junit.jupiter.api.Test;

class PolynomialImplTest {

  @Test public void testConstructor() {
    Polynomial p = new PolynomialImpl();
    assertEquals("", p.toString());
  }

  @Test public void testAddTermNegativePower() {
    Polynomial p;
    Random r = new Random(9876);
    for (int i = 0; i < 5000; i++) {
      p = new PolynomialImpl();
      try {
        p.addTerm(r.nextInt(), (r.nextInt(10000) + 1) * -1);
        fail("Negative Powers as a parameter to add term");
      } catch (IllegalArgumentException e) {
        System.out.println("fail");
      }
    }
  }

  @Test public void testAddTerm() {
    Polynomial p = new PolynomialImpl();
    p.addTerm(3, 4);
    assertEquals("3x^4", p.toString());
    p.addTerm(-1, 4);
    assertEquals("2x^4", p.toString());
    p.addTerm(-1, 9);
    assertEquals("-1x^9 +2x^4", p.toString());
    p.addTerm(7, 3);
    assertEquals("-1x^9 +2x^4 +7x^3", p.toString());
    assertThrows(IllegalArgumentException.class, () -> p.addTerm(3, -1));
  }

  @Test public void testStringConstructor() {
    Polynomial p = new PolynomialImpl("+15x^6 +2x^4 +7x^3");
    assertEquals("15x^6 +2x^4 +7x^3", p.toString());

    p = new PolynomialImpl("+2x^4 +15x^6 +7x^3");
    assertEquals("15x^6 +2x^4 +7x^3", p.toString());

    p = new PolynomialImpl("");
    assertEquals("", p.toString());

    p = new PolynomialImpl("-3x^2 +6x^4 +3x^2");
    assertEquals("6x^4", p.toString());

    p = new PolynomialImpl("2");
    assertEquals("2", p.toString());
  }

  @Test public void testAdd() {
    String s1 = "2x^1";
    String s2 = "5x^2";
    String s3 = "10x^1";
    Polynomial p1 = new PolynomialImpl(s1);
    Polynomial p2 = new PolynomialImpl(s2);
    Polynomial p3 = new PolynomialImpl(s3);
    assertEquals("5x^2 +2x^1", p1.add(p2).toString());
    assertEquals("12x^1", p1.add(p3).toString());
  }

  @Test public void testGetDegree() {
    Polynomial p = new PolynomialImpl("5x^3");
    assertEquals(3, p.getDegree());
    p = new PolynomialImpl("5x^6 +2x^2 +6x^1 +53");
    assertEquals(6, p.getDegree());
    p = new PolynomialImpl("60");
    assertEquals(0, p.getDegree());
  }

  @Test public void testIsSame() {
    Polynomial p1 = new PolynomialImpl("+5x^2 +0x^4");
    Polynomial p2 = new PolynomialImpl("5x^1");
    Polynomial p3 = new PolynomialImpl("+5x^2 +0x^4");
    Polynomial p4 = new PolynomialImpl("+5x^2 +0x^4 +9x^6");
    assertEquals(true, p1.isSame(p3));
    assertEquals(false, p1.isSame(p4));
    assertEquals(false, p1.isSame(p2));
  }

  @Test public void testGetCoefficient() {
    Polynomial p1 = new PolynomialImpl("5x^6 +2x^2 +6x^1 +53");
    assertEquals(5, p1.getCoefficient(6));
    assertEquals(2, p1.getCoefficient(2));
    assertEquals(6, p1.getCoefficient(1));
    assertEquals(53, p1.getCoefficient(0));
    assertEquals(0, p1.getCoefficient(9));
  }

  @Test public void testEvaluate() {
    Polynomial poly = new PolynomialImpl();
    poly.addTerm(5, 2);
    poly.addTerm(4, 1);
    poly.addTerm(-2, 0);
    assertEquals("5x^2 +4x^1 -2", poly.toString());
    assertEquals(26, poly.evaluate(2), 0.01);
    assertEquals(10, poly.evaluate(-2), 0.01);
  }

  @Test public void testToString() {
    Polynomial p1 = new PolynomialImpl("5x^6 +2x^2 +6x^1 +53");
    assertEquals("5x^6 +2x^2 +6x^1 +53", p1.toString());
    Polynomial p2 = new PolynomialImpl("5x^1");
    assertEquals("5x^1", p2.toString());
    Polynomial p3 = new PolynomialImpl();
    assertEquals("0", p3.toString());
    assertThrows(IllegalArgumentException.class, () ->  new PolynomialImpl("abc").toString());
  }
}