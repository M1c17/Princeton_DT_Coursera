import java.util.Arrays;

public class Main {

  public void show(Comparable[] a) {
    for(int i = 0; i < a.length; i++) {
      System.out.println(a[i]);
    }
  }

  /**
   * MinPQ Test Unit
   */
  public static void main(String[] args) {
    MinPQ pq = new MinPQ();
    pq.insert(34);
    pq.insert(4);
    pq.insert(7);
    pq.insert(8);
    pq.insert(9);
    pq.insert(12);
    pq.insert(1);
    pq.insert(3);
    pq.insert(10);
    int n = pq.size();
    System.out.println("size " + n);
    System.out.print(pq.delMin() + " ");
    System.out.print(pq.delMin() + " ");
    System.out.print(pq.delMin() + " ");
    System.out.print("min " + pq.min() + " ");
  }



  /**
   * HeapSort Test Unit
   */
//  public static void main(String[] args) {
//    Comparable[] a = {3, 4, 7, 2, 1, 9, 12};
//    HeapSort hsort = new HeapSort();
//    hsort.sort(a);
//    Arrays.stream(a).forEach((c) -> System.out.println(c + ","));
//  }



  /**
   * MaxPQ Test Unit
   */
//  public static void main(String[] args) {
//    MaxPQ2 pq = new MaxPQ2();
//    pq.insert(34);
//    pq.insert(4);
//    pq.insert(7);
//    pq.insert(8);
//    pq.insert(9);
//    pq.insert(12);
//    pq.insert(1);
//    pq.insert(3);
//    pq.insert(10);
//    int n = pq.size();
//    System.out.println("size " + n);
//    System.out.print(pq.delMax() + " ");
//    System.out.print(pq.delMax() + " ");
//    System.out.print(pq.delMax() + " ");
//    System.out.print("max " + pq.max() + " ");
//
//  }
}
