import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedQueue<Item> implements Iterable<Item> {

  private int N;
  private Node first;
  private Node last;

  public class Node {
    Item item;
    Node next;
  }

  public LinkedQueue() {
    first = null;
    last = null;
    N = 0;
    assert check();
  }

  public boolean isEmpty() {
    return first == null;
  }

  public int size() {
    return N;
  }

  public Item peek() {
    if(isEmpty()) throw new NoSuchElementException();
    return first.item;
  }

  private boolean check() {
    if(N < 0) {
      return false;
    } else if(N == 0) {
      if(first != null) return false;
      if(last != null) return false;
    } else if(N == 1) {
      if(first == null || last == null) return false;
      if(first != last) return false;
      if(first.next != null) return false;
    } else {
      if(first == null || last == null) return false;
      if(first == last) return false;
      if(first.next == null) return false;
      if(last.next != null) return false;

      // check internal consistency of instance variable n
      int numberOfNodes = 0;
      for(Node x = first; x != null && numberOfNodes <= N; x = x.next) {
        numberOfNodes++;
      }
      if (numberOfNodes != N) return false;

      // check internal consistency of instance variable last
      Node lastNode = first;
      while(lastNode.next != null) {
        lastNode = lastNode.next;
      }
      if(lastNode != last) return false;
    }
    return true;
  }

  public void enqueue(Item item) {
    Node oldLast = last;
    last = new Node();
    last.item = item;
    last.next = null;
    if(isEmpty()) first = last;
    else          oldLast.next = last;
    N++;
    assert check();
  }

  public Item dequeue() {
    if(isEmpty()) throw new NoSuchElementException();
    Item item = first.item;
    first = first.next;
    N--;
    if(isEmpty()) last = null;    // to avoid loteiring
    assert check();
    return item;
  }

  /*
  Returns a string representation of this queue
  @return the sequence of items in FIFO order, separated by spaces
  */
  public String toString() {
    // String Object is inmutable
    StringBuilder s = new StringBuilder();
    for(Item item : this)
      s.append(item + " ");
    return s.toString();
  }

  public Iterator<Item> iterator() {
    return new LinkedIterator();
  }

  private class LinkedIterator implements Iterator<Item> {
    private Node current = first;
    @Override
    public boolean hasNext() {
      return current != null;
    }

    @Override
    public Item next() {
      if(!hasNext()) throw new UnsupportedOperationException();
      Item item = current.item;
      current = current.next;
      return item;
    }
  }
}

public class Main {
  public static void main(String[] args) {
    LinkedQueue<String> queue = new LinkedQueue<String>();
    while(!StdIn.isEmpty()) {
      String item = StdIn.readString();
      if(!item.equals("-"))
        queue.enqueue(item);
      else if(!queue.isEmpty())
        StdOut.print(queue.dequeue() + " ");
    }
   StdOut.println("(" + queue.size() + "left on queue )");
  }
}

