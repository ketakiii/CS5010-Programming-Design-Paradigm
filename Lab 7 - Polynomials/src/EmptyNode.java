package polynomial;

/**
 * This is a class that defines an Empty Node and the methods associated with it.
 */
public class EmptyNode implements PolynomialNode {

  @Override public PolynomialNode addTerm(int coefficient, int power) {
    return new TermNode(coefficient, power, this);
  }

  @Override public double evaluate(double x) {
    return 0;
  }

  @Override public int getCoefficient(int power) {
    return 0;
  }

  @Override public PolynomialNode add(PolynomialNode p) {
    return p;
  }

  @Override public int getDegree() {
    return 0;
  }

  @Override public String toString() {
    return "";
  }

  @Override public PolynomialNode getRest() {
    return null;
  }
}
