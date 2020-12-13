import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
  private Node first;
  private Node last;
  private int N;

  public class Node {
    private Item item;
    private Node next;
    private Node previous;
  }

  // construct an empty deque
  public Deque() {
    first = null;
    last = null;
    N = 0;
  }

  // is the deque empty?
  public boolean isEmpty() {
    return first == null;
  }

  // return the number of items on the deque
  public int size() {
    return N;
  }

  // stack mode
  // add the item to the front
  public void addFirst(Item item) {
    // assert check();
    if(item == null) throw new IllegalArgumentException();
    if(isEmpty()) {
      first = new Node();
      first.item = item;
      last = first;
    } else {
      Node newFirst = new Node();
      newFirst.item = item;
      newFirst.next = first;
      first.previous = newFirst;
      first = newFirst;
    }
    N++;
    System.out.println("addFirst: " + item);
    System.out.println("N:" + N);
  }


  // queue mode
  // add the item to the back
  public void addLast(Item item) {
    // assert check();
    if(item == null) throw new IllegalArgumentException();
    if(isEmpty()) {
      last = new Node();
      last.item = item;
      first = last;
    } else {
      Node newLast = new Node();
      newLast.item = item;
      newLast.previous = last;
      last.next = newLast;
      last = newLast;
    }
    N++;
    System.out.println("addLast: " + item);
    System.out.println("N: " + N);
  }

  // queue mode
  // remove and return the item from the front
  public Item removeFirst() {
    if(isEmpty()) throw new NoSuchElementException();
    Item item = first.item;
    if(first.next != null) {
      first = first.next;
      first.previous = null;
    } else {              // loitering
      first = null;
      last = null;
    }
    N--;
    System.out.println("removeFirst: " + item);
    System.out.println("N: " + N);
    return item;
  }

  // stack mode
  // remove and return the item from the back
  public Item removeLast() {
    if(isEmpty()) throw new NoSuchElementException();
    Item item = last.item;
    if(last.previous != null) {
      last = last.previous;
      last.next = null;
    } else {              // loitering
      first = null;
      last = null;
    }
    N--;
    System.out.println("removeLast: " + item);
    System.out.println("N: " + N);
    return item;
  }

  // return an iterator over items in order from front to back
  public Iterator<Item> iterator() {
    return new ListIterator();
  }

  private class ListIterator implements Iterator<Item> {
    private Node currentNode = first;
    @Override
    public boolean hasNext() {
      return currentNode != null;
    }

    public void remove() { throw new UnsupportedOperationException();}

    @Override
    public Item next() {
      if(!hasNext()) throw new NoSuchElementException();
      Item item = currentNode.item;
      currentNode = currentNode.next;
      return item;
    }
  }
}
