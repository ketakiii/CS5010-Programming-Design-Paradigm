package bst;

/**
 * This class defines the LeafNode interface and its methods.
 * @param <T> comparable type
 */
public class LeafNode<T extends Comparable<T>> implements TreeNode<T> {

  @Override public TreeNode addNode(T data) {
    return new RootNode<T>(data, new LeafNode<T>(), new LeafNode<T>());
  }

  @Override public int size() {
    return 0;
  }

  @Override public int height() {
    return 0;
  }

  @Override public boolean present(T data) {
    return false;
  }

  @Override public T minimum() {
    return null;
  }

  @Override public T maximum() {
    return null;
  }

  @Override public String preOrder() {
    return "";
  }

  @Override public String inOrder() {
    return "";
  }

  @Override public String postOrder() {
    return "";
  }
}
