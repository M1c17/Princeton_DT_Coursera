package Percolation;

import java.util.Arrays;

public class Search_in_bitonicArray {

    public Search_in_bitonicArray() {

    }

    public static int search(int[] nums, int key) {
        int lo, hi, mid;
        lo = 0;
        hi = nums.length -1;

        while(lo <= hi) {
            mid = (lo + hi) / 2;
            if(nums[mid] == key) {
                return mid;
            }
            // Sorted left to mid
            if(nums[lo] <= nums[mid]) {
                if(key >= nums[lo] && key < nums[mid]) {
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            }
            // Sorted mid to right
            else {
                if(key <= nums[hi] && key > nums[mid]) {
                     lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String args[]) {
        int[] a = new int[]{11, 12, 15, 18, 2, 5, 6, 8};
        int key = 11;

        int result = search(a, key);
        System.out.println(Arrays.toString(a));
        System.out.println(result);
        int[] b= new int[]{1,2,3};

        System.out.println(Arrays.toString(b));
    }

}
