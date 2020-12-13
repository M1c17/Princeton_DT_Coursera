/*
 * Question 3
 * Inorder traversal with constant extra space.
 * Design an algorithm to perform an inorder traversal of a binary search tree using only a constant amount of extra space.
 */

/*
 * In order traversol:
 * traverse the left subtree
 * visit the root
 * traverse the right subtree
 */

import edu.princeton.cs.algs4.Stack;

public class Question3<Key, Value> {

  private Node root;

  public class Node {
    private Key key;
    private Value value;
    private Node left, right;

    public Node(Key key, Value value) {
      this.key = key;
      this.value = value;
    }
  }

  public void inOrder() {
    inOrder(root);
  }

  public void inOrder(Node x) {
    Stack<Node> s = new Stack<>();
    Node current = x;

    while(current != null || !s.isEmpty()) {
      if(current != null) {
        s.push(x);
        // traverse left
        current = current.left;
      } else {
        Node temp = s.pop();
        System.out.println(temp.value);
        // traverse right
        current = current.right;
      }
    }
  }
}
