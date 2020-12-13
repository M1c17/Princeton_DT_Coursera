public class stack_api3 {
    private String[] s = new String[1];
    private int N;

    public void push(String item) {
        if(N == s.length) resize(2 * s.length);
        s[++N] = item;
    }

    public String pop() {
        String item = s[--N];
        s[N] = null;
        return item;
    }


    private void resize(int capacity) {
        String[] copy = new String[capacity];
        for(int i = 0; i < N; i++) {
            copy[i] = s[i];
        }
        // reset our instance variable to this new larger array copy
        s = copy;
    }



}
