
/*
An inversion in an array a[] is a pair of entries a[i] and a[j] such that i < j but a[i] > a[j].
 Given an array, design a linearithmic algorithm to count the number of inversions.
 */

/*
 * Counting inversions.
 * An inversion in an array a[] is a pair of entries a[i] and a[j] such that i<j but a[i]>a[j].
 * Given an array, design a linearithmic algorithm to count the number of inversions.
 *
 * Example: Input: a = {2, 4, 1, 3, 5}
 *          Output: 3
 * In this Array there are 3 inversion 2 - 1, 4 - 3, 3 - 5
 *
 * Inversion count indicates the number of elements in the array that are not sorted yet.
 * countInversion indicates how far or close the Array is from being sorted
 * If the array is already sorted then the count  = 0
 * If the array is is sorted in decreasing order then count = a.length (max number to sorted)
 *
 * http://stackoverflow.com/questions/337664/counting-inversions-in-an-array
 * https://www.youtube.com/watch?v=Vj5IOD7A6f8
 * http://www.geeksforgeeks.org/counting-inversions/
 * https://www.youtube.com/watch?v=k9RQh21KrH8
 *
 * This condition say that the array should be in increasing order
 * and the only way a[i] > a[j] this condition is true is when you're merging
 * making comparison to swap i, j.
 */

public class CountingInversions {

  //public CountingInversions() { }


    public static int merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
      // copy array to aux
      for (int i = lo; i <= hi; i++) {
        aux[i] = a[i];
      }
      int i = lo, j = mid + 1, count = 0;
      for(int k = lo; k <= hi; k++) {
        if(i > mid) {                        a[k] = aux[j++]; }    // left is consumed
        else if(j > hi) {                    a[k] = aux[i++]; }    // right is consumed
        else if(a[i].compareTo(a[j]) > 0) {  a[k] = aux[j++];
                                             count += mid - i + 1; }
        else {                               a[k] = aux[i++]; }
      }
      return count;
    }

    public static int sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
    // base case
      int mid = (lo + hi)/ 2;
      if(hi <= lo) return 0;
      int count1 = sort(a, aux, lo, mid);           // sort left
      int count2 = sort(a, aux, mid+1, hi);     // sort right
      int count3 = merge(a, aux, lo, mid, hi);     // merge both arrays

      return count1 + count2 + count3;
    }

    public static int inversionCount(Comparable[] a) {
    int N = a.length;
    Comparable[] aux = new Comparable[N];
    return sort(a, aux, 0, N-1);
    }
}
