import edu.princeton.cs.algs4.StdOut;

/*
Given an array of n buckets, each containing a red, white and blue pebble, sort them by color
The allowed operations are:
- swap(i, j): swap the pebble in bucket i with pebble in bucket j.
- color(i): determine the color of the pebble in bucket i.
The performance requirements are as follows:
- At most n calls to color().
- At most n calls to swap().
- Constant extra space.
 */
enum Pebble {red, white, blue}

public class DutchNationalFlag {
  private int start;
  private int end;
  private int index;

  public DutchNationalFlag() {
  }

  public void sort(Pebble[] pebbles) {
    start = 0;
    end = pebbles.length - 1;
    index = 0;

    if(pebbles == null) throw new IllegalArgumentException("the Array p is null");
    if(pebbles.length == 1 || pebbles.length == 0) return;

    while(start < end && index <= end) {
      Pebble color = color(pebbles, index);
      int cmp = compare(color);
      if(cmp < 0) {   // red comes first
        swap(pebbles, index, start);
        index++;
        start++;
      } else if(cmp > 0) {  // blue comes last
        swap(pebbles, index, end);
        end--;
      } else {  // just continue white is in the middle
        index++;
      }
    }
    for(int i = 0; i < pebbles.length; i++) {
      StdOut.println(pebbles[i]);
    }
  }

  // red = 0, white = 1, blue = 2
  // 0 - 1 = -1; 1 - 1 = 0; 2 - 1 = 1
  public int compare(Pebble pebble) {
    // white => 1
    Pebble white = Pebble.white;
    int result = pebble.ordinal() - white.ordinal();
    System.out.println("compare color " + result);
    return result;
  }

  public void swap(Pebble[] pebbles, int i, int j) {
    Pebble temp = pebbles[i];
    pebbles[i] = pebbles[j];
    pebbles[j] = temp;

  }

  public Pebble color(Pebble[] pebbles, int i) {
    return pebbles[i];
  }

}
