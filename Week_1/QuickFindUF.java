public class QuickFindUF
{
    // Integer array id[] of size N
    private int[] id;

    public QuickFindUF(int N)
    {
        // N array accesses
        id = new int[N];
        // Set id of each object to itself
        for (int i = 0; i < N; i++)
            id[i] = i;
    }
    // return false or true
    public boolean connected(int p, int q)
    {
        // compare the value of the index
        System.out.println(id[p]);
        System.out.println(id[q]);
        return id[p] == id[q];
    }

    public void union(int p, int q)
    {
        int pid = id[p];
        int qid = id[q];

        for (int i = 0; i < id.length; i++)
            if(id[i] == pid) id[i] = qid;
    }
    
}