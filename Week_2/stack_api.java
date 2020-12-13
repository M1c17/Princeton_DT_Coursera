public class stack_api {
    private Node first = null;

    private static class Node {
        String item;
        Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public String pop() {
        String item = first.item;
        first = first.next;
        return item;

    }

    public void push(String item) {
        Node old_first = first;
        // Create a new node for the beginning
        first  = new Node();
        first.item = item;
        first.next = old_first;
    }
}
