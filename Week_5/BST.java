import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class BST<Key extends Comparable<Key>, Value> {
  private Node root;            // root of BST

  public class Node {
    private Key key;            // sorted by key
    private Value value;        // associated data
    private Node left;          // left subtree
    private Node right;         // right subtree
    private int size;           // number of nodes in subtree

    public Node(Key key, Value value, int size) {
      this.key = key;
      this.value = value;
      this.size = size;
    }
  }

  public BST() { }


  /*
   * Insert the specified key-value pair in the table:
   * Search for key:
   * - key in tree -> overwrites the old value
   * - key not in tree -> add new node
   * (remove key from table if value is null)
   */
  public void put(Key key, Value val) {
    if (key == null) throw new IllegalArgumentException("calls put() with a null key");
    if (val == null) {
      delete(key);
      return;
    }
    root = put(root, key, val);
    assert check();
  }

  private Node put(Node x, Key key, Value val) {
    if (x == null) return new Node(key, val, 1);
    int cmp = key.compareTo(x.key);
    if      (cmp < 0) x.left  = put(x.left,  key, val);
    else if (cmp > 0) x.right = put(x.right, key, val);
    else              x.value   = val;
    x.size = 1 + size(x.left) + size(x.right);
    return x;
  }



  /*
   * Returns the value associated with the given key
   * (Binary search)
   */
//  public Value get(Key key) {
//    Node x = root;
//    while(x != null) {
//      int cmp = key.compareTo(x.key);
//      if(cmp < 0)       x = x.left;
//      else if(cmp > 0)  x = x.right;
//      else              return x.value;
//    }
//    return null;
//  }



  /*
   * Returns the value associated with the given key
   * (recursive)
   */
  public Value get(Key key) {
    return get(root, key);
  }

  public Value get(Node x, Key key) {
   if(key == null) throw new IllegalArgumentException("called get() with null arguemnt");
   if(x == null) return null;
   int cmp = key.compareTo(x.key);
   if(cmp < 0)            return get(x.left, key);
   else if(cmp > 0)       return get(x.right, key);
   else                   return x.value;
  }

  public void delete(Key key) {
    if(key == null) throw new IllegalArgumentException("called delete with null argument");
    root = delete(root, key);
    assert check();
  }

  public Node delete(Node x, Key key) {
    if(x == null) return null;

    int cmp = key.compareTo(x.key);
    if(cmp < 0)           x.left = delete(x.left, key);
    else if(cmp > 0)      x.right = delete(x.right, key);
    else {
      if(x.left == null) return x.right;
      if(x.right == null) return x.left;

      // this is a temp Node like swap technique
      Node t = x;
      x = min(t.right);
      x.right = delMin(t.right);
      x.left = t.left;
    }
    // count tree
    x.size = size(x.left) + size(x.right) + 1;
    return x;
   }

  public boolean contains(Key key) {
    if(key == null) throw new IllegalArgumentException("called contains() with null argument");
    return get(key) != null;
  }

  public int size() {
    return size(root);
  }

  public int size(Node x) {
    if(x == null) return 0;
    else          return x.size;
  }

  public boolean isEmpty() {
    return size() == 0;
  }

  public Iterable<Key> keys() {
    if(isEmpty()) return new Queue<Key>();
    return keys(min(), max());
  }

  public Iterable<Key> keys(Key lo, Key hi) {
    if(lo == null) throw new IllegalArgumentException("first argument to keys() is null");
    if(hi == null) throw new IllegalArgumentException("second argument to keys() is null");
    Queue<Key> queue = new Queue<>();
    keys(root, queue, lo, hi);
    return queue;
  }

  public void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
    // if root null
    if(x == null) return;
    int cmplo = lo.compareTo(x.key);
    int cmphi = hi.compareTo(x.key);
    // go over the left
    if(cmplo < 0) keys(x.left, queue, lo, hi);
    // enqueue the root from left and right
    if(cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key);
    // go over the right
    if(cmphi > 0) keys(x.right, queue, lo, hi);
  }

  public Key floor(Key key) {
    if(key == null) throw new IllegalArgumentException("called floor() with null argument");
    if(isEmpty()) throw new IllegalArgumentException("called floor() with empty symbol table");
    Node x = floor(root, key);
    if(x == null) throw new IllegalArgumentException("argument to floor() is too small");
    return x.key;
  }

  public Node floor(Node x, Key key) {
    if(x == null) return null;
    int cmp = key.compareTo(x.key);
    if(cmp == 0)          return x;
    if(cmp < 0)           return floor(x.left, key);
    Node t = floor(x.right, key);
    if(t != null)         return t;
    else                  return x;
  }

  public Key ceiling(Key key) {
    if(key == null)   throw new IllegalArgumentException("called ceiling() with null argument");
    if(isEmpty())     throw  new IllegalArgumentException("called ceiling with empty symbol table");
    Node x = ceiling(root, key);
    if(x == null) throw new IllegalArgumentException("argument to floor is too large");
    else              return x.key;
  }

  public Node ceiling(Node x, Key key) {
    if(x == null) return null;
    int cmp = key.compareTo(x.key);
    if(cmp == 0)           return x;
    if(cmp > 0)            return ceiling(x.right, key);
    Node t = ceiling(x.left, key);
    if(t != null)          return t;
    else                   return x;
  }

  public void delMin() {
    if(isEmpty()) throw new IllegalArgumentException("Empty symbol table");
    root = delMin(root);
    assert check();
  }

  public Node delMin(Node x) {
    // no child in left side
    if(x.left == null) return x.right;
    // keep looking
    x.left = delMin(x.left);
    x.size = size(x.left) + size(x.right) + 1;
    return x;
  }

  public void delMax() {
    if(isEmpty()) throw new IllegalArgumentException("called delMax with empty symbol table");
    root = delMax(root);
    assert check();
  }

  public Node delMax(Node x) {
    if(x.right == null) return x.left;
    x.right = delMax(x.right);
    x.size = size(x.left) + size(x.right) + 1;
    return x;
  }

  public Key min() {
    if(isEmpty()) throw new IllegalArgumentException("called min() with empty symbol table");
    return min(root).key;
  }

  public Node min(Node x){
    if(x.left == null) return x;
    else               return min(x.left);
  }

  public Key max() {
    if(isEmpty()) throw new IllegalArgumentException("called max with empty symbol table");
    return max(root).key;
  }

  public Node max(Node x){
    if(x.right == null) return x;
    else                return max(x.right);
  }

  /*
  * return the number of keys in the symbol table strictly less than key
   */
  public int rank(Key key) {
    if(key == null) throw new IllegalArgumentException("called rank() with null argument");
    return rank(root, key);
  }

  public int rank(Node x, Key key) {
    if(x == null) return 0;
    int cmp = key.compareTo(x.key);
    if(cmp < 0)         return rank(x.left, key);
    else if(cmp > 0)    return size(x.left) + rank(x.right, key) + 1;
    else                return size(x.left);
  }

  /*
  * return the key in the symbol table of a given rank
  * This key has the property that there are keys in
  * the symbol table that are smaller.
  * In other words, this is the rank + 1 st smallest key in the symbol table.
   */
  public Key select(int rank) {
    if(rank < 0 || rank > 0) {
      throw new IllegalArgumentException("argument out of bounds");
    }
    return select(root, rank);

  }

  public Key select(Node x, int rank) {
    if(x == null) return null;
    int leftSize = size(x.left);
    if(leftSize > rank)            return select(x.left, rank);
    else if(leftSize < rank)       return select(x.right, rank - leftSize -1);
    else                           return x.key;                     // found key
  }

  /*
  * return the number of keys in the symbol table in the given range
  *
   */
  public int size(Key lo, Key hi) {
    if(lo == null) throw new IllegalArgumentException("first argument given is null");
    if(hi == null) throw new IllegalArgumentException("second argument given is null");
    if(lo.compareTo(hi) > 0) return 0;
    if(contains(hi))         return rank(hi) - rank(lo) + 1;
    else                     return rank(hi) - rank(lo);
  }

  public int height() {
    return height(root);
  }

  public int height(Node x) {
    if(x == null) return -1;
    return 1 + Math.max(height(x.left), height(x.right));
  }

  /*
  * return the keys in the BST in level order (for debugging)
   */
  public Iterable<Key> levelOrder() {
    Queue<Key> keys = new Queue<>();
    Queue<Node> queue = new Queue<>();
    queue.enqueue(root);
    while(!queue.isEmpty()) {
      Node x = queue.dequeue();
      if(x == null) continue;
      keys.enqueue(x.key);
      queue.enqueue(x.left);
      queue.enqueue(x.right);
    }
    return keys;
  }
  /************************************************************************
   * Check integrity of BST data structure
   *************************************************************************/
  public boolean check() {
    if(!isBST())                        StdOut.println("Not in symmetric order");
    if(!isSizeConsistent())             StdOut.println("Subtree counts not consistent");
    if(!isRankConsistent())             StdOut.println("Ranks not consistent");
    return isBST() && isSizeConsistent() && isRankConsistent();
  }

  /*
  * Does this binary tree satisfy symmetric order?
  * Note: this test also ensures that data structure is binary tree since order is strict
   */
  public boolean isBST() {
    return isBST(root, null, null);
  }

  public boolean isBST(Node x, Key min, Key max) {
    // is just the root so the tree is balance
    if(x == null) return true;
    // the tree is skewed to the left
    if((min != null) && (x.key.compareTo(min) <= 0)) return false;
    // the tree is skewed to the right
    if((max != null) && (x.key.compareTo(max) >= 0)) return false;
    return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
  }

  // Is the size field correct?
  public boolean isSizeConsistent() {
    return isSizeConsistent(root);
  }

  public boolean isSizeConsistent(Node x) {
    // is just the root
    if(x == null)                                     return true;
    if(x.size != size(x.left) + size(x.right) + 1)    return false;
    return isSizeConsistent(x.left) && isSizeConsistent(x.right);
  }

  // checks that ranks are consistent
  public boolean isRankConsistent() {
    // Iterate over the index checking values
    for(int i = 0; i < size(); i++) {
      if(i != rank(select(i)))    return false;
    }
    // Iterate over the keys checking keys
    for(Key key : keys()) {
      if(key.compareTo(select(rank(key))) != 0) return false;
    }
    return true;
  }

  public static void main(String[] args) {
    BST<String, Integer> bst = new BST<>();
    StdOut.println("Enter the keys: ");
    for(int i = 0; !StdIn.isEmpty(); i++) {
      String key = StdIn.readString();
      bst.put(key, i);
    }

    for(String s: bst.levelOrder()) {
      StdOut.println(s + " " + bst.get(s));
    }
  }
}

