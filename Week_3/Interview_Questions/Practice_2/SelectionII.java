public class SelectionII {
  /**
   * O(k) solution.
   * Using two pointers, you can traverse both arrays without actually merging them,
   * thus without the extra space. Both pointers are initialized to point to head of A and B respectively,
   * and the pointer that has the smaller of the two is incremented one step.
   *
   * @param a Sorted array a
   * @param b Sorted array b
   * @param K The kth item.
   * @return
   */
  public Comparable findKth(Comparable[] a, Comparable[] b, int K) {

    int i = 0, j = 0, m = 0;

    // Virtually merge
    while (i < a.length && j < b.length && m < K - 1) {
      if (a[i].compareTo(b[j]) < 0) {
        i ++;
      } else {
        j ++;
      }
      m ++;
    }

    if (i < a.length && j < b.length) {
      if (a[i].compareTo(b[j]) < 0) {
        return a[i];
      } else {
        return b[j];
      }
    } else if (i < a.length) {
      return a[i];
    } else if (j < b.length) {
      return b[j];
    } else {
      return null;
    }
  }

  /**
   * Find the median element within two sorted arrays.
   * lengths of two arrays are same k. Suppose all the elements are unique.
   * If there are some duplicated elements, this method will produce incorrect result.
   *
   * http://inder-gnu.blogspot.com/2007/09/find-k-th-largest-in-two-sorted-arrays.html
   *
   * @return
   */
  public Comparable findMedianWithinTwoArrays(Comparable[] a, Comparable[] b) {

    assert(a.length == b.length);

    int k = a.length;
    int midA = k/2, midB = k/2, loA = 0, hiA = k - 1, loB = 0, hiB = k - 1;

    while ((loA < hiA) && (loB < hiB)) {
      if (a[midA].compareTo(b[midB]) > 0) {
        hiA = midA;
        loB = midB;
      } else {
        loA = midA;
        hiB = midB;
      }
      midA = loA + (hiA - loA)/2;
      midB = loB + (hiB - loB)/2;
    }

    //Find the median of the the three elements.
    if (loA == hiA) {
      if (a[loA].compareTo(b[loB]) > 0 && a[loA].compareTo(b[hiB]) < 0) {
        return a[loA];
      } else if (a[loA].compareTo(b[loB]) < 0) {
        return b[loB];
      } else {
        return b[hiB];
      }
    } else { //loB == hiB
      if (b[loB].compareTo(a[loA]) > 0 && b[loB].compareTo(a[hiA]) < 0) {
        return b[loB];
      } else if (b[loB].compareTo(a[loA]) < 0) {
        return a[loA];
      } else {
        return a[hiA];
      }
    }
  }
}
