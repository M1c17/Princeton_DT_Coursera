package Percolation;
import java.util.Arrays;

public class Egg_droping {
    /*
    Given a number of story-floors from 1-N &&
    Given a number of eggs from 1 to N
    What is T?
    T = number of experiments after an egg start breaking.
     */

    public static int Experiment(int N, int k) {
        int[] floors = new int[N + 1];
        //int num_of_eggs = k;
        int Trials = 1;
        // Initialize boolean value 1 in range 1 to N floor
        //Arrays.fill(floors, 1, floors.length, 1);
        for(int j = 1; j < floors.length; j++) {
            floors[j] = 1;
        }
        // Create temp array to store max.no of floors that can be covered given the limitations
        int[] temp = new int[N + 1];

        // Value #of floors < floor[#eggs]
        // dynamic table -> Triangle of pascal
        while(floors[N] < k) {
            for(int i = 1; i < N + 1; i++) {
                // formula x + y + 1 ->  the reason for the 1 is because we are going to be dropping for the subproblem
                temp[i] = floors[i-1] + floors[i] + 1;
            }

            // copy into array floor the values of array temp
            floors = Arrays.copyOf(temp, temp.length);
            Trials += 1;
        }
        return Trials;
    }

    public static void main(String args[]) {
        int N = 2;
        int k = 1;
        int result = Experiment(N, k);

        System.out.println("Number of experiments:" + result);
    }

}
