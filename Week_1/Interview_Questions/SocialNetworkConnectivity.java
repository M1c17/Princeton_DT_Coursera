package Practice;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class SocialNetworkConnectivity {
  /* Question 1
  Social network connectivity.
  Given a social network containing N members and a log file containing M timestamps at which times pairs of members formed friendships,
  design an algorithm to determine the earliest time at which all members are connected
  (i.e., every member is a friend of a friend of a friend ... of a friend).
  Assume that the log file is sorted by timestamp and that friendship is an equivalence relation.
  The running time of your algorithm should be MlogN or better and use extra space proportional to N. */
  private class Log {
    int p;
    int q;
    double time;
  }

  public double getTime(Log[] M, int N) {
    WeightedQuickUnionUF network = new WeightedQuickUnionUF(N);

    for (int i = 0; i < M.length; i++) {
      network.union(M[i].p, M[i].q);
      if (network.count() == 1) return M[i].time;
    }
    return -1;
  }
}
