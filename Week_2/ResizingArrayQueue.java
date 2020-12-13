
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ResizingArrayQueue<Item> implements Iterable<Item> {

    private static final int INIT_CAPACITY = 8;
    // Use array q[] to store items in queue
    private Item[] q;                   // queue elements
    private int first;                  // index of the first element of the array
    private int last;                   // index of last element of the array
    private int N;                      // number of elements on queue

    // Initialize an empty queue
    private ResizingArrayQueue() {
        q = (Item[]) new Object[INIT_CAPACITY];
        first = 0;
        last = 0;
        N = 0;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    // return the number of items in the queue
    public int size() {
        return N;
    }


    public void resize(int capacity) {
        assert capacity >= N;
        Item[] copy = (Item[]) new Object[capacity];
        for(int i = 0; i < N; i++) {
            copy[i] = q[(first + i) % q.length];
        }
        q = copy;
        // Update head and tail modulo the capacity
        first = 0;
        last = N;
    }

    // add new item at q[tail]
    public void enqueue(Item item) {
        // isFull?
        if(N == q.length) resize(2 * q.length);
        q[last++] = item;
        if(last == q.length) last = 0;
        N++;

    }

    // remove item from q[head]
    public Item dequeue() {
        // isEmpty?
        if(isEmpty()) throw new NoSuchElementException();
        Item item = q[first];
        q[first] = null;
        N--;
        first++;
        if(first == q.length) first = 0;
        if(N > 0 && N == q.length) resize(q.length/2);
        return item;
    }

    // return the item least recently added to this queue
    public Item peek() {
        if(isEmpty()) throw new NoSuchElementException();
        return q[first];
    }

    @Override
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    // an iterator doesn't implement remove() since it's optional
    private class ArrayIterator implements Iterator<Item> {

        private int i = 0;

        @Override
        public boolean hasNext() {
            return i < N;
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Item next() {
            if(!hasNext()) throw new NoSuchElementException();
            Item item = q[(i + first) % q.length];
            i++;
            return item;
        }
    }

    public static void main(String[] args) {
        ResizingArrayQueue<String> queue = new ResizingArrayQueue<String>();
        while(!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if(!item.equals("-")) queue.enqueue(item);
            else if(!queue.isEmpty()) StdOut.print(queue.dequeue());
        }
        StdOut.println("(" + queue.size() + " left on queue)");
    }
}

