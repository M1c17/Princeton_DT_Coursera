import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class QuickSort {

  public QuickSort() { }

  public static void sort(Comparable[] a) {
    StdRandom.shuffle(a);
    sort(a, 0, a.length - 1);
    assert isSorted(a);
  }

  public static void sort(Comparable[]a, int lo, int hi) {
    if(hi <= lo) return;
    int j = partition(a, lo, hi);
    // sort left
    sort(a, lo, j-1);
    // sort right
    sort(a, j+1, hi);
    assert isSorted(a, lo, hi);
  }


  public static int partition(Comparable[] a, int lo, int hi) {
    int i = lo;
    int j = hi + 1;
    //System.out.print("this is j= " + a[j] + "\n");
    Comparable pivot = a[lo];
    while(true) {
      // find item on lo to swap
      while(less(a[++i], pivot)) {
        if(i == hi) break;
      }
      // find item on hi to swap
      while(less(pivot, a[--j])) {
        if(j == lo) break;
      }

      // check if pointer cross
      if(i >= j) break;

      exch(a ,i, j);
    }
    // put partitioning item pivot at a[j] // like in the middle
    // nothing to the left of partitionIndex is greater and nothing to the right of partitionIndex is less
    exch(a, lo, j);
    // now a[lo .. j-1] <= a[j] <= a[j+1]

    return j;
  }

  /**
   * Rearranges the array so that a[k] contains the smallest k
   * so that a[0] <= k and k <= than k+1
   * @param a
   * @return
   */
  public static Comparable select(Comparable[] a, int k) {
    if(k < 0 || k >= a.length) throw new IllegalArgumentException("index is not between 0 and " + a.length + "is: " + k);
    StdRandom.shuffle(a);
    int lo = 0, hi = a.length - 1;
    while(hi > lo) {
      int i = partition(a, lo, hi);
      if(i > k) hi = i - 1;
      else if(i < k) lo = i + 1;
      else return           a[i];
    }
    return a[lo];
  }


  public static boolean isSorted(Comparable[] a) {
    return isSorted(a, 0, a.length - 1);
  }

  public static boolean isSorted(Comparable[] a, int lo, int hi) {
    for(int i = lo; i <= hi; i++) {
      if(less(a[i], a[i - 1])) return false;
    }
    return true;
  }

  public static boolean less(Comparable v, Comparable w) {
    if(v == w) return false;
    return v.compareTo(w) < 0;
  }

  public static void exch(Object[] a, int i, int j) {
    Object temp = a[i];
    a[i] = a[j];
    a[j] = temp;
  }
}
