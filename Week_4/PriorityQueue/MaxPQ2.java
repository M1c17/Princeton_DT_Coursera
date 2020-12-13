import java.util.Iterator;
import java.util.NoSuchElementException;

public class MaxPQ2 <Key extends Comparable<Key>>{
  private int n;
  private Key[] pq;


  public MaxPQ2() {
    pq = (Key[]) new Comparable[1];
  }

  public void insert(Key item) {
    if(n == pq.length - 1) resize(2 * pq.length);
    pq[++n] = item;
    swim(n);
    assert isMaxHeap();
    System.out.println(item);
  }

  public void swim(int k) {
    while(k > 1 && less(k/2, k)) {
      swap(k/2, k);
      k = k/2;
    }
  }

  public void sink(int k) {
    while(2*k <= n) {
      int j = 2*k;
      if(j < n && less(j, j+1)) j++;
      if(!less(k, j)) break;
      swap(k, j);
      k = j;
    }

  }

  public boolean isEmpty() {
    return n == 0;
  }

  public int size() {
    return n;
  }

  public Key delMax() {
    if(isEmpty()) throw new NoSuchElementException();
    Key max = pq[1];
    swap(1, n--);
    sink(1);
    pq[n+1] = null;       // to avoid lotering and help with garbage collection
    if(n > 0 && n <= pq.length/4) resize(pq.length/2);
    assert isMaxHeap();
    return max;
  }

  public void resize(int capacity) {
    Key[] copy = (Key[]) new Comparable[capacity];

    for(int i = 1; i < pq.length; i++) {
      copy[i] = pq[i];
    }
    pq = copy;
   }

  public Key max() {
    return pq[1];
  }

  public boolean less(int v, int w) {
    return pq[v].compareTo(pq[w]) < 0;
  }

  public void swap(int i, int j) {
      Key temp = pq[i];
      pq[i] = pq[j];
      pq[j] = temp;
  }

  public boolean isMaxHeap() {
    // check for null elements
    for(int i = 1; i <= n-1; i++) {
      if(pq[i] == null) return false;
    }

    // check the last element in the heap
    for(int i = n+1; i < pq.length; i++) {
      if(pq[i] != null) return false;
    }

    // check the first element in the heap
    if(pq[0] != null) return false;
    return isMaxOrdered(1);
  }

  public boolean isMaxOrdered(int k) {
    if(k > n) return false;
    int left = 2*k;
    int right = 2*k+1;
    if(left <= n && less(k, left)) return false;
    if(right <= n && less(k, right)) return false;
    return isMaxOrdered(left) && isMaxOrdered(right);
  }

  public Iterator<Key> iterator() {
    return new HeapIterator();
  }

  private class HeapIterator implements Iterator<Key> {
    private MaxPQ2<Key> copy;

    public HeapIterator() {
      // copy all elements of key in copy
      for(int i = 1; i < n; i++) {
        copy.insert(pq[i]);
      }
    }

    @Override
    public boolean hasNext() {
      return !copy.isEmpty();
    }

    @Override
    public Key next() {
      if(!hasNext()) throw new NoSuchElementException("Empty heap");
      return copy.delMax();
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }
  }
}
