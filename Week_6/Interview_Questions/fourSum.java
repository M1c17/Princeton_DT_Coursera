/*
* Question 1
*  4-SUM. Given an array a[] of n integers, the 4-SUM problem is to determine
*  if there exist distinct indices i, j, k, and l such that a[i] + a[j] = a[k] + a[l].
*  Design an algorithm for the 4-SUM problem that takes time proportional to n^2
*
 */

import java.util.HashSet;

public class fourSum {

  public static boolean fourSum(int[] a) {
    int n = a.length;

    HashSet<Integer> s = new HashSet<>();
    for(int i = 0; i < n; i++) {
      for(int j = i + 1; j < n; j++) {
        int tmp = a[i] + a[j];
        if(s.contains(tmp)) {
          return true;
        } else {
          s.add(tmp);
        }
      }
    }
    return false;
  }

  public static void main(String[] args) {

    int [] a = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    int [] b = {0, 9};
    System.out.println(fourSum(a));
    System.out.println(fourSum(b));
  }

}
