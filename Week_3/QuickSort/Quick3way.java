public class Quick3way {
  public Quick3way() {}

  public void sort(Comparable[] a) {
    sort(a, 0, a.length - 1);
  }

  public static void sort(Comparable[] a, int lo, int hi) {
    if(hi <= lo) return;
    Comparable pivot = a[lo];
    int lt = lo, gt = hi, i = lo + 1;
    while(i <= gt) {
      int cmp = a[i].compareTo(pivot);
      if(cmp < 0) {
        exch(a, lt++, i++);
      } else if(cmp > 0) {
        exch(a, i, gt--);
      } else {
        i++;
      }
    }
    sort(a, lo, lt - 1);
    sort(a, gt + 1, hi);
    isSorted(a);
  }

  public static void exch(Object[] a, int i, int j) {
    Object temp = a[i];
    a[i] = a[j];
    a[j] = temp;
  }

  public static boolean isSorted(Comparable[] a) {
    return isSorted(a, 0, a.length);
  }

  public static boolean isSorted(Comparable[] a , int lo, int hi) {
    for(int i = lo; i < hi; i++) {
      if(a[i].compareTo(a[i-1]) < 0) return false;
    }
    return true;
  }

}
