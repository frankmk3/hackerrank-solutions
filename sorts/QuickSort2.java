package sorts;

import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/quicksort2/problem
 */

public class QuickSort2 {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] values = new int[t];
        for (int tItr = 0; tItr < t; tItr++) {
            int m = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            values[tItr] = m;
        }
        sort(values);

    }

    private static void sort(int[] array) {
        quickSort(array, array.length);
    }

    private static void quickSort(int[] array, int rightEnd) {
        if (rightEnd > 1) {
            int value = array[0];
            int[] left = new int[rightEnd];
            int leftCount = 0;
            int rightCount = 0;
            int[] right = new int[rightEnd];
            for (int i = 1; i < rightEnd; i++) {
                if (value > array[i]) {
                    left[leftCount++] = array[i];
                } else {
                    right[rightCount++] = array[i];
                }
            }
            quickSort(left, leftCount);
            quickSort(right, rightCount);

            System.arraycopy(left, 0, array, 0, leftCount);
            array[leftCount] = value;
            System.arraycopy(right, 0, array, leftCount + 1, rightCount);
            printArray(array, rightEnd);
        }
    }


    private static void printArray(int[] array, int end) {
        for (int i = 0; i < end; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();

    }

}
