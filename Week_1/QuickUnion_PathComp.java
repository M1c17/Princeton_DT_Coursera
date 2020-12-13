public class QuickUnion_PathComp {
    private int[] id;
    private int[] sz;

    public QuickUnion_PathComp(int N)
    {
        id = new int[N];
        for(int i = 0; i < N; i++)
            id[i] = i;
    }

    public int root(int i)
    {
        while(i != id[i]) {
            // Make every other node in path point to its grandparent
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }

    public boolean connect(int p, int q)
    {
        return root(p) == root(q);
    }

    public void union(int p, int q)
    {
        int i = id[p];
        int j = id[q];

        if(i == j) return;
        if(sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i]; 
        } else {
            id[j] = i;
            sz[i] += sz[j];
        }
    }
}
