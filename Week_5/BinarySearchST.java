import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


public class BinarySearchST<Key extends Comparable<Key>, Value> {
  private static final int INIT_CAPACITY = 2;
  private Key[] keys;
  private Value[] values;
  private int n = 0;

  /**
   * Initializes an empty symbol table.
   */
  public BinarySearchST() {
    this(INIT_CAPACITY);
  }

  /**
   * Initializes an empty symbol table with the specified initial capacity.
   * @param capacity the maximum capacity
   */
  public BinarySearchST(int capacity) {
    keys = (Key[]) new Comparable[capacity];
    values = (Value[]) new Object[capacity];
  }

  /*
  * put key-value pair into the table
  * (remove key from table if value is null)
   */
  public void put(Key key, Value value) {
    if(key == null) throw new IllegalArgumentException("call put with null argument");
    if(value == null) {
      delete(key);
      return;
    }

    // key is already in table overwrite value
    int i = rank(key);
    if(i < n && keys[i].compareTo(key) == 0) {
      values[i] = value;
      return;
    }

    // resize if need it
    if(n == keys.length) resize(2 * keys.length);
    // insert new key
    for(int j = n; j > i; j--) {
      keys[j] = keys[j-1];
      values[j] = values[j-1];
    }
    // references to the new key-value pair
    keys[i] = key;
    values[i] = value;
    n++;

    assert check();
  }

  /*
  * Get value pair of key
  * (null if key is absent)
  * what is a search key?
   */
  public Value get(Key key) {
    if(key == null) throw new IllegalArgumentException("call get() with null argument");
    // empty symbol table
    if(isEmpty()) return null;
    int i = rank(key);
    if(i < n && keys[i].compareTo(key) == 0)    return values[i];
    else                                        return null;
  }

  /*
  * remove key (and its value) from table
  */
  public void delete(Key key) {
    if(key == null) throw new IllegalArgumentException("call delete() with null argument");
    int i = rank(key);
    // key not in symbol table
    if(i == n || keys[i].compareTo(key) != 0) {
      return;
    }

    // walk find the key and delete it
    for(int j = i; j < n-1; j++) {
      keys[j] = keys[j+1];
      values[j] = values[j+1];
    }
    n--;
    // to avoid loitering and help with garbage collection
    keys[n] = null;
    values[n] = null;

    // resize if 1/4 full
    if(n > 0 && n == keys.length/4) resize(keys.length/2);
    assert check();
  }

  /*
  * Returns the number of keys in this symbol table strictly less than {@code key}.
  * (Binary search)
   */
  public int rank(Key key) {
    int lo = 0, hi = n - 1;
    while( lo < hi) {
      int mid = lo + (hi - lo) / 2;
      int cmp = keys[mid].compareTo(key);
      if(cmp < 0)       hi = mid - 1;
      else if(cmp > 0)  lo = mid + 1;
      else              return mid;
    }
    return lo;
  }

  public void resize(int capacity) {
    assert capacity >= n;
    Key[] keys_copy = (Key[]) new Comparable[capacity];
    Value[] values_copy = (Value[]) new Object[capacity];

    for(int i = 0; i < n; i++) {
      keys_copy[i] = keys[i];
      values_copy[i] = values[i];
    }

    keys = keys_copy;
    values = values_copy;
  }

  /*
  * Is there a key-value pair
   */
  public boolean contains(Key key) {
    if(key == null) throw new IllegalArgumentException("call contains() with null argument");
    return get(key) != null;
  }


  /*
  * return the size of key-value pair in table
   */
  public int size() {
    return n;
  }

  /*
  * Is the table empty?
   */
  public boolean isEmpty() {
    return size() == 0;
  }

  /*
   * delete smallest key
   */
  public void deleteMin() {
    if(isEmpty()) throw new IllegalArgumentException("called deleteMin() with empty symbol table");
    delete(min());
  }

  /*
   * delete largest key
   */
  public void deleteMax() {
    if(isEmpty()) throw new IllegalArgumentException("called deleteMax() with empty symbol table");
    delete(max());
  }



