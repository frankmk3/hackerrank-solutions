import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

/**
 * https://www.hackerrank.com/challenges/2d-array/problem
 */
public class TwoDArray {

    public static final int MIN_VALUE = -9 * 7;
    private static final Scanner scanner = new Scanner(System.in);


    // Complete the hourglassSum function below.
    static int hourglassSum(int[][] arr) {
        int max = MIN_VALUE;
        for (int i = 1; i < arr.length - 1; i++) {
            for (int j = 1; j < arr[i].length - 1; j++) {
                int hourglassSum = hourglassSum(arr, i, j);
                max = Math.max(max, hourglassSum);
            }
        }
        return max;
    }

    /**
     * Base on this structure
     * 0,0  1,0  2,0
     *      1,1
     * 0,2  1,2. 2,2
     * The response is based in these index
     * x-1, y-1    x-1, y   x-1, y+1
     *              x,y
     * x+1, y-1    x+1, y   x+1, y+1
     */
    private static int hourglassSum(int[][] arr, int x, int y) {
        if (arr.length > x - 2 && x > 0 &&
                arr[0].length > y - 2 && y > 0) {
            return arr[x][y] +
                    arr[x - 1][y - 1] + arr[x - 1][y] + arr[x - 1][y + 1] +
                    arr[x + 1][y - 1] + arr[x + 1][y] + arr[x + 1][y + 1];
        }
        return MIN_VALUE;
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int[][] arr = new int[6][6];

        for (int i = 0; i < 6; i++) {
            String[] arrRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 6; j++) {
                int arrItem = Integer.parseInt(arrRowItems[j]);
                arr[i][j] = arrItem;
            }
        }

        int result = hourglassSum(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
