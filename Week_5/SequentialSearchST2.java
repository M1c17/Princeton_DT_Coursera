import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class SequentialSearchST2<Key, Value> {
  private int n;
  private Node root;

  private class Node {
    private Key key;
    private Value value;
    private Node next;

    public Node(Key key, Value value, Node next) {
      this.key = key;
      this.value = value;
      this.next = next;
    }
  }

  public void put(Key key, Value value) {
    if(key == null) throw new IllegalArgumentException("call put() with null argument");
    if(value == null) {
      delete(key);
      return;
    }

    for(Node x = root; x != null; x = x.next) {
      if(key.equals(x.key)) {
        x.value = value;
        return;
      }
    }
    root = new Node(key, value, root);
    n++;
  }

  public Value get(Key key) {
    if(key == null) throw new IllegalArgumentException("call get() with null argument");
    for(Node x = root; x != null; x = x.next) {
      if(key.equals(x.key)) {
        return x.value;
      }
    }
    return null;
  }

  public void delete(Key key) {
    if(key == null) throw new IllegalArgumentException("call delete() with null argument");
    root = delete(root, key);
  }

  public Node delete(Node x, Key key) {
    // if nothing to delete
    if(x == null) return null;
    if(key.equals(x.key)) {
      n--;
      return x.next;
    }
    x.next = delete(x.next, key);
    return x;
  }

  public boolean contains(Key key) {
    if(key == null) throw new IllegalArgumentException("call contains() with null argument");
    return get(key) != null;
  }

  public int size() {
    return n;
  }

  public boolean isEmpty() {
    return size() == 0;
  }

  public Iterable<Key> keys() {
    Queue<Key> queue = new Queue<>();
    for(Node x = root; x != null; x = x.next) {
      queue.enqueue(x.key);
    }
    return queue;
  }

  public static void main(String[] args) {
    SequentialSearchST2<String, Integer> st2 = new SequentialSearchST2<String, Integer>();
    for(int i = 0; !StdIn.isEmpty(); i++) {
      String key = StdIn.readString();
      st2.put(key, i);
    }

    // print all the keys
    for(String s: st2.keys()) {
      StdOut.println(s + " " + st2.get(s));
    }
  }
}
