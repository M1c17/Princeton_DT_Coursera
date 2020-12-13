import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Shell;
/*
Given two arrays a[] and b[], each containing n distinct 2D points in the plane, design a
subquadratic algorithm to count the number of points that are contained both in array a[]
and b[]
 */

public class IntersectionOfTwoSets {
  private int nA;
  private int nB;

  public int IntersectionOfTwoSets(Point2D[] a, Point2D[] b) {
    nA = a.length;
    nB = b.length;
    int i = 0;
    int j = 0;
    int count = 0;

    if(a == null) throw new IllegalArgumentException("a is null");
    if(b == null) throw new IllegalArgumentException("b is null");

    if(nA == 0) throw new IllegalArgumentException("a is of length 0");
    if(nB == 0) throw new IllegalArgumentException("b is of length 0");

    Shell.sort(a);
    Shell.sort(b);

    while(i <= nA && j <= nB) {
      if(a[i].compareTo(b[j]) == 0 ) {
        System.out.print(a[i] + "\n");
        System.out.print(b[j] + "\n");
        count++;
        i++;
        j++;
      } else if(a[i].compareTo(b[j]) < 0) {   // When points A < points B advance A
        i++;
      } else {      // When points A > points B advance A
        j++;
      }
    }
    return count;
  }
}
