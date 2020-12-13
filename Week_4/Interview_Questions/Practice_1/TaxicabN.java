import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * A taxicab number is an integer that can be expressed as the sum of two cubes of
 * positive integers in two different ways: a^3 + b^3 = c^3 + d^3.
 * For example: 1729 is the smallest taxicab number: 9^3 + 10^3 = 1^3 + 12^3
 * Design an algorithm to find all taxicab numbers with a, b, c and d less than n
 *
 * - Version 1: Use time proportional to n^2 log n and space proportional to n^@
 * - Version 2: Use time proportional to n^2 log n and space proportional to n
 */

/**
 * Find all nontrivial integer soluitons to a^3 + b^3 = c^3 + d^3
 * where a, b, c and d are between 0 and n.
 * By nontrivial, we mean a <= b, c <= d and a < c
 * N = Is the number from 0 to 12 a, b, c and d can be calculated
 *
 * Initially, we have added (1,1),(2,2),(3,3),...,(N,N) in the min-priority queue.
 * After comparing the two sums we change the prev value to the current
 * and then we add (i^3 + (j+1)^3) to the current like finding pairs.
 *
 * for every (a,b) which is being removed from the min-PQ, (c,d)
 * is already in the min-PQ (or was just removed) if a^3+b^3=c^3+d^3
 */

// Create a class Taxicab
public class TaxicabN implements Comparable<TaxicabN>{

  private int i;
  private int j;
  private long cubeSum;               // i^3 + j^3 cached to avoid recomputation

  // create a new tuple (i , j, i ^3 + j^3)
  public TaxicabN(int i, int j) {
    this.i = i;
    this.j = j;
    this.cubeSum = (long) i*i*i + (long) j*j*j;
  }

  // compare by i^3 + j^3, breaking ties by i
  public int compareTo(TaxicabN that) {
    if(this.cubeSum < that.cubeSum)       return -1;
    else if(this.cubeSum > that.cubeSum)  return 1;
    else if(this.i < that.i)              return -1;
    else if(this.i > that.i)              return 1;
    else                                  return 0;
  }

  // traduce the result into more readible string form
  public String toString() {
    return i + "^3 + " + j  + "^3";
  }

  public static void main(String[] args) {
    // find a^3 + b^3 = c^3 + d^3, where a, b, c, d <= n
    int n = StdIn.readInt();

    // Initialize prioriority Queue
    MinPQ<TaxicabN> pq = new MinPQ<TaxicabN>();
    for(int i = 1; i <= n; i++) {
      pq.insert(new TaxicabN(i, i));
    }

    // enumerate sums in ascending order, look for repeated sums
    int count = 1;
    TaxicabN prev = new TaxicabN(0, 0);   // sentinel
    while(!pq.isEmpty()) {
      TaxicabN curr = pq.delMin();
//      System.out.println("curr " + curr);
//      System.out.println("prev " + prev);
//      System.out.println("prev.cubeSum "  + prev.cubeSum);
//      System.out.println("curr.cubeSum " + curr.cubeSum);

      // current sum is same as previous sum
      if(prev.cubeSum == curr.cubeSum) {
        count++;
        if(count == 2) StdOut.print(prev.cubeSum + " = " + prev);
        StdOut.print(" = " + curr + "\n");
      } else {
        if(count > 1) StdOut.println();
        count = 1;
      }
      prev = curr;
      if(curr.j < n) pq.insert(new TaxicabN(curr.i, curr.j + 1));
    }
    if(count > 1) StdOut.println();
  }

}
