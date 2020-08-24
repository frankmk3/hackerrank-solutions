package list;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * https://www.hackerrank.com/challenges/missing-numbers/problem
 */
public class MissingNumbers {

    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Conform key value map using how many time the value is present
     */
    private static Map<Integer, Integer> countElements(int[] array) {
        Map<Integer, Integer> valuesCount = new HashMap<>();
        for (int element : array) {
            valuesCount.putIfAbsent(element, 0);
            valuesCount.put(element, valuesCount.get(element) + 1);
        }
        return valuesCount;
    }

    // Complete the missingNumbers function below.
    static int[] missingNumbers(int[] arr, int[] brr) {
        //Count the values in the missing value array
        Map<Integer, Integer> missingValuesMap = countElements(arr);
        //Store all the values(Not repeated and in order)
        Set<Integer> resultSet = new TreeSet<>();
        for (int element : brr) {
            //if the current value is in the missing map and the count is > 0, decrease the current element
            if (missingValuesMap.containsKey(element) && missingValuesMap.get(element) > 0) {
                missingValuesMap.put(element, missingValuesMap.get(element) - 1);
            } else {
                resultSet.add(element);
            }
        }
        int[] response = new int[resultSet.size()];
        int count = 0;
        for (int value : resultSet) {
            response[count++] = value;
        }
        return response;
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

        int m = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] brr = new int[m];

        String[] brrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < m; i++) {
            int brrItem = Integer.parseInt(brrItems[i]);
            brr[i] = brrItem;
        }

        int[] result = missingNumbers(arr, brr);

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
