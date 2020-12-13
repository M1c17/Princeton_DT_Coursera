import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 * Generic max proirity queue implementation with a binary heap.
 *
 */
public class MaxHeap <Key extends Comparable<Key>> {
  private Key[] pq;
  private int n;

  public MaxHeap() {
    pq = (Key[]) new Comparable[1];
  }

  public boolean isEmpty() {
    return n == 0;
  }

  public void insert(Key item) {
    if(n == pq.length - 1) resize(2 * pq.length);
    pq[++n] = item;
    swim(n);
    //System.out.println(item);
    assert isMaxHeap();
  }

  /**
   * Returns a largest key on this priority queue
   * @return
   * @thorws NoSuchElementException if this priority queue is empty
   *
   */
  public Key max() {
    if(isEmpty()) throw new NoSuchElementException("Priority queue underflow");
    return pq[1];
  }

  public Key delMax() {
    if(isEmpty()) throw new NoSuchElementException("Priority queue underflow");
    Key max = pq[1];
    swap(1, n--);
    sink(1);
    pq[n+1] = null;         // to avoid lotering and help with garbage collection
    if((n > 0) && (n == (pq.length - 1)/4)) resize(pq.length/2);
    assert isMaxHeap();
    return max;
  }

  /**
   * go up the binary heap parent if parent is less than children
   * parent never index 0
   * parent: n/2 and children 2k, 2k + 1
   * @param k
   */
  private void swim(int k) {
    while(k > 1 && less(k/2, k)) {
      swap(k, k/2);
      k = k/2;
    }
  }

  /**
   * go down if parent is at the bottom of binary heap until reach its position
   * to know what branch take we need to node which children is greater
   * @param k
   */
  private void sink(int k) {
    // while the children is not greater than the array size
    while(2*k <= n) {
      int j = 2*k;
      // which one of the branch are bigger
      if(j < n && less(j, j + 1)) j++;
      // parent node reach their position
      if(!less(k, j)) break;
      swap(k, j);
      k = j;
    }
  }

  public boolean less(int v, int w) {
    return pq[v].compareTo(pq[w]) < 0;
  }

  public void swap(int i, int j) {
    Key temp = pq[i];
    pq[i] = pq[j];
    pq[j] = temp;
  }

  public int size() {
    return n;
  }

  public void resize(int capacity) {
    assert capacity > n;
    Key[] copy = (Key[]) new Comparable[capacity];
    for (int i = 1; i <= n; i++) {
      copy[i] = pq[i];
    }
    pq = copy;
  }

  private boolean isMaxHeap() {
    // checking is the values of the array are null
    for(int i = 1; i <= n; i++){
      if(pq[i] == null) return false;
    }
    // this is after the last child
    for(int i = n+1; i < pq.length; i++) {
      if(pq[i] != null) return false;
    }
    // this is the root
    if(pq[0] != null) return false;
    return isMaxHeapOrdered(1);
  }

  private boolean isMaxHeapOrdered(int k) {
    if(k > n) return false;
    int left = 2*k;
    int right = 2*k + 1;
    if(left <= n && less(k, left)) return false;
    if(right <= n && less(k, right)) return false;
    return isMaxHeapOrdered(left) && isMaxHeapOrdered(right);
  }

  /**
   * Returns an iterator that iterates over the keys on this priority queue
   * int descending order.
   */
  public Iterator<Key> iterator() {
    return new HeapIterator();
  }

  private class HeapIterator implements Iterator<Key>{

    // create a new pq
    private MaxHeap<Key> copy;

    // add all items to copy of heap
    // takes linear time since already in heap order so no keys move
    public HeapIterator() {
      for(int i = 1; i <= n; i++) {
        copy.insert(pq[i]);
      }
    }

    @Override
    public boolean hasNext() {
      return !copy.isEmpty();
    }

    @Override
    public Key next() {
      if(!hasNext()) throw new NoSuchElementException("Empty PQ");
      return copy.delMax();
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }
  }
}
