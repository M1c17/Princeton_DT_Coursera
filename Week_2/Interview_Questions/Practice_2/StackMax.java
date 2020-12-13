import java.util.Stack;

/*
Stack with max. Create a data structure that efficiently supports the stack operations (push and pop)
and also a return-the-maximum operation. Assume the elements are real numbers so that you can compare them.
*/

public class StackMax {
  Stack<Integer> stack;
  private int currentMax;

  public StackMax() {
    stack = new Stack<Integer>();
    currentMax = 0;
  }

  public void push(int item) {
    // Inserting new item in the stack
    if(stack.empty()) {
      stack.push(item);
      currentMax = item;
      return;
    }
    // if the new number > currentMax
    if(item > currentMax) {
      // to catch the max state of this item
      stack.push(2 * item - currentMax);
      currentMax = item;
    } else {
     stack.push(item);
    }
  }

  public int pop() {

    if(stack.empty()) {
      return 0;
    }
    // take the top of the stack
    int removeItem = stack.peek();
    stack.pop();
    if(removeItem > currentMax) {
      // retrieve the last max state
      currentMax = 2 * currentMax - removeItem;
    }
    return currentMax;
  }

  public int getMax() {
    if(stack.empty()) {
      System.out.println("Empty stack");
    } else {
      System.out.println("max:" + currentMax);
    }
    return currentMax;
  }
}
