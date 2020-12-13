package Practice;

/*
* Question 1:
* 3-SUM in quadratic time. Design an algorithm for the 3-SUM problem that takes time proportional to N2 in the worst case.
* You may assume that you can sort the N integers in time proportional to N2 or better.
*/

public class three_sum {

  public static int count(int[] a) {
    int N = a.length;
    int count = 0;

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++)
        for (int k = 0; k < N; k++)
          if (a[ i ] + a[ j ] + a[ k ] == 0) {
            count++;
          }
    }
    return count;
  }

  public boolean sum(int[] a, int key) {
    int left = 0;
    int right = a.length;
    int value = a[ key ];

    while (left < right) {
      if (left == key || a[ left ] + a[ right ] < value) left++;
      else if (right == key || a[ left ] + a[ right ] > value) right--;
      else return true;
    }
    return false;
  }

  public boolean threeSum(int[] a) {
    Array.sort(a);

    for (int i = 0; i < a; i++) {
      if (sum(a, i)) return true;
    }
    return false;
  }

  public static void main(String[] args) {
//    int[] a = In.readInst(args[0]);

  }

}


