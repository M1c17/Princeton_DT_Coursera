import edu.princeton.cs.algs4.StdRandom;

public class Shuffling {

  public static void Shuffle(Object[] a) {
    int N = a.length;

    for(int i = 0; i < N; i++) {
      int rand = StdRandom.uniform(i + 1);
      Object swap = a[i];
      a[i] = a[rand];
      a[rand] = swap;
    }
  }
}
