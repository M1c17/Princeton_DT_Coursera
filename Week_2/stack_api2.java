public class stack_api2 {

    private String[] s;
    private int N = 0;

    //Constructor cheat
    public stack_api2(int capacity) {
        s = new String[ capacity ];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public String pop() {
        if(isEmpty()) {
            System.out.println("The stack is Empty\n");
        }
        String item = s[--N];
        // This is to avoid loitering
        s[N] = null;
        return item;
    }

    public void push(String item) {
        s[N++] = item;
    }

}
