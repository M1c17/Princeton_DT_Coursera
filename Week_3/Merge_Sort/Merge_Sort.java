

public class Merge_Sort {

  // Make sure you have both left and right array sorted
  public static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {

    assert isSorted(a, lo, mid);
    assert isSorted(a, mid+1, hi);

    // copy original into aux
    for(int i = 0; i <= a.length; i++) {
      aux[i] = a[i];
    }

    // index to the left
    int i = lo;
    // index to the right
    int j = mid + 1;
    for(int k = lo; k <= hi; k++) {
      if(i > mid)                   a[k] = aux[j++];        // reach left limit
      else if(j > hi)               a[k] = aux[i++];        // reach right limit
      else if(less(aux[j], aux[i])) a[k] = aux[j++];
      else                          a[k] = aux[i++];
    }
    assert isSorted(a, lo, hi);
  }

  public static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
    // Is the array one element long?
    if(hi <= lo) return;
    int mid = (lo + hi)/2;
    sort(a, aux, lo, mid);
    sort(a, aux, mid+1, hi);
    // practical improvement
    // if(!less(a[mid+1], a[mid])) {
    // for(int i = lo; i <= hi; i++) aux[i] = a[i];
    // return;
    // }
    merge(a, aux, lo, mid, hi);
  }

  // for purpose of performance is needed to create this function sort
  // otherwise there will be extensive cost of aux array creation because the recursive call
  public static void sort(Comparable[] a) {
    Comparable[] aux = new Comparable[a.length];
    sort(a, aux, 0, a.length-1);

  }

  public static boolean isSorted(Comparable[] a) {
    return isSorted(a, 0, a.length);
  }

  public static boolean isSorted(Comparable[] a, int lo, int hi) {
    for(int i = lo; i <= hi; i++) {
      if(less(a[i], a[i-1])) return false;
    }
    return true;
  }

  public static boolean less(Comparable v, Comparable w) {
    return v.compareTo(w) < 0;
  }

}
