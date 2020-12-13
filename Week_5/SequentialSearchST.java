import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class SequentialSearchST<Key extends Comparable<Key>, Value> {
  private int n;
  private Node root;

  public class Node {
    private Key key;
    private Value value;
    private Node next;

    public Node(Key key, Value value, Node next) {
      this.key = key;
      this.value = value;
      this.next = next;
    }
  }

  public SequentialSearchST() { }

  public int size() {
    return n;
  }


  /*
   * Return true if this symbol table is empty
   */
  public boolean isEmpty() {
    return size() == 0;
  }


  /*
   *  put key-value pair into the table
   * (remove key from table if value is null)
   * Overwrites old value with new value
   */
  public void put(Key key, Value value) {
    if(key == null) throw new IllegalArgumentException("first argument to put() is null");
    if(value == null) {
      delete(key);
      return;
    }

    // walk the linked list
    for(Node x = root; x != null; x = x.next) {
      // if found key return value
      if(x.key.equals(key)) {
        x.value = value;
        return;
      }
    }

    root = new Node(key, value, root);
    n++;
  }

  public Value get(Key key) {
    if(key == null) throw new IllegalArgumentException("call get() with null key");

    for(Node x = root; x != null; x = x.next) {
      if(key.equals(x.key)) {
        return x.value;
      }
    }
    return null;
  }

  /*
   * Remove the specified key and its associated value from this symbol table
   * (if the key is in this symbol table)
   */
  public void delete(Key key) {
     if(key == null) throw new IllegalArgumentException("call delete() with argument null");
     root = delete(root, key);
  }

  /*
  * delete key-value pair from symbol table
   */
  public Node delete(Node x, Key key) {
    if(x == null) return null;
    if(key.equals(x.key)) {
      n--;
      root = x.next;
      StdOut.println("key " + x.key);
    }
    x.next = delete(x.next, key);
    return x;
  }

  /*
   * returns true if this symbol table contains the specified key
  */
  public boolean contains(Key key) {
    if(key == null) throw new IllegalArgumentException("call contains() with null argument");
    return get(key) != null;
  }

  /*
   * Iterate over all key-value pairs
   */
  public Iterable<Key> keys() {
    Queue<Key> queue = new Queue<>();
    for(Node x = root; x != null; x = x.next) {
      queue.enqueue(x.key);
    }
    return queue;
  }

//  public static void main(String[] args) {
//    SequentialSearchST<String, Integer> st = new SequentialSearchST();
//    System.out.print("Type Here >>> ");
//    for(int i = 0; !StdIn.isEmpty(); i++) {
//      String key = StdIn.readString();
//      st.put(key, i);
//    }
//
//    for(String s: st.keys()) {
//      StdOut.println(s + " " + st.get(s));
//    }
//
//    st.delete("g");
//  }

}
