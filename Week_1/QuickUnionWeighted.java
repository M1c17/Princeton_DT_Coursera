public class QuickUnionWeighted {

    private int[] id;
    //extra array to count number of objects in the tree rooted at i
    private int[] sz;

    public QuickUnionWeighted(int N) 
    {
       id = new int[N];

       for(int i = 0; i < N; i++)
        id[i] = i;
    }

    public int root(int i)
    {
        while(i != id[i]) i = id[i];
        return i;
    }

    public boolean connect(int p, int q)
    {
        // is root of p equal to root of q
        return root(p) == root(q);
        
    }
 
    public void union(int p, int q) 
    {
        int i = root(p);
        int j = root(q);

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
