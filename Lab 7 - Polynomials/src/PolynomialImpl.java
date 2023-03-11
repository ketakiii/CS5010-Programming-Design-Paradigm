package polynomial;

import java.util.Scanner;

/**
 * This class defines the PolynomialImpl and the methods associated with it.
 */
public class PolynomialImpl implements Polynomial {
  private PolynomialNode poly;

  /**
   * Create a PolynomialImpl with no terms.
   */
  public PolynomialImpl() {
    poly = new EmptyNode();
  }

  /**
   * Create a PolynomialImpl with PolynomialNode as an input.
   * @param poly PolynomialNode
   */
  public PolynomialImpl(PolynomialNode poly) {
    this.poly = poly;
  }

  /**
   * Create a PolynomialImpl from a string in such a way that all the non-zero term from the string
   * are represented in this polynomial.
   * @param equation equation
   */
  public PolynomialImpl(String equation) {
    String acceptableChars = "[[0-9]x+-\\^\\s]*";
    Scanner scan = new Scanner(equation);
    if (!equation.matches(acceptableChars)) {
      throw new IllegalArgumentException("String can only contain digits, "
          + "the variable x and the ^" + "symbol and the input is " + equation);
    }
    this.poly = new EmptyNode();
    while (scan.hasNext()) {
      String term = scan.next();
      int index = term.indexOf("x");
      int coeff;
      int power;
      if (index != -1) {
        coeff = Integer.parseInt(term.substring(0, index));
        power = Integer.parseInt(term.substring(index + 2));
      } else {
        coeff = Integer.parseInt(term);
        power = 0;
      }
      if (power < 0) {
        throw new IllegalArgumentException("String contains a power of " + power + ". Power must "
            + "be non negative.");
      }
      this.poly = this.poly.addTerm(coeff, power);
    }
    scan.close();
  }

  @Override public Polynomial add(Polynomial other) throws IllegalArgumentException {
    if (!(other instanceof PolynomialImpl)) {
      throw new IllegalArgumentException("Other is not a PolynomialImpl");
    }
    PolynomialImpl o = (PolynomialImpl) other;
    return new PolynomialImpl(this.poly.add(o.poly));
  }

  @Override public void addTerm(int coefficient, int power) throws IllegalArgumentException {
    if (power < 0) {
      throw new IllegalArgumentException("Power must be non-negative");
    }
    this.poly = this.poly.addTerm(coefficient, power);
  }

  @Override public boolean isSame(Polynomial poly) {
    if (this.toString().equals(poly.toString())) {
      return true;
    } else {
      return false;
    }
  }

  @Override public double evaluate(double x) {
    double e = 0;
    PolynomialNode curr = this.poly;
    if (curr != null) {
      int coeff = curr.getCoefficient(curr.getDegree());
      if (curr.getDegree() == 0) {
        e += coeff;
      } else {
        e += coeff * Math.pow(x, curr.getDegree());
      }
    }
    return this.poly.evaluate(x);
  }

  @Override public int getCoefficient(int power) {
    while (this.poly != null) {
      if (this.poly.getDegree() == power) {
        return this.poly.getCoefficient(power);
      } else {
        return this.poly.getCoefficient(power);
      }
    }
    return 0;
  }

  @Override public int getDegree() {
    return this.poly.getDegree();
  }

  @Override public String toString() {
    if (null == this) {
      return "0";
    }
    if (poly.toString().length() == 0) {
      return "0";
    }
    String pstring = this.poly.toString().strip();
    if (pstring.length() > 0 && pstring.charAt(0) == '+') {
      pstring = pstring.substring(1);
    }
    return pstring;
  }
}
