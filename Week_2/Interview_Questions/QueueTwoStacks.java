import java.util.Stack;

/*
Question 1
Queue with two stacks. Implement a queue with two stacks so that each queue operations takes
 a constant amortized number of stack operations.
*/
public class QueueTwoStacks {
  Stack<Integer> stack1;
  Stack<Integer> stack2;
  private int N;

  public QueueTwoStacks() {
    stack1 = new Stack();
    stack2 = new Stack();
    N = 0;
  }

  public boolean isEmpty(){
    if (stack1.empty()) {
      return true;
    } else {
      return false;
    }
  }

  // push to stack1
  public void enqueue(int item) {
    stack1.push(item);
    N++;
  }

  // pop from top of stack after been pop from the other stack
  public int dequeue(){
    int result;
    if(isEmpty()) {
      return 0;
    } else {
      while(!stack1.empty()) {
        stack2.push(stack1.pop());
      }
      result = stack2.pop();
      while(!stack2.empty()) {
        stack1.push(stack2.pop());
      }
      return result;
    }
  }

  public int size(){
    return N;
  }

  // Get the front element
  public int peek(){
    if(isEmpty()) {
      return 0;
    } else {
      while(!stack1.empty()) {
        stack2.push(stack1.pop());
      }
      int result;
      result = stack2.pop();
      while(!stack2.empty()) {
        stack1.push(stack2.pop());
      }
      return result;
    }
  }
}
