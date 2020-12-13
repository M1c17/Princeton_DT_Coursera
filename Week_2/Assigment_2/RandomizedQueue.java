import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class RandomizedQueue<Item> implements Iterable<Item> {
  private Item[] queue;
  private int N;
  private int first;
  private int last;

  // construct an empty randomized queue
  public RandomizedQueue() {
    queue = (Item[]) new Object[2];
    N = 0;
    first = 0;
    last = 0;
  }

  // is the randomized queue empty?
  public boolean isEmpty() {
    return N == 0;
  }

  // return the number of items on the randomized queue
  public int size() {
    return N;
  }

  public void resize(int capacity) {
    assert capacity >= N;
    Item[] copy = (Item[]) new Object[capacity];
    for(int i = 0; i < N; i++) {
      // position
      copy[i] = queue[(first + i) % queue.length];
    }
    queue = copy;
    first = 0;
    last = N;
  }

  // add the item
  public void enqueue(Item item) {
    if(item == null) throw new IllegalArgumentException();
    // isFull
    if(N == queue.length) resize(2 * queue.length);
    queue[last++] = item;
    // last reach its capacity wrap-around
    if(last == queue.length) last = 0;
    N++;
    //System.out.println("enqueue item: "+ item);
    //System.out.println("last: " + last);
    //System.out.println("N: " + N);
  }

  // remove and return a random item
  public Item dequeue() {
    if(isEmpty()) throw new NoSuchElementException();
    int rand = StdRandom.uniform(N);
    Item item = queue[rand];
    Item temp;
    if(rand != N-1) {
      temp = queue[rand];
      queue[rand] = queue[N-1];
      queue[N-1] = temp;
    }
    queue[N-1] = null;
    first++;
    N--;
    // shrink size of array if necessary
    if(N > 0 && N == queue.length/4) resize(queue.length/2);
    // first is same as queue length wrap-around
    if(first == queue.length) first = 0;

    //System.out.println("rand: " + rand);
    //System.out.println("dequeue item: "+ item);
    //System.out.println("first: " + first);
    //System.out.println("N: " + N);
    return item;
  }

  // return a random item (but do not remove it)
  public Item sample() {
    if(isEmpty()) throw new NoSuchElementException();
    int rand = StdRandom.uniform(N);
    Item item = queue[rand];
    return item;
  }

  // return an independent iterator over items in random order
  public Iterator<Item> iterator() {
    return new ListIterator();
  }

  public class ListIterator implements Iterator<Item> {
    private int i;

    public boolean hasNext() {
      return queue[i] != null;
    }
    public void remove() {throw new UnsupportedOperationException();}

    public Item next() {
      if(!hasNext()) throw new IllegalArgumentException();
      Item item = queue[i++];
      //Item item = queue[(first + i) % queue.length];
      //i++;
      return item;
    }
  }
}
