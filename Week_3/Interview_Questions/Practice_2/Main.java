import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class Main {


//  public static void main(String[] args) {
//    Comparable[] a = {1, 2, 1, 2, 1, 2, 1, 4, 3, 2, 4, 2, 3, 5, 6, 7, 8, 9, 3, 2, 4, 5, 2, 3, 6, 7, 8, 3, 2, 5};
//    DecimalDominants d = new DecimalDominants(a);
//    d.sort(a);
//  }


  /**
   * Test Unit for Question 2: Selection two sorted Arrays
   */
  public static void main(String[] args) {
//    // work for odds
//    int[] a = {1, 3, 8, 9, 15};
//    int[] b = {7, 11, 18, 19, 21, 25};
//    // works for evens
////    int[] a = {23, 26, 31, 35};
////    int[] b = {3, 5, 7, 9, 11, 16};
//    int k = 2;

    int[] a = {1, 5, 6, 8, 10, 11, 16, 30};
    int[] b = {2, 9, 13, 15, 17, 18, 20, 22};
    int k = 2;

    SelectionInTwoSortedArrays selection =  new SelectionInTwoSortedArrays(a, b, 2);
    int result = selection.findkth(a, b, k);
    System.out.print("k: " + result + "\n");
    double media = selection.findMedianSortedArrays(a, b);
    System.out.println("Media: " + media);
  }


/**
 * Test Unit for Question 1: Nuts and Bolts
 */
//  public static void main(String[] args) {
//    Comparable[] nuts = {6, 2, 8, 1, 4, 3, 54, 32, 12, 89};
//    Comparable[] bolts = {54, 32, 12, 89, 1, 4, 3, 2, 8, 6};
//    StdRandom.shuffle(nuts);
//    StdRandom.shuffle(bolts);
//
//    NutsAndBolts n = new NutsAndBolts(nuts, bolts);
//    System.out.print("this is nuts: ");
//    Arrays.stream(nuts).forEach((c) -> System.out.print(c + ","));
//    System.out.print("this is bolts: ");
//    Arrays.stream(bolts).forEach((c) -> System.out.print(c + ","));
//  }
}