  /*******************************************************************************
   * Ordered symbol table methods
  ********************************************************************************/
  /*
  * return min key in symbol table
   */
  public Key min() {
    if(isEmpty()) throw new IllegalArgumentException("called min() with empty symbol table");
    return keys[0];
  }

  /*
  * return max key in symbol table
   */
  public Key max() {
    if(isEmpty()) throw new IllegalArgumentException("called max() with empty symbol table");
    return keys[n-1];
  }

  /*
  * largest key less than or equal to key
   */
  public Key floor(Key key) {
    if(key == null) throw new IllegalArgumentException("called floor() with null argument");
    int i = rank(key);
    if(i < n && key.compareTo(keys[i]) == 0) return keys[i];
    if(i == 0) return null;
    else       return keys[i-1];
  }

  /*
  * smallest key greater or equal than key
   */
  public Key ceiling(Key key) {
    if(key == null) throw new IllegalArgumentException("called ceiling() with null argument");
    int i = rank(key);
    // the key is the greatest key in the symbol table
    if(i == n) return null;
    else       return keys[i];
  }

  /*
  * return the kth smallest key in this symbol table
   */
  public Key select(int k) {
    if(k < 0 || k >= size()) {
      throw new IllegalArgumentException("kth index out of bounds: " + k);
    }
    return keys[k];
  }

  /*
   * iterate over all keys in the symbol table
   */
  public Iterable<Key> keys() {
    return keys(min(), max());
  }

  /*
   * Returns all keys in this symbol table int he given range
   */
  public Iterable<Key> keys(Key lo, Key hi){
    if(lo == null) throw new IllegalArgumentException("first argument is null");
    if(hi == null) throw new IllegalArgumentException("second argument is null");

    Queue<Key> queue = new Queue<>();
    for(int i = rank(lo); i < rank(hi); i++) {
      queue.enqueue(keys[i]);
    }
    if(contains(hi)) queue.enqueue(keys[rank(hi)]);
    return queue;
  }

  /*
  * return the number of keys in this symbol table in the specified range
   */
  public int size(Key lo, Key hi) {
    if(lo == null) throw new IllegalArgumentException("first argument to size() is null");
    if(hi == null) throw new IllegalArgumentException("second argument to size() is null");

    Queue<Key> queue = new Queue<>();
    if(lo.compareTo(hi) > 0) return 0;
    if(contains(hi)) return rank(hi) - rank(lo) + 1;
    else             return rank(hi) - rank(lo);
  }

  /*******************************************************************************
   * Ordered symbol table methods
   ********************************************************************************/

  /***************************************************************************
   *  Check internal invariants.
   ***************************************************************************/
  private boolean check() {
    return isSorted() && rankCheck();
  }

  private boolean isSorted() {
    for(int i = 1; i < size(); i++) {
      if(keys[i].compareTo(keys[i-1]) < 0) return false;
    }
    return true;
  }

  private boolean rankCheck() {
    for(int i = 0; i < size(); i++) {
      if(i != rank(select(i))) return false;
    }

    for(int i = 0; i < size(); i++) {
      if(keys[i].compareTo(select(rank(keys[i]))) != 0) return false;
    }
    return true;
  }

  public static void main(String[] args) {
    BinarySearchST<String, Integer> st = new BinarySearchST<String, Integer>();
    for (int i = 0; !StdIn.isEmpty(); i++) {
      String key = StdIn.readString();
      st.put(key, i);
    }

    for (String s : st.keys())
      StdOut.println(s + " " + st.get(s));

    StdOut.println("Min: " + st.min());
    StdOut.println("Max: " + st.max());

    st.delete("b");

    for (String s : st.keys())
      StdOut.println(s + " " + st.get(s));

    StdOut.println("rank: " + st.rank("d"));
    StdOut.println("rank: " + st.rank("c"));

    StdOut.println("key: " + st.select(3));
    StdOut.println("key: " + st.select(4));

    StdOut.println("floor: " + st.floor("d"));
    StdOut.println("ceiling: " + st.ceiling("c"));

    st.deleteMin();
    st.deleteMax();

    for (String s : st.keys())
      StdOut.println(s + " " + st.get(s));
  }

}
