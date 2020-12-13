/******************************************************************************
 *  Compilation:  javac Accumulator.java
 *  Execution:    java Accumulator < input.txt
 *  Dependencies: StdOut.java StdIn.java
 *
 *  Mutable data type that calculates the mean, sample standard
 *  deviation, and sample variance of a stream of real numbers
 *  use a stable, one-pass algorithm.
 *
 ******************************************************************************/


import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/*
 * write a computer program to estimate p*.
 */
public class PercolationStats {

  private double[] p;
  private int T;

  // perform independent trials on an n-by-n grid
  // perform T independent computational experiments on an N X N grid
  public PercolationStats(int N, int trials)
  {
    if(N <= 0) {
      throw new IllegalArgumentException("Incorrect argument N");
    }

    if(trials <= 0) {
      throw new IllegalArgumentException("Incorrect argument trials");
    }

    this.T = trials;
    p = new double[T];
    for(int i = 0; i < T; i++) {
      Percolation percolation = new Percolation(N);
      double threshold = 0;
      while(!percolation.percolates()) {
        int row = StdRandom.uniform(N) + 1;
        int column = StdRandom.uniform(N) + 1;
        if(!percolation.isOpen(row, column)) {
          threshold++;
          percolation.open(row, column);
        }
      }
      p[i] = threshold / (N * N);
    }
  }


  // sample mean of percolation threshold
  public double mean() {
    return StdStats.mean(p);
  }

  // sample standard deviation of percolation threshold
  public double stddev() {
    return StdStats.stddev(p);
  }

  // low endpoint of 95% confidence interval
  public double confidenceLo() {
    return mean() - 1.96 * stddev() / Math.sqrt(T);
  }

  // high endpoint of 95% confidence interval
  public double confidenceHi() {
    return mean() + 1.96 * stddev() / Math.sqrt(T);
  }

  // test client (see below)
  public static void main(String[] args) {
    if(args.length != 2) {
      return;
    }

    try {
      int N = Integer.parseInt(args[0]);
      int T = Integer.parseInt(args[1]);

      PercolationStats percolationStats = new PercolationStats(N, T);
      StdOut.println("mean = " + percolationStats.mean());
      StdOut.println("stddev = " + percolationStats.stddev());
      StdOut.println("confidenceLo = " + percolationStats.confidenceLo());
      StdOut.println("confidenceHi = " + percolationStats.confidenceHi());
      StdOut.println("95% confidence interval = " + percolationStats.confidenceLo() + percolationStats.confidenceHi());
    } catch (NumberFormatException e) {
      StdOut.println("Argument must be an integer");
      return;
    }
  }

}

