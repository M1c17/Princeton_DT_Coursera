import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class Main {

  private void show(Comparable[] a) {
    for(int i = 0; i < a.length; i++) {
      System.out.println(a[i]);
    }
  }

  public static void main(String[] args) {
    Quick3way quick3way = new Quick3way();
    Comparable[] a = {6,7,8,9,0,9,5,2,1,2,9,9,9,7,2,3,23,14,56};
    quick3way.sort(a);
    Arrays.stream(a).forEach((c) -> System.out.print(c + ","));
  }

//  public static void main(String[] args) {
//    QuickSort quick = new QuickSort();
//    //Comparable[] a = {56, 34, 78, 0, 2, 3, 6, 8};
//    Comparable[] a = {12, 9, 2, 5, 0, 26};
//    quick.sort(a);
//    Arrays.stream(a).forEach((c) -> System.out.print(c + ","));
//
//    StdRandom.shuffle(a);
//    for(int i = 0; i < a.length; i++) {
//      int ith = (int) quick.select(a, i);
//      StdOut.println(ith);
//    }
//  }
}
