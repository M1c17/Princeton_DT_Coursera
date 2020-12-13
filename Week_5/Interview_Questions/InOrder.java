
/*
* Question 3
* Inorder traversal with constant extra space.
* Design an algorithm to perform an inorder traversal of a binary search tree using only a constant amount of extra space.
*/

/*
* in order traversal is a depth-first algorithm
* In order traversol:
* traverse the left subtree
* visit the root
* traverse the right subtree
 */

public class InOrder<Key, Value> {
  private Node root;

  public class Node {
    private Key key;
    private Value value;
    private Node left, right;
    private int size;

    public Node(Key key, Value value, int size) {
      this.key = key;
      this.value = value;
      this.size = size;
    }

    public void inorder(Node root) {
      if (root == null) return;

      Node previous;
      Node current = root;
      while (current != null) {
        // current has no left child, print current, then go right
        if (current.left == null) {
          System.out.println(current.value);
          // traverse right
          current = current.right;
        }
        else {
          previous = current.left;

          // go down to current left children's rightmost child
          // if right is not null && is not equal to root
          while (previous.right != null && previous.right != current) {
            // traverse rightmost child of left subtree
            previous = previous.right;
          }

          // if the rightmost child hasn't being linked to current, then link it, and traverse to current left
          if (previous.right == null) {
            // link it to current
            previous.right = current;
            // traverse current left
            current = current.left;
          }
          // if the rightmost child already linked to current (current left children being traversed),
          // then print current and cut the link to restore tree structure
          else {
            previous.right = null;
            System.out.println(current.value);
            current = current.right;
          }
        }
      }
    }

    public void inOrderT() {
      inOrderT(root);
    }
    public void inOrderT(Node x) {
      // base case
      if(x == null) return;

      // traverse left
      inOrderT(x.left);
      // visit root
      System.out.println(x.value);
      // traverse right
      inOrderT(x.right);
    }

  }


}
