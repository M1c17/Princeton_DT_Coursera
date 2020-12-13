/**
 * A disorganized carpenter has a mixed pile of n nuts and n bolts.
 * The goal is to find the corresponding pairs of nuts and bolts.
 * Each nut fits exactly one bolt and each bolt fits exactly one nut.
 * By fitting a nut and a bolt together, the carpenter can see which one is bigger
 * (but the carpenter cannot compare two nuts or two bolts directly).
 * Design an algorithm for the problem that uses at most proportional to n \log n
 * compares (probabilistically).
 */

import edu.princeton.cs.algs4.StdRandom;

/**
 * The elements in the nuts and bolts arrays have a one-to-one correspondence. And there is a size relationship between the elements.
 * This requires using the elements in one array to partition another array, and repeating this process in turn, so that both arrays
 * meet the same order defined by the comparator.
 * What we should pay attention to here is the variant of partition, because pivot is not the source of the current array, and can
 * only use one element as a benchmark to partition another array.
 * The core is: first use an element in nuts as a benchmark to partition bolts, and then use the benchmark element obtained in bolts
 * as a benchmark to partition nuts.
 *
 * https://programmer.help/blogs/coursera-algorithms-week3-quick-sort-exercise-test-nuts-and-bolts.html
 * https://www.jiuzhang.com/solutions/nuts-bolts-problem/
 *
 */

public class NutsAndBolts {

  public NutsAndBolts(Comparable[] nuts, Comparable[] bolts) {
    if(nuts == null || bolts == null) throw new IllegalArgumentException("The array is null");
    if(nuts.length != bolts.length) throw new IllegalArgumentException("The arrays are not the same length");

    quickSort(nuts, bolts, 0, nuts.length - 1);
  }

  public static void quickSort(Comparable[] a,Comparable[] b, int lo, int hi) {
    if (lo >= hi) return;
    // find the partition_index for nuts with bolts[lo]
    int partition_index = partition(a, lo, hi, b[lo]);
    // partition bolt with partition_index nuts
    partition(b, lo, hi, a[partition_index]);

    // sort left
    quickSort(a, b, lo, partition_index - 1);
    // sort right
    quickSort(a, b, partition_index + 1, hi);
  }

  private static int partition(Comparable[] a,  int lo, int hi, Comparable pivot) {
    // find pairs
    for (int i = lo; i <= hi; i++) {
      if (a[i].compareTo(pivot) == 0) {
        //System.out.print("find" + a[i]);
        // swap the found element at the begining
        exch(a, i, lo);
        break;
      }
    }
    Comparable current_pivot = a[lo];
    int left = lo;
    int right = hi;
    while (true) {
      while (less(pivot, a[right])) {
        if(right == lo) break;
        right--;
      }
      exch(a, right, left);

      while (less(a[left], pivot)) {
        if(left == hi) break;
        left++;
      }
      if(left >= right) break;
      exch(a, left, right);

    }
    a[left] = current_pivot;

    return left;
  }

  public static boolean less(Comparable v, Comparable w) {
    return v.compareTo(w) < 0;
  }

  public static void exch(Object[] a, int i, int j) {
    Object temp = a[i];
    a[i] = a[j];
    a[j] = temp;
  }
}

