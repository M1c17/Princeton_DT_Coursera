import edu.princeton.cs.algs4.In;

import java.util.Comparator;

/**
 * Given two sorted arrays a[] and b[], of length n1 and n2 and an integer 0 <= k < n1 + n2
 * design an algorithm to find key of rank k. The order of growth f the worst case running
 * time of your algorithm should be log n where n = n1 + n2.
 *
 * Version 1: n1=n2 (equal length arrays) and k=n/2 (median).
 * Version 2: k=n/2 (median).
 * Version 3: no restrictions.
 *
 */

public class SelectionInTwoSortedArrays {

  public SelectionInTwoSortedArrays(int[] a, int[] b, int k) {
    int key = k;
    int n = a.length + b.length;
    if(a == null || b ==null) throw new IllegalArgumentException("null arguments");
    if(key <= 0 || key > n) throw new IllegalArgumentException("k is not between 0 and " + a.length + "is: " + k);
    //findkth(a, b, key);
  }

  public static int findkth(int[] a, int[] b, int k) {
    int i = 0; int j = 0; int m= 0;

    while(i < a.length && j < b.length && m < k - 1) {
      if(less(a[i], b[j])) {
        i++;
      } else {
        j++;
      }
      m++;
    }

    if(i < a.length && j < b.length) {
      if(less(a[i], b[j])) {
        return a[i];
      } else {
        return b[j];
      }
    } else if(i < a.length) {
      return a[i];
    } else if(j < b.length){
      return b[j];
    } else {
      return 0;
    }
  }

  public static double findMedianSortedArrays(int[] a, int[] b) {
    // find smallest array
    // if a.length > b.length. switch them so that a is the smallest array
    if(a.length > b.length) {
      return findMedianSortedArrays(b, a);
    }
    int x = a.length;
    int y = b.length;

    int start = 0;
    int end = x;

    while(start <= end) {
      int partitionX = (start + end)/2;
      int partitionY = (x + y + 1)/2 - partitionX;

      // if partitionX is 0 its means nothing is there on left side. Use -INF for maxLeft
      // if partitionX is == length then there is nothing to the right side. Use INF for minRightX
      int maxLeftX = (partitionX == 0) ? Integer.MIN_VALUE : a[partitionX - 1];
      int minRightX = (partitionX == x) ? Integer.MAX_VALUE : a[partitionX];

      int maxLeftY = (partitionY == 0) ? Integer.MIN_VALUE : b[partitionY - 1];
      int minRightY = (partitionY == y) ? Integer.MAX_VALUE : b[partitionY];

      if(maxLeftX <= minRightY && maxLeftY <= minRightX) {
        // we have partitioned array at correct place
        // get the max of left elements and min of right elements to get the median
        // In case of even array combined the array size of both arrays
        // In case of odd array get max left of both arrays
        if((x + y) % 2 == 0) {                // is even?
          return ((double)Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY))/2;
        } else {
          return ((double)Math.max(maxLeftX, maxLeftY));
        }
      } else if(maxLeftX > minRightY) {        // To much on the right Side
          end = partitionX - 1;                // move toward left on array a
      } else {
          start = partitionX + 1;             // to much on the left Side
      }                                       // move towards right on array a
    }
  // if arrays are not sorted we come here
    throw new IllegalArgumentException();
  }

  public static boolean less(Comparable v, Comparable w) {
    return v.compareTo(w) < 0;
  }
}
