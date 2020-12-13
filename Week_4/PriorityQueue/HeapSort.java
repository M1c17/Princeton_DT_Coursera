public class HeapSort {

  public HeapSort() {}

  public void sort(Comparable[] pq) {
    int n = pq.length;
    // bottom up method
    for(int k = n/2; k >= 1; k--) {
      sink(pq, k, n);
    }
    int k = n;
    while(k > 1) {
      swap(pq, 1, k--);
      sink(pq, 1, k);
    }
  }

  public void sink(Comparable[] pq, int k , int n) {
    while(2*k <= n) {
      int j = 2*k;
      if(j < n && less(pq, j, j+1)) j++;
      if(!less(pq, k, j)) break;
      swap(pq, k, j);
      k = j;
    }
  }

  private boolean less(Comparable[] pq, int i, int j) {
    return pq[i-1].compareTo(pq[j-1]) < 0;
  }

  private static void swap(Object[] pq, int i, int j) {
        Object temp  = pq[i-1];
        pq[i-1] =pq[j-1];
        pq[j-1] = temp;
  }
}
