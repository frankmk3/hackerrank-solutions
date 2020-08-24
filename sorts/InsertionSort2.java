package sorts;

import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/insertionsort2/problem
 */
public class InsertionSort2 {

    private static final Scanner scanner = new Scanner(System.in);

    // Complete the insertionSort2 function below.
    static void insertionSort2(int n, int[] arr) {
        for (int i = 1; i < n; i++) {
            insertionSort(arr, i, arr[i]);
            printArray(arr);
        }
    }

    /**
     * Inserting from the end until we get to the position
     */
    private static void insertionSort(int[] arr, int newSize, int value) {
        int index = newSize;
        while (index > 0) {
            if (value <= arr[index - 1]) {
                arr[index] = arr[index - 1];
                index--;
            } else {
                break;
            }

        }
    }

    private static void printArray(int[] array) {
        for (int value : array) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        insertionSort2(n, arr);

        scanner.close();
    }
}
