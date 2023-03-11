package polynomial;

/**
 * This class defines TermNode and the methods associated with it.
 */
public class TermNode implements PolynomialNode {

  private int coefficient;
  private int power;
  private PolynomialNode rest;

  /**
   * Constructor of the TermNode class.
   * @param coefficient coefficient of the term
   * @param power power of the term
   * @param rest rest of the equation/terms
   */
  public TermNode(int coefficient, int power, PolynomialNode rest) {
    this.coefficient = coefficient;
    this.power = power;
    this.rest = rest;
  }

  @Override public PolynomialNode addTerm(int coefficient, int power) {
    if (this.power > power) {
      this.rest = this.rest.addTerm(coefficient, power);
      return this;
    }
    if (this.power == power) {
      this.coefficient += coefficient;
      if (this.coefficient == 0) {
        return this.rest;
      }
      return this;
    }
    return new TermNode(coefficient, power, this);
  }

  @Override public PolynomialNode add(PolynomialNode p) {
    if (null == p) {
      return this;
    }
    PolynomialNode p1 = this.addTerm(p.getCoefficient(p.getDegree()), p.getDegree());
    return p1.add(p.getRest());
  }

  @Override public PolynomialNode getRest() {
    return this.rest;
  }

  @Override public double evaluate(double x) {
    return this.coefficient * Math.pow(x, this.power) + this.rest.evaluate(x);
  }

  @Override public int getCoefficient(int power) {
    if (this.power == power) {
      return this.coefficient;
    }
    return this.rest.getCoefficient(power);
  }

  @Override public int getDegree() {
    return this.power;
  }

  /**
   * This method shows how to represent the TermNode in a string format.
   * @return string
   */
  @Override public String toString() {
    String thisTerm = "";
    if (this.coefficient > 0) {
      thisTerm = " +" + this.coefficient + "";
    } else if (this.coefficient < 0) {
      thisTerm = " " + this.coefficient + "";
    } else {
      thisTerm = "";
    }
    if (this.power != 0 && this.coefficient != 0) {
      thisTerm += "x^" + this.power;
    }
    return thisTerm + this.rest.toString();
  }
}
