import java.io.*;
import java.util.*;
// Add any extra import statements you may need here

/*
Pair Sums
Given a list of n integers arr[0..(n-1)], determine the number of different pairs of elements within it which sum to k.
If an integer appears in the list multiple times, each copy is considered to be different; that is, two pairs are considered different if one pair includes at least one array index which the other doesn't, even if they include the same values.
Signature
int numberOfWays(int[] arr, int k)
Input
n is in the range [1, 100,000].
Each value arr[i] is in the range [1, 1,000,000,000].
k is in the range [1, 1,000,000,000].
Output
Return the number of different pairs of elements which sum to k.
Example 1
n = 5
k = 6
arr = [1, 2, 3, 4, 3]
output = 2
The valid pairs are 2+4 and 3+3.
Example 2
n = 5
k = 6
arr = [1, 5, 3, 3, 3]
output = 4
There's one valid pair 1+5, and three different valid pairs 3+3 (the 3rd and 4th elements, 3rd and 5th elements, and 4th and 5th elements).



First approach
Brute force
Double for from i and j= i+1 compare with the input k
-minor improve (skip  i if value in i > k)
count matching sum arr[i]+ arr[j] = k
O(N^2) time and O(1) space

Using HashMap
adding number in 0 to start
for each remain value check if the complement vale is in the HashMap
( hash contains exist k-arr[i] then count +1, add the value to the key)
O(N) space and  time
Keep count of elements to be sure that all the unique values match 

*/

class PairSums {

    // Add any helper functions you may need here


    int numberOfWays(int[] arr, int k) {
        // Write your code here
        //If the array is shorter than 2 there are no result
        int result = 0;
        if (arr.length > 1) {
            Map<Integer, Integer> store = new HashMap<>();
            store.put(arr[0], 1);

            for (int i = 1; i < arr.length; i++) {
                Integer value = arr[i];
                int diff = k - value;
                if (store.containsKey(diff)) {
                    result += store.get(diff);
                }
                Integer prevCount = store.getOrDefault(value, 0);
                store.put(value, prevCount + 1);//this set keeps uniques values ,if the value is present does not affects.
            }
        }

        return result;

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
        int k_1 = 6;
        int[] arr_1 = {1, 2, 3, 4, 3};
        int expected_1 = 2;
        int output_1 = numberOfWays(arr_1, k_1);
        check(expected_1, output_1);

        int k_2 = 6;
        int[] arr_2 = {1, 5, 3, 3, 3, 3};
        int expected_2 = 7;
        int output_2 = numberOfWays(arr_2, k_2);
        check(expected_2, output_2);

        // Add your own test cases here

    }

    public static void main(String[] args) {
        new PairSums().run();
    }
}