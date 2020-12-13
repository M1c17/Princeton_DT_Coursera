import java.util.Iterator;
import java.util.NoSuchElementException;

public class MinHeap <Key extends Comparable<Key>> {
  private Key[] pq;
  private int n;

  public MinHeap() {
    pq = (Key[]) new Comparable[1];
    n = 0;
  }

  public void insert(Key k) {
    if(n == pq.length - 1) resize(2 * pq.length);
    pq[++n] = k;
    swim(n);
    assert isMinHeap();
    //System.out.print(k + ", ");
  }

  public Key delMin() {
    if(isEmpty()) throw new IllegalArgumentException("Empty heap");
    Key min = pq[1];
    swap(1, n--);
    sink(1);
    pq[n+1] = null;        // to avoid with loteiring and help garbage collection
    if(n > 0 && (n == (pq.length-1)/4)) resize(pq.length/2);
    isMinHeap();
    return min;
  }

  public void swim(int k) {
    while(k > 1 && greater(k/2, k)) {
      swap(k, k/2);
      k = k/2;
    }
  }

  public void sink(int k) {
    while(2 * k <= n) {
      int j = 2 * k;
      if(j < n && greater(j, j + 1)) j++;
      if(!greater(k, j)) break;
      swap(j, k);
      k = j;
    }
  }

  public void resize(int capacity) {
    Key[] copy = (Key[]) new Comparable[capacity];
    for(int i = 0; i < pq.length; i++) {
      copy[i] = pq[i];
    }
    pq = copy;
  }

  public int size() {
    return n;
  }

  public Key min() {
    return pq[1];
  }

  public boolean isEmpty() {
    return n == 0;
  }

  public boolean greater(int i, int j) {
    return pq[i].compareTo(pq[j]) > 0;
  }

  public void swap(int i, int j) {
    Key temp = pq[i];
    pq[i] = pq[j];
    pq[j] = temp;
  }

  public boolean isMinHeap() {
    for(int i = 1; i <= n; i++) {
      if(pq[i] == null) return false;
    }

    for(int i = n+1; i < pq.length; i ++) {
      if(pq[i] != null) return false;
    }

    if(pq[0] != null) return false;
    return isMinOrdered(1);
  }

  public boolean isMinOrdered(int k) {
    if(k > n) return false;
    int left = 2 * k;
    int right = 2 * k + 1;
    if(left <= n && greater(left, k)) return false;
    if(right <= n && greater(right, k)) return false;
    return isMinOrdered(left) && isMinOrdered(right);
  }

  public Iterator<Key> iterator() {
    return new HeapIterator();
  }

  public class HeapIterator implements Iterator<Key> {

    private MinHeap<Key> copy;

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
      if(isEmpty()) throw new NoSuchElementException("Empty heap");
      return copy.delMin();
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }
  }
}
