import java.util.Stack;

public class QueueTwoStacks2 {
  Stack<Integer> enqueue;
  Stack<Integer> dequeue;
  private int N;

  public QueueTwoStacks2() {
    enqueue = new Stack();
    dequeue = new Stack();
    N = 0;
  }

  public boolean isEmpty(){
    if (enqueue.empty()) {
      return true;
    } else {
      return false;
    }
  }

  // push to stack1
  public void enqueue(int item) {
    enqueue.push(item);
    N++;
  }

  // pop from top of stack after been pop from the other stack
  public int dequeue(){
    if(dequeue.empty()) {
      // Transfer the elements from enq to deq
      while(!enqueue.empty()) {
        dequeue.push(enqueue.pop());
      }
    }
    return dequeue.pop();
    //throw new NoSuchElementException("Cannot pop empty stack");
  }

  public int size(){
    return N;
  }

  // Get the front element
  public int peek(){
    if(isEmpty()) {
      return 0;
    } else {
      return dequeue();
    }
  }
}

