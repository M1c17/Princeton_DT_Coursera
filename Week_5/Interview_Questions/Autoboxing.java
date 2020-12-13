import edu.princeton.cs.algs4.StdOut;

/*
*  Autoboxing is the automatic conversion that the Java compiler makes
*  between the primitive types and their corresponding object wrapper classes.
*  For example, converting an int to an Integer, a double to a Double, and so on.
*  If the conversion goes the other way, this is called unboxing.
* https://stackoverflow.com/questions/12559634/java-autoboxing-rules
* https://docs.oracle.com/javase/tutorial/java/data/autoboxing.html
*
* Converting a primitive value (an int, for example) into an object of the corresponding wrapper class (Integer) is called autoboxing.
*
 */

public class Autoboxing {

  public static void cmp(Integer first, Integer second) {
    if (first < second)
      StdOut.printf("%d < %d\n", first, second);
    else if (first == second)
      StdOut.printf("%d == %d\n", first, second);
    else if (first > second)
      StdOut.printf("%d > %d\n", first, second);
    else
      StdOut.printf("%d and %d are incomparable\n", first, second);
  }

  public static void main(String[] args) {
    cmp(new Integer(42), 43);
    cmp(new Integer(42), new Integer(42));
    cmp(43, 43);
    cmp(142, 142);
    cmp(14, 14);
    cmp(129, 129);

    Integer a0 = 1000;
    int b0 = 1000;
    Integer c0 = 1000;
    StdOut.println("a0==b0?" + (a0==b0));
    StdOut.println("a0==c0?" + (a0==c0));
    StdOut.println("b0==c0?" + (b0==c0));

    double x1 = 0.0, y1 = -0.0;
    Double a1 = x1, b1 = y1;
    StdOut.println(x1 == y1);
    StdOut.println(a1.equals(b1));

    double x2 = 0.0/0.0, y2 = 0.0/0.0;
    Double a2 = x2, b2 = y2;
    StdOut.println(x2 != y2);
    StdOut.println(!a2.equals(b2));
  }

}
