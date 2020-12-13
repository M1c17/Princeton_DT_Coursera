package Practice;/*
* Question 3
* Successor with delete. Given a set of nn integers S = \{ 0, 1, ... , n-1 \}S={0,1,...,n−1} and a sequence of requests of the following form:
* Remove xx from SS
* Find the successor of xx: the smallest yy in SS such that y \ge xy≥x.
* design a data type so that all operations (except construction) take logarithmic time or better in the worst case.
*/

public class QuickUnion_succesor {

    private int[] id;
    private int[] sz;
    private int[] actualList;
    private int N;

    public QuickUnion_succesor(int N)
    {
        this.N = N;
        id = new int[N];
        sz = new int[N];
        actualList = new int[N];

        for(int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = i;
            actualList[i] = i;
        }
    }

    // returns the root or parent of the index element
    public int root(int i)
    {
        while(i != id[i]) i = id[i];
        return i;
    }

    public boolean connected(int p, int q)
    {
        return root(p) == root(q);
    }

    // weighted quick union
    public void union(int p, int q)
    {
        int pRoot = root(p);
        int qRoot = root(q);

        if(pRoot == qRoot) return;
        if(sz[pRoot] < sz[qRoot]) {
            id[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        } else {
            id[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
            actualList[pRoot] = qRoot+1;
        }

    }

    // Removing means -> we are actually saying union(x, x+1)
    public void remove(int x)
    {
        union(x, x+1);
    }

    public int successor(int x) {
        return actualList[(root(x+1))];
    }
}
