
/*
Suppose that the subarray a[0] to a[n-1] is sorted and the subarray a[n] to a[2*n-1]
is sorted. How can you merge the two subarrays so that a[0] to a[2*n-1] is sorted using an
auxiliary array of length n (instead of 2n)?
 */

public class MergingSmallerAuxiliarArray {

  public MergingSmallerAuxiliarArray() {

  }

  public static void merge(Comparable[] a, Comparable[] aux, int N) {
    for(int i = 0; i < N; i++) {
      aux[i] = a[i];
    }

    // i -> index of aux array
    // j -> index of right part of a
    // k -> index of merged array
    int i = 0, j = N, k = 0;
    while(k < a.length) {
      if(i >= N)                  a[k++] = a[j++];
      else if(j >= a.length)      a[k++] = aux[i++];
      else if(less(aux[i], a[j])) a[k++] = aux[i++];
      else                        a[k++] = a[j++];
    }
  }

  public static boolean less(Comparable v, Comparable w) {
    return v.compareTo(w) < 0;
  }

}
