package polynomial;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Random;
import org.junit.jupiter.api.Test;

class PolynomialNodeTest {

  @Test public void testAddTerm() {
    PolynomialNode p = new EmptyNode();
    p = p.addTerm(5, 3);
    assertEquals(" +5x^3", p.toString());

    p = new EmptyNode();
    p = p.addTerm(3, 0);
    assertEquals(" +3", p.toString());

    p = p.addTerm(2, 2);
    assertEquals(" +2x^2 +3", p.toString());

    p = p.addTerm(5, 1);
    assertEquals(" +2x^2 +5x^1 +3", p.toString());

    p = p.addTerm(3, 1);
    assertEquals(" +2x^2 +8x^1 +3", p.toString());

    p = p.addTerm(-3, 0);
    //assertEquals();

  }

  @Test public void testEvaluate() {
    PolynomialNode p = new EmptyNode();
    Random r = new Random(2251);
    for (int i = 0; i < 1000; i++) {
      assertEquals(0, p.evaluate(r.nextDouble() * 10000 - 5000), 0.0);
    }
    for (int test = 0; test < 1000; test++) {
      p = p.addTerm(1, r.nextInt(1000));
      assertEquals(test + 1, p.evaluate(1.0), 0.0);
    }
    double x = 0;
    int c;
    int pow;
    double total;
    for (int test = 0; test < 50; test++) {
      p = new EmptyNode();
      x = r.nextDouble() * 1000 - 500;
      total = 0;
      for (int coeff = 0; coeff < 50; coeff++) {
        c = r.nextInt(1000) * (int) Math.signum(r.nextDouble() - 0.5);
        pow = r.nextInt(50);
        total += c * Math.pow(x, pow);
        p = p.addTerm(c, pow);
        assertTrue(total - p.evaluate(x) < Math.abs(total * 0.01));
        assertEquals("Evaluating f(" + x + ") = " + p.toString(), total,
            p.evaluate(x), Math.abs(total * 0.00001));
      }
    }
  }

  @Test public void testGetDegree() {
    PolynomialNode p = new EmptyNode();
    assertEquals(0, p.getDegree());

    p = p.addTerm(5000, 3);
    assertEquals(3, p.getDegree());

    p.addTerm(100, 1);
    assertEquals(3, p.getDegree());

    p = p.addTerm(10, 5);
    assertEquals(5, p.getDegree());

    Random r = new Random(1138);
    for (int test = 0; test < 1000; test++) {
      p = new EmptyNode();
      int[] powers = new int[300];
      int expected = 0;
      for (int power = 0; power < powers.length; power++) {
        powers[power] = r.nextInt(9000);
        p = p.addTerm(r.nextInt(7000), powers[power]);
        if (powers[power] > expected) {
          expected = powers[power];
        }
      }
      assertEquals(expected, p.getDegree());
    }
  }

  @Test public void testGetCoefficient() {
    PolynomialNode p = new EmptyNode();
    assertEquals(0, p.getCoefficient(0));

    p = p.addTerm(90, 2);
    assertEquals(90, p.getCoefficient(2));

    p = p.addTerm(40, 3);
    assertEquals(90, p.getCoefficient(2));
    assertEquals(40, p.getCoefficient(3));

    p = p.addTerm(50, 4);
    assertEquals(50, p.getCoefficient(4));

    p = p.addTerm(100, 4);
    assertEquals(150, p.getCoefficient(4));
  }

  @Test public void testToString() {
    PolynomialNode p = new EmptyNode();
    assertEquals("", p.toString());
    p = p.addTerm(90, 2);
    assertEquals(" +90x^2", p.toString());
  }



}