import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

/**
 * Minimum Swaps 2
 * https://www.hackerrank.com/challenges/minimum-swaps-2/problem
 */
public class MinimumSwaps2 {

    private static final Scanner scanner = new Scanner(System.in);

    // Complete the minimumSwaps function below.
    static int minimumSwaps(int[] arr) {
        int i = 0;
        int count = 0;
        int temp;
        int n = arr.length;
        while (i < n) {
            //We are looking the right position for the current element
            //if the current is not the right one we swap the element in the target position(n-1)
            //this is possible because the sequence are consecutive number starting in 1
            if (arr[i] != i + 1) {
                temp = arr[i];
                arr[i] = arr[temp - 1];
                arr[temp - 1] = temp;
                count++;
            } else {
                i++;
            }
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int res = minimumSwaps(arr);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
