
/*
 * Given a singly-linked list containing n items, rearrange the items uniformly
 * at random. Your algorithm should consume a logarithmic (or constant) amount of extra memory
 * and run in time proportional to n \log nlogn in the worst case.
 *
 * https://stackoverflow.com/questions/12167630/algorithm-for-shuffling-a-linked-list-in-n-log-n-time
 * http://web.stanford.edu/class/cs9/sample_probs/ListShuffling.pdf
 *


 */

import edu.princeton.cs.algs4.StdRandom;

public class ShufflingALinkedlist {
  private static Node head;     // the head of the Linked list

    static class Node {
      int item;
      Node next;
    }

    public Node sort(Node head) {
      // no elements or just one element already sorted
      if(head == null || head.next == null) return head;
      Node fast_ptr = head;
      Node slow_ptr = head;

      while(fast_ptr.next != null && fast_ptr.next.next != null) {
        slow_ptr = slow_ptr.next;        // this is the middle
        fast_ptr = fast_ptr.next.next;
      }
      Node left = head;
      //System.out.print(" left " + left.item);
      Node right = slow_ptr.next;       // mid + 1
      //System.out.print(" right " + right.item);
      System.out.print("");
      slow_ptr.next = null;             // Separate the left and the right linked list
      left = sort(left);
      right = sort(right);
      return merge(left, right);
    }

    public Node merge(Node left, Node right){
      //System.out.println("left= " + left.item + " right= " + right.item);
      // put the sorted elements in aux Linked list
      Node aux = new Node();
      Node l = left;
      Node r = right;
      Node current = aux;
      while(l != null && r != null){
        int rand = StdRandom.uniform(2);
        if(rand == 0) {   // if rand == 0 select elements from left
          current.next = l;
          current = current.next;
          l = l.next;     // increment left
        } else {          // if the rand == 1 select element from right
          current.next = r;
          current = current.next;
          r = r.next;     // increment right
        }
      }

      if(l != null) {           // if the left side is not traverse connected to the current
        current.next = l;
      } else if(r != null) {    // if the right side is not traverse connected to the current
        current.next = r;
      }

      return aux.next;
    }

    public void insert(int item) {
      Node newNode = new Node();
      newNode.next = head;
      newNode.item = item;
      head = newNode;
      //System.out.println(item);
    }

    public void printLinkedlist() {
      Node tnode = head;
      while(tnode != null) {
        System.out.print(tnode.item + " ");
        tnode = tnode.next;
      }
    }

//    public Node middle() {
//      Node fast_ptr = head;
//      Node slow_ptr = head;
//
//      if(head != null) {
//        while(fast_ptr != null && fast_ptr.next != null){
//          fast_ptr= fast_ptr.next.next;
//          slow_ptr = slow_ptr.next;
//        }
//      }
//      return slow_ptr;
//    }

  public static void main(String[] args) {
    ShufflingALinkedlist s = new ShufflingALinkedlist();
    for (int i = 0; i <= 5; i++) {
      s.insert(i);
    }
    System.out.println("head " + head.item);
    s.sort(head);

//    int result = s.middle();
//    StdOut.println("result " + result);
    s.printLinkedlist();
  }
}
