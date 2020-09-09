package heap;

import java.util.*;
// Add any extra import statements you may need here


class MagicalCandyBags {

    // Add any helper functions you may need here
  /*
  with a max heap we can have the max in the top position
  Iterate and remove from position 0

  2, 1, 7, 4, 2

  7 , 4, 1, 2, 2
  4, 1, 2, 2, 7
  3, 2, 1, 1,
  */

    int maxCandies(int[] arr, int k) {
        // Write your code here
        if (arr == null || arr.length == 0) {
            return 0;//we can throw some exceptions id null.
        }
        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> b.compareTo(a));
        for (int value : arr) {
            heap.add(value);
        }
        int total = 0;
        for (int i = 0; i < k; i++) {
            int value = heap.poll();
            total += value;
            heap.add(value / 2);
        }
        return total;
    }


    // These are the tests we use to determine if the solution is correct.
    // You can add your own at the bottom, but they are otherwise not editable!
    int test_case_number = 1;

    void check(int expected, int output) {
        boolean result = (expected == output);
        char rightTick = '\u2713';
        char wrongTick = '\u2717';
        if (result) {
            System.out.println(rightTick + " Test #" + test_case_number);
        } else {
            System.out.print(wrongTick + " Test #" + test_case_number + ": Expected ");
            printInteger(expected);
            System.out.print(" Your output: ");
            printInteger(output);
            System.out.println();
        }
        test_case_number++;
    }

    void printInteger(int n) {
        System.out.print("[" + n + "]");
    }

    public void run() {
        int n_1 = 5, k_1 = 3;
        int[] arr_1 = {2, 1, 7, 4, 2};
        int expected_1 = 14;
        int output_1 = maxCandies(arr_1, k_1);
        check(expected_1, output_1);

        int n_2 = 9, k_2 = 3;
        int[] arr_2 = {19, 78, 76, 72, 48, 8, 24, 74, 29};
        int expected_2 = 228;
        int output_2 = maxCandies(arr_2, k_2);
        check(expected_2, output_2);

        // Add your own test cases here
        int value_4 = 100;
        int n_4 = 10, k_4 = value_4;
        int[] arr_4 = {value_4, value_4, value_4, value_4, value_4, value_4, value_4, value_4, value_4, value_4};
        int expected_4 = value_4 * n_4;
        int prevValue = value_4;
        for (int i = 1; i < n_4; i++) {
            prevValue = prevValue / 2;
            expected_4 += n_4 * prevValue;
        }
        int output_4 = maxCandies(arr_4, k_4);
        check(expected_4, output_4);
    }

    public static void main(String[] args) {
        new MagicalCandyBags().run();
    }
}