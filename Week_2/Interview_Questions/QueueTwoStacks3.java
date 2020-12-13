import java.util.Stack;

public class QueueTwoStacks3 {
  Stack<Integer> stack1;
  Stack<Integer> stack2;

  public QueueTwoStacks3() {
    stack1 = new Stack<Integer>();
    stack2 = new Stack<Integer>();
  }

  public void transferAtoB() {
    while(!stack1.empty()) {
      stack2.push(stack1.pop());
    }
  }

  public void push(int item) {
    stack1.push(item);
  }

  public int pop() {
    if(stack2.empty()) {
      transferAtoB();
    }
    return stack2.pop();
  }

  public boolean empty() {
    return stack1.empty() && stack2.empty();
  }

  public int peek() {
    if(stack2.empty()) {
      transferAtoB();
    }
    return stack2.peek();
  }
}
