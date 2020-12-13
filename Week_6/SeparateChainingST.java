import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.SequentialSearchST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class SeparateChainingST<Key, Value> {
  private static final int INIT_CAPACITY = 4;

  private SequentialSearchST<Key, Value>[]  st;                       // array of linked-list symbol table
  private int n;                                                      // number of key-value pairs
  private int m;                                                      // size of hash-table

  public SeparateChainingST() {
    this(INIT_CAPACITY);
  }

  /*
  * Initializes a empty symbol table with chains
   */
  public SeparateChainingST(int initCapacity) {
    m = initCapacity;
    st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[initCapacity];
    for(int i = 0; i < m; i++) {
      st[i] = new SequentialSearchST<Key, Value>();
    }
  }

  private void resize(int capacity) {
    assert capacity >= m;
    SeparateChainingST<Key, Value> temp = new SeparateChainingST<>(capacity);
    for (int i = 0; i < m; i++) {
      for (Key key : st[i].keys()) {
        temp.put(key, st[i].get(key));
      }
    }
    this.m  = temp.m;
    this.n  = temp.n;
    this.st = temp.st;
   }

  /*
  * hash function for keys - returns value between 0 and m-1
  * (assume m is a power of 2)
  * (from Java 7 implementation, protects against poor quality hashCode() implementations)
   */
  private int hash(Key key) {
    int h = key.hashCode();
    h ^= (h >>> 20) ^ (h >>> 12) ^ (h >>> 7) ^ (h >>> 4);
    return h & (m-1);
  }

  /*
   * Given a key return index array of key
   * hash function keys - returns value between 0 and m-1
   * call hashcode and transform into positive then % M
   */
  private int hashTextbook(Key key) {
    return (key.hashCode() & 0x7fffffff) % m;
  }

  public void put(Key key, Value value) {
    if(key == null) throw new IllegalArgumentException("called put() with null argument");
    if(value == null) {
      delete(key);
      return;
    }
    // resize table size if n >= 10*m
    if(n >= 10*m) resize(2 * m);
    int i = hash(key);
    if(!st[i].contains(key)) n++;
    st[i].put(key,value);
  }

  public Value get(Key key) {
    if(key == null) throw new IllegalArgumentException("argument given to get() is null");
    int i  = hash(key);
    return st[i].get(key);
  }

  public void delete(Key key) {
    if(key == null) throw new IllegalArgumentException("called delete() with null argument");
    int i = hash(key);
    if(st[i].contains(key)) n--;
    st[i].delete(key);

    // halve table size if average length of list <= 2
    if(m > INIT_CAPACITY && n <= 2) resize(m/2);
  }

  public boolean contains(Key key) {
    if(key == null) throw new IllegalArgumentException("argument to contains() is null");
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
    for(int i = 0; i < m; i++) {
      for(Key key: st[i].keys()) {
        queue.enqueue(key);
      }
    }
    return queue;
  }

  public static void main(String[] args) {
    SeparateChainingST<String, Integer> st = new SeparateChainingST<>();
    StdOut.print("Type input here >>> ");
    for(int i = 0; !StdIn.isEmpty(); i++) {
      String key = StdIn.readString();
      st.put(key, i);
    }

    for(String s: st.keys()) {
      StdOut.println(s + " " + st.get(s));
    }
  }


}
