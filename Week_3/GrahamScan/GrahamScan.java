import edu.princeton.cs.algs4.Point2D;
import java.util.Arrays;
import java.util.Stack;

/*
The GrahamScan data type provides methods for computing the convex hull of a set of
n points in the plane.
Create points from standard input and compute the convex hull using Graham scan algorithms
 */
public class GrahamScan {
  private Stack<Point2D> hull = new Stack<Point2D>();

  /*
  Compute the convex hull of the specified array of points.
   */
  public GrahamScan(Point2D[] points) {
    if(points == null) throw new IllegalArgumentException("Argument is null");
    if(points.length == 0) throw new IllegalArgumentException("Array is of length 0");

    // defensive copy
    int n = points.length;;
    Point2D[] a = new Point2D[n];
    for(int i = 0; i < n; i++) {
      if(points[i] == null) {
        throw new IllegalArgumentException("points[" + i + "] is null");
      }
      a[i] = points[i];
    }

    // preprocess so that a[0] has lowest y-coordinate; break ties by x-coordinate
    // a[0] is an extreme point of the convex hull
    // (alternatively, could do easily in linear time)
    Arrays.sort(a);

    // sort by polar angle with respect to base point a[0],
    // breaking ties by distance to a[0]
    /*
    polarOrder:
    if q1 is above and p and q2 is below p then q1 makes smaller polar angle
    if q1 is below and q2 is above p then q1 makes larger polar angle
    Otherwise, ccw(p,q1,q2) identifies which of q1 or q2 makes larger angle
     */
    Arrays.sort(a, 1, n, a[0].polarOrder());

    hull.push(a[0]);      // a[0] is first extreme point

    // find index k1 of first point not equal to a[0]
    // not the same cardinal point (not the same point)
    int k1;
    for(k1 = 1; k1 < n; k1++) {
      if(!a[0].equals(a[k1])) break;
    }
    if(k1 == n) return;   // all points equal

    // find index k2 of first point not collinear with a[0] and a[k1]
    int k2;
    for(k2 = k1+1; k2 < n; k2++) {
      // not collinear
      if(Point2D.ccw(a[0], a[k1], a[k2]) != 0) break;
    }
    hull.push(a[k2-1]);   // a[k2-1] is second extreme point

    // Graham scan; note that a[n-1] is extreme point different from a[0]
    // finding the vertices of the convex
    for(int i = k2; i < n; i++) {
      Point2D top = hull.pop();
      // if the points are clockwise and collinear
      while(Point2D.ccw(hull.peek(), top, a[i]) <= 0) {
        top = hull.pop();
      }
      // if it is counterclockwise push the last point
      hull.push(top);
      // and the next point
      hull.push(a[i]);
    }
    assert isConvex();
   }

   /*
   return the extreme points on the convex hull in counterclockwise order.
    */
  public Iterable<Point2D> hull() {
    Stack<Point2D> s = new Stack<Point2D>();
    for(Point2D p:hull) s.push(p);
    return s;
  }

  /*
  Check that boundary of hull is strictly convex
  */
  public boolean isConvex() {
    int n = hull.size();
    if(n <= 2) return true;
    Point2D[] points = new Point2D[n];
    int k = 0;
    for(int i = 0; i < n; i++) {
      if(Point2D.ccw(points[i], points[(i+1) % n], points[(i+2) % n]) <= 0) {
        return false;
      }
    }
    return true;
  }

}
