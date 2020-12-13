package Practice;

/*
Question 2
Union-find with specific canonical element.
Add a method find() to the union-find data type so that find(i) returns the largest element in the connected component containing i.
The operations, union(), connected(), and find() should all take logarithmic time or better.
For example, if one of the connected components is {1,2,6,9}, then the find() method should return 9 for each of the four elements in the connected components.
*/

public class FindMax {
  private int[] id;
  private int[] size;
  private int[] largest;
  private int count;

  public FindMax(int n) {
    id = new int[n];
    size = new int[n];
    largest = new int[n];
    count = n;

    for(int i = 0; i < n; i++){
      id[i] = i;
      size[i] = 1;
      largest[i] = i;
    }
  }

  public int count() {
    return count;
  }

  // return the grandparent of every node
  public int root(int p) {
    while(p != id[p]) {
      id[p] = id[id[p]];
      p = id[p];
    }
    return p;
  }

  public boolean connected(int p, int q) {
    return root(p) == root(q);
  }

  public void union(int p, int q) {
    int rootp = root(p);
    int rootq = root(q);

    // make smaller root point to larger one
    if(size[rootp] < size[rootq]) {
      size[rootp] = rootq;
      size[rootq] += size[rootp];
      if(largest[rootq] < largest[rootp])
        largest[rootq] = largest[rootp];
    }
    else {
      size[rootq] = rootp;
      size[rootp] += size[rootq];
      if(largest[rootp] < largest[rootq])
        largest[rootp] = largest[rootq];
    }
    count--;
  }

  public int find(int p) {
    int root = root(p);
    return largest[root];
  }



}
