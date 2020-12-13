import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Design a data type that supports insert in logarithmic time, find-the-median in constant time, and remove-the-median in logarithmic time.
 * If the number of keys in the data type is even, find/remove the lower median.
 */

public class DynamicMedian <Key extends Comparable<Key>>{
  private PriorityQueue<Integer> Lowers;
  private PriorityQueue<Integer> Uppers;
//  public PriorityQueue<Integer> biggerHeap;
//  public PriorityQueue<Integer> smallerHeap;

  public DynamicMedian(int[] numbers) {
    if(numbers == null) throw new IllegalArgumentException();
    Lowers = new PriorityQueue<Integer>(new Comparator<Integer>() {
      // Change comparison to put biggest element in the top
      @Override
      public int compare(Integer a, Integer b) {
        return -1 * a.compareTo(b);
      }
    });
    Uppers = new PriorityQueue<Integer>();
  }

  public void add(int number) {
    if(Lowers.size() == 0 || number < Lowers.peek()) {
      Lowers.add(number);
      //System.out.println("lower " + k);
    } else {
      Uppers.add(number);
      //System.out.println("upper " + k);
    }
  }

  /**
   * Compare the size of the two heaps and we want the sizes to be as close
   * to the same size as possible
   * if one side have bigger size than the other one -> switch to the other buccker
   */
  public void rebalance() {
    PriorityQueue<Integer> biggerHeap = Lowers.size() > Uppers.size() ? Lowers : Uppers;
    PriorityQueue<Integer> smallerHeap  = Lowers.size() > Uppers.size() ? Uppers : Lowers;

    if(biggerHeap.size() - smallerHeap.size() >= 2) {
      smallerHeap.add(biggerHeap.poll());
    }
  }

  public double getMedia() {
    PriorityQueue<Integer> biggerHeap = Lowers.size() > Uppers.size() ? Lowers : Uppers;
    PriorityQueue<Integer> smallerHeap = Lowers.size() > Uppers.size() ? Uppers : Lowers;

    if(biggerHeap.size() == smallerHeap.size()) {
      return ((double) biggerHeap.peek() + smallerHeap.peek()) / 2;
    } else {
      return biggerHeap.peek();
    }
  }

}
