/*
*Web tracking. Suppose that you are tracking nn web sites and mm users and you want to support the following API:
*
* - User visits a website.
* - How many times has a given user visited a given site?
*
* What data structure or data structures would you use?
* Symbol table that keep track the key-value pair
* User  = key
* website = value
* count visit_number_of_websites++
*
*
 */

public class Web_tracking<Key, Value> {
  public Node root;

  public class Node {
    private Key key;
    private Value value;
    private Node left, right;

    public Node(Key key, Value value) {
      this.key = key;
      this.value = value;
    }
  }


}
