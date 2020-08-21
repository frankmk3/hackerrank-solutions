import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

/**
 * https://www.hackerrank.com/challenges/ctci-array-left-rotation/problem
 */
public class CtciArrayLeftRotation {

    private static final Scanner scanner = new Scanner(System.in);

    // Complete the rotLeft function below.
    static int[] rotLeft(int[] a, int d) {
        int size = a.length;
        //Do not look for empty array or if the final place will be the same
        if (size == 0 || d % size == 0) {
            return a;
        }
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            int targetPos = getFinalPosition(size, d, i);
            result[targetPos] = a[i];
        }
        return result;
    }

    /**
     * Calculates the final position base on the rotation
     */
    static int getFinalPosition(int size, int rotation, int position) {
        int targetPosition = position + (size - rotation);
        return targetPosition % size;
    }

    /**
     * HackerRank provides this code.
     */
    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nd = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nd[0]);

        int d = Integer.parseInt(nd[1]);

        int[] a = new int[n];

        String[] aItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int aItem = Integer.parseInt(aItems[i]);
            a[i] = aItem;
        }

        int[] result = rotLeft(a, d);

        for (int i = 0; i < result.length; i++) {
            bufferedWriter.write(String.valueOf(result[i]));

            if (i != result.length - 1) {
                bufferedWriter.write(" ");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
