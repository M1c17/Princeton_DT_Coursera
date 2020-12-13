import java.util.Comparator;

public class InsertionSort {

  private InsertionSort() {
  }

  /*
  Rearrage the array in ascending order, using the natural order
   */
  public static void sort(Comparable[] array) {
    int N = array.length;
    for(int i = 1; i < N; i++) {
      for(int j = i; j > 0 && less(array[j], array[j-1]); j--) {
        exch(array, j, j-1);
      }
      assert isSorted(array, 0, i);
    }
    assert isSorted(array);
  }

  /*
  Rearrange the subarray a[lo..hi] in ascending order using a natural order
   */
  public static void sort(Comparable[] array, int lo, int hi) {
    for(int i = lo + 1; i < hi; i++) {
      for(int j = i; j > lo && less(array[j], array[j-1]); j--) {
        exch(array, j, j-1);
      }
    }
    assert isSorted(array, lo, hi);
  }

  /*
  Rearrange the array in ascending order, using comparator.
  @parameter array
  @parameter comparator the comparator specifying the order
   */
  public static void sort(Object[] array, Comparator comparator) {
    int N = array.length;
    for(int i = 1; i < N; i++) {
      for(int j = i; j > 0 && less(array[j], array[j-1], comparator); j--) {
        exch(array, j, j-1);
      }
      assert isSorted(array, 0, i, comparator);
    }
    assert isSorted(array, comparator);
  }

  /*
  Rearrange the subarray array[lo..hi] in ascending order using comparator.
   */
  public static void sort(Object[] array, int lo, int hi, Comparator comparator) {
    for(int i = lo + 1; i < hi; i++) {
      for(int j = i; j > 0 && less(array[j], array[j-1], comparator); j--) {
        exch(array, j, j-1);
      }
      assert isSorted(array, lo, hi, comparator);
    }
  }

  /*
  Return a permutation that given the elements in a[] in ascending order
  do not change the original array a[]
   */
  public static int[] indexsort(Comparable[] a) {
    int N = a.length;
    int[] index = new int[N];

    for(int i = 0; i < N; i++) {
      index[i] = i;
    }

    for(int i = 1; i < N; i++) {
      for(int j = i; j > 0 && less(a[j], a[j-1]); j--) {
        exch(a, j, j-1);
      }
    }
    return index;
  }

  /*********************************************************************
   Helper sorting function
   *********************************************************************/
  public static boolean less(Comparable v, Comparable w){
    return v.compareTo(w) < 0;
  }

  private static boolean less(Object v, Object w, Comparator comparator) {
    return comparator.compare(v, w) < 0;
  }

  public static void exch(Object[] a, int i, int j) {
    Object temp = a[i];
    a[i] = a[j];
    a[j] = temp;
  }

  public static void exch(int[] a, int i, int j) {
    int temp = a[i];
    a[i] = a[j];
    a[j] = temp;
  }

  /*********************************************************************
   Check if array is sorted - useful for debugging.
   *********************************************************************/
   public static boolean isSorted(Comparable[] a) {
     return isSorted(a, 0, a.length);
   }

   public static boolean isSorted(Comparable[] a, int lo, int hi) {
     for(int i = lo + 1; i <= hi; i++) {
       if(less(a[i], a[i-1])) return false;
     }
     return true;
   }

   public static boolean isSorted(Object[] a, Comparator comparator) {
     return isSorted(a, 0, a.length, comparator);
   }

   public static boolean isSorted(Object[] a, int lo, int hi, Comparator comparator) {
     for(int i = lo + 1; lo <= hi; lo++) {
       if(less(a[i], a[i-1], comparator)) return false;
     }
     return true;
   }
}
