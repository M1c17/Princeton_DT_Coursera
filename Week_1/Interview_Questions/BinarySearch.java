package Percolation;

import java.util.Arrays;

/*
* Question 1:
* 3-SUM in quadratic time. Design an algorithm for the 3-SUM problem that takes time proportional to n^2
* in the worst case. You may assume that you can sort the n integers in time proportional to n^2
* or better.
*/

public class BinarySearch {

    public BinarySearch() {

    }

    public static int[] sort(int[] a) {
        int i, j, key, temp;
        for(i = 1; i < a.length; i++) {
            key = a[i];
            j = i - 1;
            while(j >= 0 && key < a[j]) {
                // swap
                temp = a[j];
                a[j] = a[j+1];
                a[j+1] = temp;
                j--;
            }
        }
        return a;
    }

    public static int binary(int[] a, int key) {

        int lo, hi, mid;
        lo = 0;
        hi = a.length;
        mid = lo +(hi - lo) /2;

        while(lo <= hi) {
            if(key < a[mid]) hi = mid - 1;
            else if(key > a[mid]) lo = mid + 1;
            else return mid;
        }

        return -1;
    }


    public static void main(String[] args) {
        int [] a = new int[]{3, 5, 7, 8, 0};
        int key = 3;


        sort(a);
        binary(a, key);
        System.out.println(Arrays.toString(a));
    }


}
