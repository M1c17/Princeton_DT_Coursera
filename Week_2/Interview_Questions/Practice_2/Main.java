public class Main {

  public static void main(String[] args) {
    StackMax2 s2 = new StackMax2();
//    StackMax s = new StackMax();
    s2.push(3);
    s2.push(5);
    s2.getMax();
    s2.push(7);
    s2.push(19);
    s2.getMax();
    s2.pop();
    s2.getMax();
    s2.pop();
    s2.getMax();
    s2.pop();
    s2.getMax();
    s2.pop();
    s2.getMax();
  }
}
