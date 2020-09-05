import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

/**
 * Sherlock and Anagrams
 * <p>
 * https://www.hackerrank.com/challenges/sherlock-and-anagrams/problem
 */
public class SherlockAndAnagrams {

    private static final Scanner scanner = new Scanner(System.in);

    static int sherlockAndAnagrams(String s) {
        Map<String, Integer> values = new HashMap<>();
        int length = s.length();
        for (int i = 1; i < length; i++) {
            //4    6   4 *3/2
            //3    3   3*2/2
            //2    1   2*1/2
            for (int j = 0; j < length - i + 1; j++) {
                String textOriginal = s.substring(j, j + i);
                char[] charts = textOriginal.toCharArray();
                Arrays.sort(charts);
                String text = new String(charts);
                values.put(text, values.getOrDefault(text, 0) + 1);
            }
        }
        //the formula to know the combination is n * (n-1) / 2
        return values.
                values().
                stream().
                map(value -> (value * (value - 1)) / 2).
                mapToInt(Integer::intValue).sum();

    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String s = scanner.nextLine();

            int result = sherlockAndAnagrams(s);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}

