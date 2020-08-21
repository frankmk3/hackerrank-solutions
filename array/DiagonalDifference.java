import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

/**
 * https://www.hackerrank.com/challenges/diagonal-difference/problem
 */
class Result {

    /*
     * Complete the 'diagonalDifference' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts 2D_INTEGER_ARRAY arr as parameter.
     */

    public static int diagonalDifference(List<List<Integer>> arr) {
        // Write your code here
        int lastPosition = arr.size() - 1;
        int center = lastPosition / 2;
        int result = 0;
        for (int posX = 0; posX <= center; posX++) {
            int posY = lastPosition - posX;
            if (posX != posY) {
            result += extractValue(arr, posX, posX) + extractValue(arr, posY, posY);
            result -= extractValue(arr, posX, posY) + extractValue(arr, posY, posX);
            }

        }
        return Math.abs(result);
    }

    private static int extractValue(List<List<Integer>> arr, int posX, int posY) {
        return arr.get(posX).get(posY);
    }
}

/**
 * HackerRank provides this code.
 */
public class DiagonalDifference {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> arr = new ArrayList<>();

        IntStream.range(0, n).forEach(i -> {
            try {
                arr.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int result = Result.diagonalDifference(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
