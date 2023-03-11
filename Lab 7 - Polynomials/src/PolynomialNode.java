package polynomial;

/**
 * This class represents the PolynomialNode interface.
 */
public interface PolynomialNode {

  /**
   * Adds a new term to our list of nodes. The order of the nodes at the end of the add
   * must be from the highest power to the lowest power term.
   * @param coefficient coefficient of this term
   * @param power power of this term
   * @return a pointer to the list of the nodes
   */
  PolynomialNode addTerm(int coefficient, int power);

  /**
   * Evaluate this nodes value and combines with the rest of the nodes value.
   * @param x the value of x to evaluate
   * @return the total value of this node and all following nodes
   */
  double evaluate(double x);

  /**
   * Find if this power exists, and if it does return its coefficients.
   * @param power power to search for
   * @return the coefficient of that power
   */
  int getCoefficient(int power);

  /**
   * Return the highest power in the list of polynomial nodes.
   * @return the highest power
   */
  int getDegree();

  PolynomialNode add(PolynomialNode p);

  PolynomialNode getRest();
}
