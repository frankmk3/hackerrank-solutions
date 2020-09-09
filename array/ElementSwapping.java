import java.util.*;
// Add any extra import statements you may need here

/**
 * Problem
 * Element Swapping
 * Given a sequence of n integers arr, determine the lexicographically smallest sequence which may be obtained from it after performing at most k element swaps, each involving a pair of consecutive elements in the sequence.
 * Note: A list x is lexicographically smaller than a different equal-length list y if and only if, for the earliest index at which the two lists differ, x's element at that index is smaller than y's element at that index.
 * Signature
 * int[] findMinArray(int[] arr, int k)
 * Input
 * n is in the range [1, 1000].
 * Each element of arr is in the range [1, 1,000,000].
 * k is in the range [1, 1000].
 * Output
 * Return an array of n integers output, the lexicographically smallest sequence achievable after at most k swaps.
 * Example 1
 * n = 3
 * k = 2
 * arr = [5, 3, 1]
 * output = [1, 5, 3]
 * We can swap the 2nd and 3rd elements, followed by the 1st and 2nd elements, to end up with the sequence [1, 5, 3]. This is the lexicographically smallest sequence achievable after at most 2 swaps.
 * Example 2
 * n = 5
 * k = 3
 * arr = [8, 9, 11, 2, 1]
 * output = [2, 8, 9, 11, 1]
 * We can swap [11, 2], followed by [9, 2], then [8, 2]
 */

class ElementSwapping {

    // Add any helper functions you may need here
  /*
  If we can swap in the same array

  1- Find the min element from 0 to k or length(if is less than k)
  2- do the swap and decrease each swap in the k count
  3 repeat the stepst from 1 until k count is 0

   start = 0 position = 6
   5, 3, 2, 6, 1   ==== 4
   min = 1
   pos = 4
   i = 5


  position = 2, start = 1
  1, 5, 3, 2, 6
  start + position =3 or <5
   min = 2
   pos = 3
   i = 3

  */
    private int findMin(int[] arr, int start, int position) {
        if (position == 0 || arr == null || arr.length == 0) {
            return position;// maybe we can throw some exceptions here
        }
        int min = arr[start];
        int minPosition = start;
        for (int i = start + 1; i <= start + position && i < arr.length; i++) {
            if (arr[i] < min) {//find position of the first min
                min = arr[i];
                minPosition = i;
            }
        }
        return minPosition;
    }

    /*
    position = 4 and start 0
     5, 3, 2, 6, 1

     1, 5, 3, 2, 6
    */
    private void swapFromPosition(int[] arr, int start, int position) {
        if (position == 0 ||
                arr == null ||
                arr.length == 0 ||
                position >= arr.length
        ) {
            return;// maybe we can throw some exceptions here
        }
        //this can be modified using System.arraycopy
        int tmp = arr[position];
        // 4 - 0
        System.arraycopy(arr, start, arr, start + 1, position - start);//  5 4 3 2 1 0
        arr[start] = tmp;
    /*for(int i = position; i > start; i--){
      int tmp = arr[i];
      arr[i] = arr[i-1];
      arr[i-1] = tmp;
    }*/
    }

    /*
    k = 6
    5, 3, 2, 6, 1

    pos = 4

    k = 6- 4= 2, pos = 1
    1, 5, 3, 2, 6

    min =

    */
    int[] findMinArray(int[] arr, int start, int k) {
        // Write your code here
        if (k == 0 || arr == null || arr.length == 0 || start >= arr.length) {
            return arr;
        }
        int pos = findMin(arr, start, k);
        swapFromPosition(arr, start, pos);
        return findMinArray(arr, start + 1, k - (pos - start));

    }

    int[] findMinArray(int[] arr, int k) {
        // Write your code here
        return findMinArray(arr, 0, k);

    }


    // These are the tests we use to determine if the solution is correct.
    // You can add your own at the bottom, but they are otherwise not editable!
    int test_case_number = 1;

    void check(int[] expected, int[] output) {
        int expected_size = expected.length;
        int output_size = output.length;
        boolean result = true;
        if (expected_size != output_size) {
            result = false;
        }
        for (int i = 0; i < Math.min(expected_size, output_size); i++) {
            result &= (output[i] == expected[i]);
        }
        char rightTick = '\u2713';
        char wrongTick = '\u2717';
        if (result) {
            System.out.println(rightTick + " Test #" + test_case_number);
        } else {
            System.out.print(wrongTick + " Test #" + test_case_number + ": Expected ");
            printIntegerArray(expected);
            System.out.print(" Your output: ");
            printIntegerArray(output);
            System.out.println();
        }
        test_case_number++;
    }

    void printIntegerArray(int[] arr) {
        int len = arr.length;
        System.out.print("[");
        for (int i = 0; i < len; i++) {
            if (i != 0) {
                System.out.print(", ");
            }
            System.out.print(arr[i]);
        }
        System.out.print("]");
    }

    public void run() {
        int n_1 = 3, k_1 = 2;
        int[] arr_1 = {5, 3, 1};
        int[] expected_1 = {1, 5, 3};
        int[] output_1 = findMinArray(arr_1, k_1);
        check(expected_1, output_1);

        int n_2 = 5, k_2 = 3;
        int[] arr_2 = {8, 9, 11, 2, 1};
        int[] expected_2 = {2, 8, 9, 11, 1};
        int[] output_2 = findMinArray(arr_2, k_2);
        check(expected_2, output_2);

        // Add your own test cases here

        n_2 = 5;
        k_2 = 10000000;
        arr_2 = new int[]{11, 9, 8, 2, 1};
        expected_2 = new int[]{1, 2, 8, 9, 11};
        output_2 = findMinArray(arr_2, k_2);
        check(expected_2, output_2);

        n_2 = 5;
        k_2 = 4;
        arr_2 = new int[]{11, 9, 8, 2, 1};
        expected_2 = new int[]{1, 11, 9, 8, 2};
        output_2 = findMinArray(arr_2, k_2);
        check(expected_2, output_2);

    }

    public static void main(String[] args) {
        new ElementSwapping().run();
    }
}