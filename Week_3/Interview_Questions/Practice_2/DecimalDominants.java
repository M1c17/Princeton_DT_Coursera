/**
 * Given an array with n keys, design an algorithm to find all values
 * that occur more than n/10 times. The expected running time of yor algorithm
 * should be linear
 */
public class DecimalDominants {

  /**
   * Using 3-way quick sort we can find all the duplicates keys in the middle
   * then is more easy to find the numbers of keys than repeated > n/10
   */

  public DecimalDominants(Comparable[] a) {
    if(a == null) throw new IllegalArgumentException("null Argument");
  }

  public static void sort(Comparable[] a) {
    sort(a, 0, a.length - 1);
  }

  public static void sort(Comparable[] a, int lo, int hi) {
    if(lo >= hi) return;
    int lt = lo, gt = hi;
    int i = lo;
    Comparable v = a[lo];
    while(i <= gt) {
      int cmp = a[i].compareTo(v);
      if(cmp < 0) swap(a, lt++, i++);
      else if(cmp > 0) swap(a, i, gt--);
      else i++;
    }
    int times = gt - lt + 1;
    if(times > a.length / 10) System.out.println(v + " repeats " + times + " times\n");
    sort(a, lo, lt - 1);
    sort(a, gt + 1, hi);
  }

  public static void swap(Object[] a, int i, int j) {
    Object temp = a[i];
    a[i] = a[j];
    a[j] = temp;
  }
}
