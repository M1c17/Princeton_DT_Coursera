package Percolation;

import java.util.ArrayList;
import java.util.List;

public class Convert2Dto1D {


    public static void convert(int[][] test) {
        List<Integer> temp_list = new ArrayList<Integer>();
        for(int i = 0; i < test.length; i++) {
            for(int j = 0; j < test[i].length; j++)
                temp_list.add(test[i][j]);
        }
        int[] onearray = new int[temp_list.size()];
        for(int i = 0; i < onearray.length; i++) {
            onearray[ i ] = temp_list.get(i);
            System.out.println(onearray[ i ]);
        }
    }

    public static void main(String[] args) {

        int test[][] = {{1,2,4}, {3,8,9}};
        convert(test);
    }

}
