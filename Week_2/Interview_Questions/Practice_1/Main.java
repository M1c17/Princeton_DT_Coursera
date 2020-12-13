import edu.princeton.cs.algs4.StdOut;


public class Main {

/*
This test is for DutchNationalFlag
 */
  public static void main(String[] args) {
    Pebble[] pebbles = {Pebble.white, Pebble.red, Pebble.blue, Pebble.blue, Pebble.white};
    StdOut.println("red " + Pebble.red.ordinal());
    StdOut.println("blue " + Pebble.blue.ordinal());
    StdOut.println("white " + Pebble.white.ordinal());

    DutchNationalFlag Dutch = new DutchNationalFlag();
    Dutch.sort(pebbles);
  }




  /*
  This test is for Permutation
   */
//  public static void main(String[] args){
//    System.out.println("Give me the size of array a");
//    int nA = StdIn.readInt();
//    int[] a = new int[nA];
//    for(int i = 0; i < nA; i++) {
//      a[i] = StdIn.readInt();
//    }
//    System.out.println("Give me the size of array b");
//    int nB = StdIn.readInt();
//    int[] b = new int[nB];
//    for(int j = 0; j < nB; j++) {
//      b[j]= StdIn.readInt();
//    }

//    Integer[] a = {1, 2, 3, 4, 5};
//    //Integer[] b = {1, 2, 3, 4, 5};
//    //Integer[] b = {5, 6, 7, 8, 9};
//    Integer[] b = {5, 2, 3, 1, 4};
//    boolean result;
//    Permutation permutation = new Permutation();
//    result = permutation.Permutation(a, b);
//    StdOut.println(result);
//  }







  /*
  This test is for intersectionOfTwoArrays
   */
//  public static void main(String[] args) {
//    System.out.println("Give me the length of array A");
//    int nA = StdIn.readInt();
//    Point2D[] a = new Point2D[nA];
//    for(int i = 0; i < nA; i++) {
//      int xA = StdIn.readInt();
//      int yA = StdIn.readInt();
//      a[i] = new Point2D(xA, yA);
//    }
//    System.out.println("Give me the length of array B");
//    int nB = StdIn.readInt();
//    Point2D[] b = new Point2D[nB];
//    for(int j = 0; j < nB; j++) {
//      int xB = StdIn.readInt();
//      int yB = StdIn.readInt();
//
//      b[j] = new Point2D(xB, yB);
//    }
//    int count;
//    IntersectionOfTwoSets intersection = new IntersectionOfTwoSets();
//    count = intersection.IntersectionOfTwoSets(a, b);
//    StdOut.println("There are: " + count + " intersections in array a and b");
//  }
}
