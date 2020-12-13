public class QuickUnionUF 
{
    // id is a tree
    private int[] id;
    
    public QuickUnionUF(int N) 
    {
        id = new int[N];
        for (int i = 0; i < N; i++)
            id[i] = i;
    }
    
    public int root(int i) 
    {
        // Chase parent pointer until reach root
        while (i != id[i]) 
            i = id[i];
        return i;
    }

    public boolean connected(int p, int q)
    {
        // Check if p and q have the same root
        return root(p) == root(q);
    }

    public void union(int p, int q) 
    {
        int i = id[p];
        int j = id[q];
        // to merge components containing p and q
        // set the id of p's root to the id of q's root
        id[i] = j;
    }

}
