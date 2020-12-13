import java.util.Comparator;

public class SelectionSort {
  public SelectionSort() {
  }

  /*
  Rearrange the array in ascending order, using natural order
  @parameter array to be sorted
  */
  public static void sort(Comparable[] array) {
    int N = array.length;
    for(int i = 0; i < N; i++) {
      int min = i;
      for(int j = i + 1; j < N; j++) {
        if(less(array[j], array[min])) min = j;
      }
      exch(array, i, min);
      assert isSorted(array, 0, i);
    }
    assert isSorted(array);
  }

  /*
  Rearrenge the array in ascending order, using comparator
  @parameter array the array to be sorted
   */
  public static void sort(Object[] array,Comparator comparator) {
    int N = array.length;
    for(int i = 0; i < N; i++) {
      int min = i;
      for(int j = i + 1; i < N; j++) {
        if(less(comparator, array[j], array[min])) min = j;
      }
      exch(array, i , min);
      assert isSorted(array, comparator, 0, i);
    }
    assert isSorted(array, comparator);
  }

  /*********************************************************************
    Helper soritng function
   *********************************************************************/

  public static boolean less(Comparable v, Comparable w) {
    return v.compareTo(w) < 0;
  }

  public static boolean less(Comparator comparator, Object v, Object w) {
    return comparator.compare(v, w) < 0;
  }

  public static void exch(Object[] array, int i, int j) {
    Object temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }

  /*********************************************************************
   Check if array is sorted - useful for debugging.
   *********************************************************************/
  // Is the array a sorted?
  private static boolean isSorted(Comparable[] a) {
    return isSorted(a, 0, a.length - 1);
  }

  // Is the array sorted from a[lo] to a[hi]
  private static boolean isSorted(Comparable[] a, int lo, int hi) {
    for(int i = lo + 1; i <= hi; i++) {
      if(less(a[i], a[i - 1])) return false;
    }
    return true;
  }

  // Is the array a[] sorted?
  private static boolean isSorted(Object[] a, Comparator comparator) {
    return isSorted(a, comparator, 0, a.length - 1);
  }

  // Is the array a[] sorted from a[lo] to a[hi]
  private static boolean isSorted(Object[] a, Comparator comparator, int lo, int hi) {
    for(int i = lo + 1; i <= hi; i++) {
      if(less(comparator, a[i], a[i-1])) return false;
    }
    return true;
  }

}
