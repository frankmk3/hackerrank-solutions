import java.io.*;
import java.util.*;

/**
 * Problem:
 * Reverse to Make Equal
 * Given two arrays A and B of length N, determine if there is a way to make A equal to B by reversing any subarrays from array B any number of times.
 * Signature
 * bool areTheyEqual(int[] arr_a, int[] arr_b)
 * Input
 * All integers in array are in the range [0, 1,000,000,000].
 * Output
 * Return true if B can be made equal to A, return false otherwise.
 * Example
 * A = [1, 2, 3, 4]
 * B = [1, 4, 3, 2]
 * output = true
 * After reversing the subarray of B from indices 1 to 3, array B will equal array A.
 */
// Add any extra import statements you may need here

/*

assuming that is only possible for each substring once is a different aproach, but id any sequence con be reverse
the solution is check if all the elements exist in the same amount.
Preconditions to check, null or different length
Using the firts array create a map key value(occurence)
the second will decrease the existence, if no present or 0 false
O(N) space and time

If a sub-string can be move only once then the logic is different
check the null and size
starting from 0 to N-1
check if the values in position are equals and move to next if equals
If not then in a while check for string a.equals(b)// check i< length and !a.equals(b)
a is each element in order a+=array_a[i];
b is reverser string.      b = array_b[i] + b;
if we get to the end and the value not match, false
If we get to the and and is equals each character(or sequences), then true
*/

class ReverseToMakeEqual {

    // These are the tests we use to determine if the solution is correct.
    // You can add your own at the bottom, but they are otherwise not editable!
    int test_case_number = 1;

    public static void main(String[] args) {
        new ReverseToMakeEqual().run();
    }

    // Add any helper functions you may need here
    private Map<Integer, Integer> arrayToMap(int[] array) {
        Map<Integer, Integer> store = new HashMap<>();
        for (int value : array) {
            Integer count = store.getOrDefault(value, 0);
            store.put(value, count + 1);
        }
        return store;
    }

    boolean areTheyEqual(int[] array_a, int[] array_b) {
        // Write your code here
        if (array_a != null &&
                array_b != null &&
                array_a.length == array_b.length) {

            Map<Integer, Integer> store = arrayToMap(array_a);

            //if any value is missing then false.
            for (int value : array_b) {
                Integer count = store.getOrDefault(value, 0);
                if (count > 0) {
                    store.put(value, count - 1);
                } else {
                    return false;
                }
            }
            return true;

        }
        return false;
    }

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
        int[] array_a_1 = {1, 2, 3, 4};
        int[] array_b_1 = {1, 4, 3, 2};
        boolean expected_1 = true;
        boolean output_1 = areTheyEqual(array_a_1, array_b_1);
        check(expected_1, output_1);

        int[] array_a_2 = {1, 2, 3, 4};
        int[] array_b_2 = {1, 4, 3, 3};
        boolean expected_2 = false;
        boolean output_2 = areTheyEqual(array_a_2, array_b_2);
        check(expected_2, output_2);
        // Add your own test cases here

    }
}