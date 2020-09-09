package sorts;

import java.io.*;
import java.util.*;
// Add any extra import statements you may need here
/*
problem
Balanced Split
Given an array of integers (which may include repeated integers), determine if there's a way to split the array into two subarrays A and B such that the sum of the integers in both arrays is the same, and all of the integers in A are strictly smaller than all of the integers in B.
Note: Strictly smaller denotes that every integer in A must be less than, and not equal to, every integer in B.
Signature
bool balancedSplitExists(int[] arr)
Input
All integers in array are in the range [0, 1,000,000,000].
Output
Return true if such a split is possible, and false otherwise.
Example 1
arr = [1, 5, 7, 1]
output = true
We can split the array into A = [1, 1, 5] and B = [7].
Example 2
arr = [12, 7, 6, 7, 6]
output = false
We can't split the array into A = [6, 6, 7] and B = [7, 12] since this doesn't satisfy the requirement that all integers in A are smaller than all integers in B.
 */
/*
Ideas, sort the array, get the total, find the half, and check if from the end we can get same value and the last position is greater than the first in the big array
sort is like O(n log(n)) time and O(i) space
*/

class BalancedSplit {

    // Add any helper functions you may need here


    boolean balancedSplitExists(int[] arr) {
        // Write your code here
        if (arr == null || arr.length < 3) {
            return false;
        }
        Arrays.sort(arr);
        int sum = 0;
        for (int val : arr) {
            sum += val;
        }//14
        if (sum % 2 == 1) {//imposible to get equals integer sum for odd values
            return false;
        }
        int partialSum = 0;
        for (int i = arr.length - 1; i > 0; i--) {
            partialSum += arr[i];
            if (partialSum == sum / 2) {
                return arr[i] > arr[i - 1];
            }
            if (partialSum > sum / 2) {
                return false;
            }
        }
        return false;

    }


    // These are the tests we use to determine if the solution is correct.
    // You can add your own at the bottom, but they are otherwise not editable!
    int test_case_number = 1;

    void check(boolean expected, boolean output) {
        boolean result = (expected == output);
        char rightTick = '\u2713';
        char wrongTick = '\u2717';
        if (result) {
            System.out.println(rightTick + " Test #" + test_case_number);
        } else {
            System.out.print(wrongTick + " Test #" + test_case_number + ": Expected ");
            System.out.print(expected);
            System.out.print(" Your output: ");
            System.out.print(output);
            System.out.println();
        }
        test_case_number++;
    }

    void printString(String str) {
        System.out.print("[" + str + "]");
    }

    public void run() {
        int arr_1[] = {2, 1, 2, 5};
        boolean expected_1 = true;
        boolean output_1 = balancedSplitExists(arr_1);
        check(expected_1, output_1);

        int arr_2[] = {3, 6, 3, 4, 4};
        boolean expected_2 = false;
        boolean output_2 = balancedSplitExists(arr_2);
        check(expected_2, output_2);

        // Add your own test cases here

    }

    public static void main(String[] args) {
        new BalancedSplit().run();
    }
}