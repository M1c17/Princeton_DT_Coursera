import InsertionSort.src.ShellSort;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Main {

  // print array to standard output
  public static void printAll(Comparable[] a){
    for(int i = 0; i < a.length; i++){
      StdOut.println(a[i]);
    }
  }

  public static void main(String[] args) {
    In in = new In("/Users/MASTER/Desktop/GitHub/Algorithms_Coursera/InsertionSort/src/test.txt");
    String[] a = in.readAllStrings();
    //SelectionSort.sort(a);
    //InsertionSort.sort(a);
    ShellSort.sort(a);
    printAll(a);
  }
}
