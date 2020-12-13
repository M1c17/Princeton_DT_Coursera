
/*
* Check if a binary tree is a BST.
* Given a binary tree where each \mathtt{Node}Node contains a key,
* determine whether it is a binary search tree.
*  Use extra space proportional to the height of the tree.
 */

public class Question2<Key extends Comparable<Key>, Value> {

  private Node root;

  public class Node {
    private int key;         // sorted by key
    private int value;      // associated data
    private Node left, right;   //left and right subtree
    private int size;           // number of nodes in subtrees

    public Node(int key, int value, int size) {
      this.key = key;
      this.value = value;
      this.size = size;
    }
  }

  public boolean checkBST(Node p, int min, int max) {
    if (p == null) return true;
    if(p.key >= max || p.key <= min ) return false;
    return checkBST(p.left, min, p.key) && checkBST(p.right, p.key, max);
  }
}
//
//
//  public boolean check() {
//    if (!isBST()) System.out.println("Not in symmetric order");
//    if (!isSizeConsistent()) System.out.println("Subtree counts not consistent");
//    if (!isRankConsistent()) System.out.println("Ranks not consistent");
//    return isBST() && isSizeConsistent() && isRankConsistent();
//  }
//
//  /*
//   * Does this binary tree satisfy symmetric order?
//   * Note: this test also ensures that data structure is binary tree since order is strict
//   */
//  public boolean isBST() {
//    return isBST(root, null, null);
//  }
//
//  public boolean isBST(Node x, Key min, Key max) {
//    // is just the root so the tree is balance
//    if (x == null) return true;
//    // the tree is skewed to the left
//    if ((min != null) && (x.key.compareTo(min) <= 0)) return false;
//    // the tree is skewed to the right
//    if ((max != null) && (x.key.compareTo(max) >= 0)) return false;
//    return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
//  }
//
//  public int size() {
//    return size(root);
//  }
//
//  public int size(Node x){
//    if(x == null) return 0;
//    else    return x.size;
//  }
//
//
//  // Is the size field correct?
//  public boolean isSizeConsistent() {
//    return isSizeConsistent(root);
//  }
//
//  public boolean isSizeConsistent(Node x) {
//    // is just the root
//    if (x == null) return true;
//    if (x.size != size(x.left) + size(x.right) + 1) return false;
//    return isSizeConsistent(x.left) && isSizeConsistent(x.right);
//  }
//
//  // checks that ranks are consistent
//  public boolean isRankConsistent() {
//    // Iterate over the index checking values
//    for (int i = 0; i < size(); i++) {
//      if (i != rank(select(i))) return false;
//    }
//    // Iterate over the keys checking keys
//    for (Key key : keys()) {
//      if (key.compareTo(select(rank(key))) != 0) return false;
//    }
//    return true;
//  }
//
//  public int rank() {
//
//  }
//
//  public Key select() {
//
//  }
//}
