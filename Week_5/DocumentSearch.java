/*
* Design an algorithm that takes a sequence of N document words and a sequence
* of M query words and find the shortest interval in which the M query words appear
* in the document in the order given.
* The length of an interval is the number of words in that interval.
 */

/*
* The main idea of the topic: Given a set of n words to be searched, and a set of m words to be searched,
* it is required to find a minimum interval among the n words to be searched, so that this interval contains
* all m words to be searched, and these m the order in which words appear is consistent with the given order.
* Solution: open a list for each different word, record the subscript position of the word in n words to be searched (in order);
* get a list of positions corresponding to m words to be searched, from each of the m lists Pick a number to form a sequence.
* The difference between the beginning and the end of the sequence is required to be the smallest, and the sequence must be an increasing sequence.
*
 */
import java.util.*;

public class DocumentSearch {
  private String[] document;
  private String[] query;

  public void search() {
    // Record the two endpoints of the shortest interval that meets the conditions
    // Setting first to -1 helps to determine whether a valid interval can be found in the end
    int first = -1, last = document.length - 1;

    //Generate the corresponding increasing subscript sequence according to each different documentWord
    Map<String, Queue<Integer>> map = new HashMap<>();
    for(int i = 0; i < document.length; i++) {
      if(!map.containsKey(document[i])) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(i);
        map.put(document[i], queue);
      } else {
        map.get(document[i]).add(i);
      }
    }

    // Extract the subscript sequence corresponding to m words to be searched
    Queue<Integer>[] listQueue = new Queue[query.length];
    for(int i= 0; i < query.length; i++) {
      listQueue[i] = map.get(query[i]);
    }

    // Search for the interval that meets the conditions
    OUTER:
    for(int i : listQueue[0]) {
      int left = i, right = i;      // Record the two endpoints of the current interval
      for(int j = 1; j < listQueue.length; j++) {
        // Select the smallest position that satisfies the conditions in each sequence each time, and remove all subscripts smaller than this position
        while(!listQueue[j].isEmpty() && listQueue[j].peek() <= right) {
          listQueue[j].poll();
        }
        if(listQueue[j].isEmpty()) {
          break OUTER;                                 // When a word corresponding to the subscript is out of the team, you can jump out of the outer loop directly
        } else {
          right = listQueue[j].peek();                // Update the right end of the current interval
        }
      }
      // Update the left and right endpoints of the shortest interval
      if(right - left < last - first) {
        first = left;
        last = right;
      }
    }

    if(first != -1) {
      System.out.println(last - first);
    } else {
      System.out.println("Not found");
    }
  }

}
