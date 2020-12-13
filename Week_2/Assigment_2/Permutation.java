import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {

  public static void main(String[] args) {
    // take the arg
    //int k = Integer.valueOf(args[3]);
    int k = 3;
    In in = new In("/Users/MASTER/Desktop/GitHub/Algorithms_Coursera/Assigment2/src/distinct.txt");
    RandomizedQueue<String> queue = new RandomizedQueue<>();
    // Read from StdIn
    while(!in.isEmpty()) {
      String item = in.readString();
      queue.enqueue(item);
    }
    while(k > 0) {
      StdOut.println(queue.dequeue());
      k--;
    }
  }
}



// unit testing (required)
//  public static void main(String[] args) {
//    Deque<Integer> deque = new Deque<Integer>();
//    deque.addFirst(2);
//    deque.addFirst(5);
//    deque.removeFirst();
//    deque.addLast(7);
//    deque.removeLast();

//  RandomizedQueue<Integer> RdQueue = new RandomizedQueue<Integer>();
//    RdQueue.enqueue(5);
//    RdQueue.enqueue(2);
//    RdQueue.enqueue(7);
//    RdQueue.dequeue();
//    RdQueue.enqueue(9);
//    RdQueue.dequeue();
//    RdQueue.dequeue();

