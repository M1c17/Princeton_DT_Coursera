
public class stack_api4 {

    private String[] s = new String[1];
    private int N;

    public void push(String item) {
        // isFull?
        if(N == s.length) resize(2 * s.length);
        s[++N] = item;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    // halve size of array s[] when array is one-quarter
    public String pop() {
        String item = s[--N];
        if(N > 0 && N == s.length/4) resize(s.length/2);
        s[N] = null;
        return item;
    }

    public void resize(int capacity) {
        String[] copy = new String[capacity];
        for(int i = 0; i < N; i++) {
            copy[i] = s[i];
        }
        s = copy;
    }
}
