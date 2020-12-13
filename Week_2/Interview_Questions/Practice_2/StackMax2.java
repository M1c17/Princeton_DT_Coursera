/*
Design a max stack that support push, pop, top, peekTop and popMax
 */

import java.util.NoSuchElementException;

public class StackMax2 {
  private Node first;
  private Node currentMax;

  private class Node {
    private int item;
    private Node next;
    private Node oldMax;
  }

  public StackMax2(){
//    stack = new Node();
//    currentMax = 0;
  }

  public boolean isEmpty() {
    return first == null;
  }

  // push element x onto the stack
  public void push(int item) {
    Node oldFirst = first;
    Node n = new Node();
    n.item = item;

    // check if this is first item in the stack
    if(isEmpty()) {
      first = n;
    } else {
      n.next = oldFirst;
      // n node the first Node in the stack
      first = n;
    }
    if(currentMax == null || n.item > currentMax.item) {
      n.oldMax = currentMax;
      currentMax = n;
    }
  }

  // Retrieve the maximum element in the stack, and remove it.
  // if you find more than one maximum elements, only remove the top-most one.
  public int pop() {
    if(isEmpty()) throw new NoSuchElementException();
    Node n = first;
    first = n.next;
    if(n.oldMax != null) currentMax = n.oldMax;
    return n.item;
  }

//  // Get the element on the top
//  public int top() {
//
//  }

  // peek the Max element on the stack
  public int peekMax() {
    if(isEmpty()) throw new NoSuchElementException();
    return currentMax.item;
  }

  // Retrieve the max element in the stack
  public int getMax() {
    if(isEmpty()) throw new NoSuchElementException();
    System.out.println("max:" + currentMax.item);
    return currentMax.item;
  }
}
