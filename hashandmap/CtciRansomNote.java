package hashandmap;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

/**
 * Hash Tables: Ransom Note
 * https://www.hackerrank.com/challenges/ctci-ransom-note/problem
 */
public class CtciRansomNote {

    private static final Scanner scanner = new Scanner(System.in);

    // Complete the checkMagazine function below.
    static void checkMagazine(String[] magazine, String[] note) {
        Map<String, Integer> index = extractWords(magazine);
        boolean complete = true;
        for (String word : note) {
            if (!removeFromIndex(index, word)) {
                complete = false;
                break;
            }
        }
        System.out.println(complete ? "Yes" : "No");
    }

    private static boolean removeFromIndex(Map<String, Integer> index, String word) {
        int count = index.getOrDefault(word, 0);
        if (count > 0) {
            index.put(word, count - 1);
            return true;
        }
        return false;
    }

    private static Map<String, Integer> extractWords(String[] magazine) {
        Map<String, Integer> index = new HashMap<>();
        for (String word : magazine) {
            index.put(word, index.getOrDefault(word, 0) + 1);
        }
        return index;
    }

    public static void main(String[] args) {
        String[] mn = scanner.nextLine().split(" ");

        int m = Integer.parseInt(mn[0]);

        int n = Integer.parseInt(mn[1]);

        String[] magazine = new String[m];

        String[] magazineItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < m; i++) {
            String magazineItem = magazineItems[i];
            magazine[i] = magazineItem;
        }

        String[] note = new String[n];

        String[] noteItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            String noteItem = noteItems[i];
            note[i] = noteItem;
        }

        checkMagazine(magazine, note);

        scanner.close();
    }
}
