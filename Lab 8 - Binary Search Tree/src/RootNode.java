package bst;

/**
 * This class defines the class RootNode and its methods.
 * @param <T> comparable type
 */
public class RootNode<T extends Comparable<T>> implements TreeNode<T> {
  private T data;
  private TreeNode<T> left;
  private TreeNode<T> right;

  /**
   * This defines the constructor of the RootNode class.
   * @param data data
   * @param left left node
   * @param right right node
   */
  public RootNode(T data, TreeNode<T> left, TreeNode<T> right) {
    this.data = data;
    this.left = left;
    this.right = right;
  }

  @Override public TreeNode addNode(T data) {
    if (this.data.compareTo(data) > 0) {
      this.left = this.left.addNode(data);
    } else if (this.data.compareTo(data) < 0) {
      this.right = this.right.addNode(data);
    }
    return this;
  }

  @Override public int size() {
    return this.left.size() + this.right.size() + 1;
  }

  @Override public int height() {
    int leftHeight = this.left.height();
    int rightHeight = this.right.height();
    if (leftHeight > rightHeight) {
      return leftHeight + 1;
    }
    return rightHeight + 1;
  }

  @Override public boolean present(T data) {
    return this.data.equals(data) || this.left.present(data) || this.right.present(data);
  }

  @Override public T minimum() {
    T min;
    min = this.left.minimum();
    if (min == null) {
      return this.data;
    }
    return min;
  }

  @Override public T maximum() {
    T max;
    max = this.right.maximum();
    if (max == null) {
      return this.data;
    }
    return max;
  }

  @Override public String preOrder() {
    String result = " " + this.data.toString();
    result += " " + this.left.preOrder();
    result += " " + this.right.preOrder();
    return result;
  }

  @Override public String inOrder() {
    String result = " " + this.left.inOrder();
    result += " " + this.data.toString();
    result += " " + this.right.inOrder();
    return result;
  }

  @Override public String postOrder() {
    String result = " " + this.left.postOrder();
    result += " " + this.right.postOrder();
    result += " " + this.data.toString();
    return result;
  }
}
