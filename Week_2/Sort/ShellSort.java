package InsertionSort.src;

public class ShellSort {
  public ShellSort() {

  }

  public static void sort(Comparable[] a) {
    int N = a.length;
    int h = 1;

    // increment sequence
    while(h < N/3) h = 3 * h + 1;

    while(h >= 1) {
      // insertion sort
      for(int i = h; i < N; i++) {
        for(int j = i; j >= h && less(a[j], a[j-h]); j--) {
          exch(a, j, j-h);
        }
      }
      assert isHSorted(a, h);
      h /= 3;
    }
    assert isSorted(a);
  }

  /*********************************************************************
   Helper soritng function
   *********************************************************************/
  public static boolean less(Comparable v, Comparable w) {
    return v.compareTo(w) < 0;
  }

  public static void exch(Object[] a, int i, int j) {
    Object temp = a[i];
    a[i] = a[j];
    a[j] = temp;
  }

  /*********************************************************************
   Check if array is sorted - useful for debugging.
   *********************************************************************/
  public static boolean isSorted(Comparable[] a) {
    for(int i = 0 ; i < a.length; i++) {
      if(less(a[i], a[i-1])) return false;
    }
    return true;
  }

  public static boolean isHSorted(Comparable[] a, int h) {
    for(int i = h; i < a.length; i++) {
      if(less(a[i], a[i-h])) return false;
    }
    return true;
  }
}
