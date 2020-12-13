/*
* Question 3
* Generalized queue. Design a generalized queue data type that supports all of the
*  following operations in logarithmic time (or better) in the worst case.
* - Create an empty data structure.
* - Append an item to the end of the queue.
* - Remove an item from the front of the queue.
* - Return the ith item in the queue.
* - Remove the ith item from the queue.
* Hint: create a red-black BST where the keys are integers and the values are the items such
* that the ith largest key in the red-black BST corresponds to the ith item in the queue.
* To find the i-th largest element is to find the node with the i-th largest sequence number in the tree,
* which is the same as the select() implementation in the symbol table implemented by the previous BST
*/

import edu.princeton.cs.algs4.RedBlackBST;

public class GeneralizedQueue<Item> {

  //  Create an empty data structure.
  private int index;
  private RedBlackBST<Integer, Item> store;

  public GeneralizedQueue() {
    index = 0;
    store = new RedBlackBST<Integer, Item>();
  }

  //  Append an item to the end of the queue.
  public void append(Item item) {
    store.put(index++, item);
  }

  //  Remove an item from the front of the queue.
  public void removeFront() {
    store.deleteMin();
  }

  //  Return the ith item in the queue.
  public Item get(int i) {
    int key = store.rank(i);
    return store.get(key);
  }

  //  Remove the ith item from the queue.
  public void remove(int i){
    int key = store.rank(i);
    store.delete(key);
  }

}
