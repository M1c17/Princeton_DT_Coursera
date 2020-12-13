import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import javax.naming.InitialContext;


/*
* Value type: Any generic type
*
* Key type: several type natural assumptions:
* - Assume keys are Comparable, use compareTo()
* - Assume keys are generic type, use equals() to test equality
* - use hashcode() to scramble keys.
*
*
 */
public class ArraySt<Key, Value> {

  private static final int INIT_SIZE = 8;

  private Value[] values;                     // Symbol table value
  private Key[] keys;                         // Symbol table keys
  private int n = 0;                          // number of elements in symbol table

  public ArraySt() {
    keys = (Key[]) new Object[INIT_SIZE];
    values = (Value[]) new Object[INIT_SIZE];
  }

  /*
  * insert the key-value pair into the symbol table
   */
  public void put(Key key, Value value) {
    // to deal with duplicates
    delete(key);
    // resize the arrays key and value if need it
    if(n >= values.length) resize(2 * n);

    // add key
    keys[n] = key;
    values[n] = value;
    n++;
  }

  /*
  * get value paired with key
  * (null if key is absent)
   */
  public Value get(Key key) {
    for(int i = 0; i < n; i++) {
      if(keys[i].equals(key)) return values[i];
    }
    return null;
  }
//  public Value get(Key key) {
//      if(isEmpty()) return null;
//      int i = rank(key);
//      if(i < n && keys[i].compareTo(key) == 0) return values[i];
//      else return null;
//  }

  /*
  * remove key and its value from table
  * lazy version of delete method
   */
  public void delete(Key key) {
    for(int i = 0; i < n; i++) {
      if(keys[i].equals(key)) {
        // if found it then put it at the end
        keys[i] = keys[n-1];
        values[i] = values[n-1];
        // to avoid loteiring and help with garbage collection
        keys[n-1] = null;
        values[n-1] = null;
        // and then delete it
        n--;
        // resize if need it
        if(n > 0 && n == keys.length/4) resize(keys.length/2);
        return;
      }
    }
  }
//  public void delete(Key key) {
//    put(key, null);
//  }

  /*
  * Is there a value paired with key ?
   */
  public boolean contains(Key key) {
    return get(key) != null;
  }


  /*
  * return number of key-value pairs in the table
   */
  public int size() {
    return n;
  }

  /*
  * Is the table Empty?
   */
  public boolean isEmpty() {
    return size() == 0;
  }

  /*
  * Iterate over all keys
   */
  public Iterable<Key> keys() {
    Queue<Key> queue = new Queue<>();
    for(int i = 0; i < n; i++) {
      queue.enqueue(keys[i]);
    }
    return queue;
  }

  public void resize(int capacity) {
    Key[] tempKey = (Key[]) new Object[capacity];
    Value[] tempValue = (Value[]) new Object[capacity];

    for(int i = 0; i < n; i++) {
      tempKey[i] = keys[i];
      tempValue[i] = values[i];
    }
    keys = tempKey;
    values = tempValue;
  }

  public static void main(String[] args) {
    ArraySt<String, Integer> st = new ArraySt<>();
    for(int i = 0; !st.isEmpty(); i++) {
      String key = StdIn.readString();
      st.put(key, i);
    }

    for(String s: st.keys()) {
      StdOut.println(s + " " + st.get(s));
    }
  }

}
