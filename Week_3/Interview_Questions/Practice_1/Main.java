

public class Main {
  public static void show(Comparable[] a) {
    for(int i = 0; i < a.length; i++) {
      System.out.println(a[i]);
    }
  }



  }

//  public static void main(String[] args) {
//    Comparable[] a = {50, 61, 44, 32, 99, 87, 51, 50, 50, 12};
//    ShufflingALinkedlist sf = new ShufflingALinkedlist();
//    sf.shuffle(a);
//    Arrays.stream(a).forEach((c) -> System.out.print(c + ","));
//    System.out.println();
//  }


  /*
  This is main test CountingInversions
   */
//  public static void main(String[] args) {
//    Comparable[] a = {2, 4, 1, 3, 5};
//    int result;
//    CountingInversions cInv = new CountingInversions();
//    result = cInv.inversionCount(a);
//    StdOut.println("Count " + result);
//    System.out.println("After merging: ");
//    Arrays.stream(a).forEach((c) -> System.out.print(c + ","));
//  }


/*
This is main test MergingSmallerAuxiliarArray
 */
//  public static void main(String[] args) {
//    Comparable[] a = {40, 61, 70, 71, 99, 20, 51, 55, 75, 100};
//    MergingSmallerAuxiliarArray m = new MergingSmallerAuxiliarArray();
//    int N = a.length/2;
//    Comparable[] aux = new Comparable[N];
//    m.merge(a, aux, N);
//    System.out.println("After merging: ");
////    show(a);
//    Arrays.stream(a).forEach((c) -> System.out.print(c + ","));
//    System.out.println();
//  }
//}
