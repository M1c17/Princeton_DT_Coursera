import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

  private final boolean[][] grid;
  private final int N;
  private int openSite;
  private final WeightedQuickUnionUF uf;
  private final WeightedQuickUnionUF isFullUf;

  // creates n-by-n grid, with all sites initially blocked
  // Constructor
  public Percolation(int N) {
    if (N <= 0)
      throw new IllegalArgumentException("N should be positive");

    this.N = N;
    this.openSite = 0;
    grid = new boolean[ N + 1 ][ N + 1 ];
    uf = new WeightedQuickUnionUF(1 + N * N + 1);
    isFullUf = new WeightedQuickUnionUF(N * N + 1);

    for (int i = 1; i < N; i++) {
      // Connect source to top
      union(0, i);
      // Connect sink to bottom
      uf.union(N * N - i + 1, N * N + 1);
    }

  }

  // transform from cell(0,1),(0,1),...,(N-1)(N-1) to site (1, 2,....,N^2)
  // use method row-major order formula: index = row * width + col
  // link -> https://www.cyotek.com/blog/converting-2d-arrays-to-1d-and-accessing-as-either-2d-or-1d
  private int xyTo1D(int i, int j) {
    return (i - 1) * N + j;
  }

  private void checkingBounds(int i, int j) {
    if (i <= 0 || i > N)
      throw new IndexOutOfBoundsException("row index i out of bounds");
    if (j <= 0 || j > N)
      throw new IndexOutOfBoundsException("col index j out of bounds");
  }

  private void union(int p, int q) {
    uf.union(p, q);
    isFullUf.union(p, q);
  }

  private void ConnectTop(int i, int j) {
    // base case
    if (i == 1) {
      return;
    }

    if (isOpen(i - 1, j)) {
      int index = xyTo1D(i, j);
      int target = xyTo1D(i - 1, j);
      union(index, target);
    }

  }

  private void ConnectBottom(int i, int j) {
    // base case
    if (i == N) {
      return;
    }

    if (isOpen(i + 1, j)) {
      int index = xyTo1D(i, j);
      int target = xyTo1D(i + 1, j);
      union(index, target);
    }

  }

  private void ConnectLeft(int i, int j) {
    // base case
    if (j == 1) {
      return;
    }

    if (isOpen(i, j - 1)) {
      int index = xyTo1D(i, j);
      int target = xyTo1D(i, j - 1);
      union(index, target);
    }

  }

  private void ConnectRight(int i, int j) {
    // base case
    if (j == N) {
      return;
    }

    if (isOpen(i, j + 1)) {
      int index = xyTo1D(i, j);
      int target = xyTo1D(i, j + 1);
      union(index, target);
    }

  }

  // opens the site (row, col) if it is not open already
  public void open(int row, int col) {
    checkingBounds(row, col);
    // is already open
    if (isOpen(row, col))
      return;
    // otherwise open the site
    grid[ row ][ col ] = true;
    openSite++;
    // check if any neighbors top, bottom, left, right are open if so connect them
    ConnectTop(row, col);
    ConnectLeft(row, col);
    ConnectRight(row, col);
    ConnectBottom(row, col);

  }

  /*
   * is the site (row, col) open?
   * return true is site is open
   */
  public boolean isOpen(int row, int col) {
    checkingBounds(row, col);
    return grid[ row ][ col ];
  }

  // is the site (row, col) full?
  public boolean isFull(int row, int col) {
    if (!isOpen(row, col)) {
      return false;
    }
    int site = xyTo1D(row, col);
    return isFullUf.connected(0, site);
  }

  // returns the number of open sites
  public int numberOfOpenSites() {
//        int openSites = 0;
//        for(int row = 1; row <= N; row++) {
//            for(int col = 1; col <= N; col++)
//                if(isOpen(row, col)) {
//                    openSites++;
//                }
//        }
    return openSite;
  }

  // does the system percolate?
  public boolean percolates() {
    if (N == 1) {
      return isOpen(1, 1);
    }
    return uf.connected(0, N * N + 1);
  }
}

