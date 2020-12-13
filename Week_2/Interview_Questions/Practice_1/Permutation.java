import edu.princeton.cs.algs4.Shell;

/*
Given two integer array of size n, design a subquadratic algorithm to determine whether one is a
permutation of the other. That is, do they contain exactly the same entries but, possible in different order.
*/
public class Permutation {
  private int nA;
  private int nB;

  public boolean Permutation(Integer[] a, Integer[] b) {
    nA = a.length;
    nB = b.length;

    if(nA != nB){ return false; }
    Shell.sort(a);
    Shell.sort(b);

    for(int i = 0; i < nA; i++) {
      if(a[i] != b[i]) return false;
    }
    return true;
  }
}
