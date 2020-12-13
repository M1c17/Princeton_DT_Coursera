public class Red_blackBST<Key extends Comparable<Key>, Value> {

  private static final boolean RED = true;
  private static final boolean BLACK = false;
  private Node root;                  // root of the BST

  public class Node {
    private Key key;
    private Value value;
    private Node left, right;
    private boolean color;            // color of parent link
    private int size;

    public Node(Key key, Value value, boolean color, int size) {
      this.key = key;
      this.value = value;
      this.color = color;
      this.size = size;
    }
  }

  // Constructor
  public Red_blackBST() {}

  public boolean isRed(Node x) {
    if(x == null) return BLACK;
    return x.color == RED;
  }

  public int size() {
    return size(root);
  }

  public int size(Node x) {
    if(x == null) return 0;
    else          return x.size;
  }

  public boolean isEmpty() {
    return root == null;
  }

  public void put(Key key, Value value) {
    if(key == null) throw new IllegalArgumentException("called put() with null argument");
    if(value == null) {
      //delete(key);
      return;
    }
    root = put(root, key, value);
    root.color = BLACK;

  }

  public Node put(Node x, Key key, Value value) {
    if(x == null) return new Node(key, value, RED, 1);
    int cmp = key.compareTo(x.key);
    if(cmp < 0)   x.left = put(x.left, key, value);
    if(cmp > 0)   x.right = put(x.right, key, value);
    else          x.value = value;

    if(isRed(x.right) && !isRed(x.left))              x = rotateLeft(x);
    if(isRed(x.left) && isRed(x.left.left))           x = rotateRight(x);
    if(isRed(x.left) && isRed(x.right))               flipCoLors(x);
    // recount again
    x.size = size(x.left) + size(x.right) + 1;

    return x;
  }

  public boolean contains(Key key) {
    return get(key) != null;
  }


  public Value get(Key key) {
    if(key == null) throw new IllegalArgumentException("called get() with null argument");
    return get(root, key);
  }

  public Value get(Node x, Key key) {
    while(x != null) {
      int cmp = key.compareTo(x.key);
      if(cmp < 0)         x = x.left;
      else if(cmp > 0)    x = x.right;
      else                return x.value;
    }
    return null;
  }

//  public void delete(Key key) {
//    if(key == null) throw new IllegalArgumentException("argument to delete() is null");
//    if(!contains(key)) return;
//
//
//  }
//
//  public void delete(Key key) {
//    if()
//  }
//
//  public Key min() {
//
//  }
//
//  public Key max() {
//
//  }
//
//  public void delMin() {
//
//  }
//
//  public void delMax() {
//
//  }
//
//  public Key floor(Node x, Key key) {
//
//  }
//
//
//  public Node floor(Node x, Key key) {
//
//  }
//
//  public Key ceiling(Key key) {
//
//  }
//
//  public Node ceiling(Node x, Key key) {
//
//  }
//
//  public Key select(int rank) {
//
//  }
//
//  public Key select(Node x, int rank) {
//
//  }
//
//  public Iterable<Key> keys() {
//
//  }
//
//  public int rank(Key key) {
//
//  }
//
//  private int rank(Node root, Key key) {
//
//  }

  /***************************************************************************
   * Red-Black tree helper functions
   ****************************************************************************/

  // make a left-leaning link lean to the right
  public Node rotateRight(Node h) {
    Node x = h.left;
    h.left = x.right;
    x.right = h;
    x.color = x.right.color;
    x.right.color = RED;
    x.size = h.size;
    h.size = size(h.left) + size(h.right) + 1;
    return x;
  }

  // make a right-leaning link lean to the left
  public Node rotateLeft(Node h) {
    Node x = h.right;
    h.right = x.left;
    x.left = h;
    x.color = x.left.color;
    x.left.color = RED;
    x.size = h.size;
    h.size = size(h.left) + size(h.right) + 1;
    return x;
  }

  // flip the colors of a node and it's two children
  public void flipCoLors(Node h) {
    // h must have opposite color of its two children
    h.color = !h.color;
    h.left.color = !h.left.color;
    h.right.color = !h.right.color;
  }
}
