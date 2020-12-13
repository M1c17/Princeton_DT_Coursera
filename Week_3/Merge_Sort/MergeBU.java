

public class MergeBU {

  private MergeBU() {

  }

  private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
    // copy original into aux
    for(int i = lo; i <= hi; i++) {
       aux[i] = a[i];
      //StdOut.println(a[i]);
    }

    int i = lo;
    int j = mid + 1;
    for(int k = lo; k <= hi; k++) {
      if(j > hi)                    a[k] = a[i++];
      else if(i > mid)              a[k] = a[j++];
      else if(less(aux[i], aux[j])) a[k] = a[i++];
      else                          a[k] = a[j++];
    }
  }

  public static void sort(Comparable[] a) {
    int n = a.length;
    Comparable[] aux = new Comparable[n];

    for(int len = 1; len < n; len *= 2) {
      for(int lo = 0; lo < n-len; lo += len+len) {
        int mid = lo+len-1;
        int hi = Math.min(lo+len+len-1, n-1);
        merge(a, aux, lo, mid, hi);
      }
    }
    assert isSorted(a);
  }

  public static boolean isSorted(Comparable[] a){
    for(int i = 0; i < a.length; i++) {
      if(less(a[i], a[i-1])) return false;
    }
    return true;
  }

  public static boolean less(Comparable v, Comparable w) {
    return v.compareTo(w) < 0;
  }
}
