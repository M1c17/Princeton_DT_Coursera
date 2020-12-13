import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class LinearProbingHashST<Key, Value> {

  private static final int INIT_CAPACITY = 4;
  private int n;                              // number of key-value pairs
  private int m;                              // size of linear probing table
  private Key[] keys;                         // keys
  private Value[] values;                     // values

  /*
  * Initializes the empty symbol table.
   */
  public LinearProbingHashST() {
     this(INIT_CAPACITY);
  }

  public LinearProbingHashST(int initCapacity) {
    m = initCapacity;
    n = 0;
    keys = (Key[]) new Object[m];
    values = (Value[]) new Object[m];
  }

  public int size() {
    return n;
  }

  public boolean isEmpty() {
    return size() == 0;
  }

  public int hashTextbook(Key key) {
    return (key.hashCode() & 0x7fffffff) % m;
  }

  public int hash(Key key) {
    int h = key.hashCode();
    h = (h >>> 20) ^ (h >>> 12) ^ (h >>> 7) ^ (h >>> 4);
    return h & (m-1);
  }

  public void resize(int capacity) {
    LinearProbingHashST<Key, Value> temp = new LinearProbingHashST<>();
    for(int i = 0; i < m; i++) {
      if(keys[i] != null) {
        temp.put(keys[i], values[i]);
      }
    }
    keys = temp.keys;
    values = temp.values;
    m = temp.m;
  }

  public boolean contains(Key key) {
    if(key == null) throw new IllegalArgumentException("called contains with null argument");
    return get(key) != null;
  }

  /*
  * Insert the specified key-value pair into symbol table
  * overwriting the old value if key exists
  * delete key-value pair from symbol table if value == null
   */
  public void put(Key key, Value value) {
    if (key == null) throw new IllegalArgumentException();
    if(value == null) {
      delete(key);
      return;
    }

    // resize symbol table if halve full
    if(n >= m/2) resize(2 * m);

    int i;
    for(i = hash(key); keys[i] != null; i = (i + 1) % m) {
      if(keys[i].equals(key)) {
        // overwrite value
        values[i] = value;
        return;
      }
    }
    keys[i] = key;
    values[i] = value;
    n++;
  }

  /*
  * returns the value associated with the specified key
   */
  public Value get(Key key){
      if(key == null) throw new IllegalArgumentException("called get() with null argument");
      for(int i = hash(key); keys[i] != null; i = (i + 1) % m) {
        if(keys[i].equals(key)) {
          return values[ i ];
        }
      }
      return null;
  }

  /*
  * Removes the specified key-value pair from this symbol table
  * (if the key is in the symbol table)
   */
  public void delete(Key key) {
    if(key == null) throw new IllegalArgumentException("called delete() with null argument");
    if(!contains(key)) return;

    // find position i of key
    int i = hash(key);
    while(!key.equals(keys[i])) {
      // go to the next one in the symbol table
      i = (i + 1) % m;
    }

    // delete key and value associated
    keys[i] = null;
    values[i] = null;

    // rehash all keys in same cluster
    i = (i + 1) % m;
    while(keys[i] != null) {
      // delete keys[i] and values[i] and reinsert
      Key keyToRehash = keys[i];
      Value valToRehash = values[i];
      keys[i] = null;
      values[i] = null;
      n--;
      put(keyToRehash, valToRehash);
      i = (i + 1) % m;
    }
    n--;

    // halves size of array if it's 12.5% full or less
    if(n > 0 && n <= m/8) resize(m/2);

    assert check();
  }

  public Iterable<Key> keys() {
    Queue<Key> queue = new Queue<>();
    for(int i = 0; i < m; i++) {
      if(keys[i] != null) queue.enqueue(keys[i]);
    }
    return queue;
  }

  public boolean check() {

    // check that hash table is at least 50 % full
    if(m < 2 * m) {
      System.err.println("Hash table size m = " + m + "; array size n " + n);
      return false;
    }

    // check that each key in the table can be found by get()
    for(int i = 0; i < m; i++) {
      if(keys[i] == null) continue;
      else if(get(keys[i]) != values[i]) {
        System.err.println("get[" + keys[i] + "] = " + get(keys[i]) + "; values[i] = " + values[i]);
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    LinearProbingHashST<String, Integer> st = new LinearProbingHashST<>();
    for(int i = 0; !StdIn.isEmpty(); i++) {
      String key = StdIn.readString();
      st.put(key, i);
    }

    for(String s: st.keys()) {
      StdOut.println(s + " " + st.get(s));
    }
  }


}
