import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import javax.sound.sampled.Line;
import java.util.Arrays;

/*
 * Copyright (C) 2016 Michael <GrubenM@GMail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 *
 * @author Michael <GrubenM@GMail.com>
 */

/*
 * The method segments() should include each line segment containing 4 points exactly once.
 * If 4 points appear on a line segment in the order p→q→r→s, then you should include either
 * the line segment p→s or s→p (but not both) and you should not include subsegments
 * such as p→r or q→r. For simplicity, we will not supply any input to BruteCollinearPoints
 * that has 5 or more collinear points.
 *
 */

public class BruteCollinearPoints {
  private LineSegment[] segments;
  private int size;
  private Point[] pts;            // mutates for each new origin

  /*
  * Constructor
  * Find all line segments containing 4 points
  * @param  points
  * @throws IllegalAccessException if argument is null
  *                                if any point in the array is null
  *                                if the argument in the constructor contains a repeated point
   */
  public BruteCollinearPoints(Point[] points) {           // finds all line segments containing 4 points
    int N = points.length;
    if(points == null)  throw new IllegalArgumentException("null argument");
    pts = new Point[N];
    // copy points to pts
    for(int i = 0; i < N; i++) {
      pts[i] = points[i];
    }

    /**
     * Checking for null points goes way faster if we've already sorted
     * the points array, but the API requires that we can't mutate the
     * incoming points. Hence we make pts, and sort that.
     */
    Arrays.sort(pts);
    for(int j = 0; j < pts.length - 1; j++) {
      if(pts[j] == null) throw new IllegalArgumentException("null point");
      if(pts[j].compareTo(pts[j+1]) == 0) throw new IllegalArgumentException("Duplicate points");
    }

    // Store the segments we found in a resizing array
    segments = new LineSegment[1];
    size = 0;

    /**
     * Accordingly, consider all possible combination of 4 points
     * sort them to determine which Points are the endpoints, and
     * no additional checks are needed
     *
     * We don't have the A-B B-A symmetry problem here, since we're
     * only considering each potential line segment once.
     */
    Point[] subset = new Point[4];
    for(int i = 0; i < pts.length - 3; i++) {
      subset[0] = pts[i];
      for(int j = i + 1; j < pts.length - 2; j++) {
        subset[1] = pts[j];
        for(int k = j + 1; k < pts.length - 1; k++) {
          subset[2] = pts[k];
          for(int l = k + 1; l < pts.length - 0; l++) {
            subset[3] = pts[l];
            Arrays.sort(subset);
            double slopeA = subset[0].slopeTo(subset[1]);
            double slopeB = subset[0].slopeTo(subset[2]);
            double slopeC = subset[0].slopeTo(subset[3]);
            if(slopeA == slopeB && slopeB == slopeC) {
              enqueue(new LineSegment(subset[0], subset[3]));
            }
          }
        }
      }
    }

  }
  public int numberOfSegments() {                         // the number of line segments
    return size;
  }

  /*
   * return a copy of the line segments in an array with no null elements
   * @param
   * @return LineSegment[]
   */
  public LineSegment[] segments() {
    LineSegment[] shrunk = new LineSegment[size];
    for(int i = 0; i < size; i++) shrunk[i] = segments[i];
    return shrunk;
  }

  public void enqueue(LineSegment line) {
    if(line == null) throw new IllegalArgumentException();
    if(size == segments.length) {
     resize(2 * segments.length, segments);
    }
    segments[size++] = line;
  }
  public void resize(int capacity, LineSegment[] line) {
    LineSegment[] copy = new LineSegment[capacity];

    for(int i = 0; i < size; i++) {
      copy[i] = line[i];
    }
    segments = copy;
  }

  public static void main(String[] args) {
    // read the n points from a file
    In in = new In("input48.txt");
    int n = in.readInt();
    Point[] points = new Point[n];
    for(int i = 0; i < n; i++) {
      int x = in.readInt();
      int y = in.readInt();
      points[i] = new Point(x, y);
    }

    // draw the points
    StdDraw.enableDoubleBuffering();
    StdDraw.setXscale(0, 32768);
    StdDraw.setYscale(0, 32768);
    for(Point p : points) {
      p.draw();
    }
    StdDraw.show();

    // print and draw the line segments
    BruteCollinearPoints collinear = new BruteCollinearPoints(points);
    for(LineSegment segment : collinear.segments()) {
      StdOut.println(segment);
      segment.draw();
    }
    StdDraw.show();
  }
}
