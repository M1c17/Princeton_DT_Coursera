import edu.princeton.cs.algs4.StdOut;

public class Main {

  public static void show(Comparable[] a) {
    for(int i = 0; i < a.length; i++) {
      StdOut.println(a[i]);
    }
  }

  public static void main(String[] args) {
    Comparable[] a = {5, 7, 88, 9, 0, 2, 15};
    //Merge_Sort.sort(a);
    MergeBU.sort(a);
    show(a);
  }
}
