package bst;

/**
 * This class defines the Binary Search Tree class and its methods.
 * @param <T> Comparable<T></T>
 */
public class BinarySearchTreeImpl<T extends Comparable<T>> implements BinarySearchTree<T> {

  private TreeNode<T> root;

  /**
   * This is the constructor of the BinarySearchTreeImpl class.
   */
  public BinarySearchTreeImpl() {
    this.root = new LeafNode<T>();
  }

  @Override public void add(T data) {
    if (data == null) {
      return;
    }
    this.root = this.root.addNode(data);
  }

  @Override public int size() {
    return this.root.size();
  }

  @Override public int height() {
    return this.root.height();
  }

  @Override public boolean present(T data) {
    return this.root.present(data);
  }

  @Override public T minimum() {
    return this.root.minimum();
  }

  @Override public T maximum() {
    return this.root.maximum();
  }

  @Override public String preOrder() {
    String result = this.root.preOrder();
    result = result.strip();
    result = result.replaceAll(" [ ]*", " ");
    return "[" + result + "]";
  }

  @Override public String inOrder() {
    String result = this.root.inOrder();
    result = result.strip();
    result = result.replaceAll(" [ ]*", " ");
    return "[" + result + "]";
  }

  @Override public String postOrder() {
    String result = this.root.postOrder();
    result = result.strip();
    result = result.replaceAll(" [ ]*", " ");
    return "[" + result + "]";
  }

  @Override public String toString() {
    return this.inOrder();
  }
}
