import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Describe how to add the methods sample() and delRandom() to our binary heap implementations.
 * The two methods return a key that is chosen uniformly at random among the remaining keys, with the
 * latter method also removing that key. The sample method should take constant; the delRandom()
 * should take logarithmic time. Do not worry about resizing the underlying array.
 */

public class RandomizedPQ <Key extends Comparable<Key>>{

  private Key[] pq;
  private int n;

  public RandomizedPQ() {
    pq = (Key[]) new Comparable[1];
  }

  public void insert(Key k) {
    if(n == pq.length - 1) resize(2 * pq.length);
    pq[++n] = k;
    swim(n);
    System.out.println(k);
  }

  public void swim(int k) {
    while(k > 1 && less(k/2, k)) {
      swap(k, k/2);
      k = k/2;
    }
  }

  public void sink(int k) {
    while(2*k <= n) {
      int j = 2*k;
      if(less(j, j+1)) j++;
      if(!less(k, j)) break;
      swap(k, j);
      k = j;
    }
  }

  public Key delRandom() {
    if(isEmpty()) throw new NoSuchElementException();
    int rdKey = StdRandom.uniform(n);
    Key k = pq[rdKey];
    if(rdKey != n-1) {
      swap(n-1, rdKey);
    }
    // Key top = pq[1];
    swap(1, n--);
    sink(1);
    pq[n+1] = null;         // to avoid loteiring and help with garbage collection
    if(n > 0 && n == (pq.length)/4) resize(pq.length/2);
    return k;
  }

  public Key sample() {
    if(isEmpty()) throw new NoSuchElementException();
    int rdKey = StdRandom.uniform(n);
    Key k = pq[rdKey];
    System.out.println("random key " + k);
//    for(int i = 0; i < pq.length; i++) {
//      if(k == pq[i]) {
//        System.out.println("random key " + k);
//      }
//    }
    return k;
  }

  public void printPQ() {
    for(int i = 0; i < n; i++) {
      System.out.println(pq[i] + ", ");
    }
  }

  public boolean isEmpty() {
    return n == 0;
  }

  public boolean less(int i, int j) {
    return pq[i].compareTo(pq[j]) < 0;
  }

  public void swap(int i, int j) {
    Key temp = pq[i];
    pq[i] = pq[j];
    pq[j] = temp;
  }

  public void resize(int capacity) {
    Key[] copy = (Key[]) new Comparable[capacity];
    for(int i = 0; i <= pq.length - 1; i++) {
      copy[i] = pq[i];
    }
    pq = copy;
  }

  public Iterator<Key> Iterator() {
    return new HeapIterator();
  }

  public class HeapIterator implements Iterator<Key> {
    private RandomizedPQ<Key> copy;

    public HeapIterator() {
      for(int i = 0; i <= n; i++) {
        copy.insert(pq[i]);
      }
    }

    @Override
    public boolean hasNext() {
      return !copy.isEmpty();
    }

    @Override
    public Key next() {
      if(!hasNext()) throw new NoSuchElementException();
      return copy.delRandom();
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }
  }

}
