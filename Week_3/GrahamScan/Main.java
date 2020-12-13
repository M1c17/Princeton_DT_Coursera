import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Main {
  public static void main(String[] args){
    int n = StdIn.readInt();
    Point2D[] points = new Point2D[n];
    for(int i = 0; i < n; i++) {
      int x = StdIn.readInt();
      int y = StdIn.readInt();
      points[i] = new Point2D(x, y);
    }
    GrahamScan grahamScan = new GrahamScan(points);
    for(Point2D p: grahamScan.hull()) {
      StdOut.println(p);
    }
  }
}
