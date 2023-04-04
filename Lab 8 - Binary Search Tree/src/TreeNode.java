package bst;

/**
 * This class defines the TreeNode interface with its methods.
 * @param <T> comparable type
 */
public interface TreeNode<T extends Comparable<T>> {

  TreeNode<T> addNode(T data);

  int size();

  int height();

  boolean present(T data);

  T minimum();

  T maximum();

  String preOrder();

  String inOrder();

  String postOrder();


}
