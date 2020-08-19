import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Problem https://www.hackerrank.com/challenges/designer-pdf-viewer/problem
 */
public class DesignerPdfViewer {

    private static final Scanner scanner = new Scanner(System.in);

    // Complete the designerPdfViewer function below.
    static int designerPdfViewer(int[] h, String word) {
        int maxHeight = 0;
        for (char letter : word.toCharArray()) {
            int position = letter - 'a';
            if (h[position] > maxHeight) {
                maxHeight = h[position];
            }
        }
        return maxHeight * word.length();
    }

    /**
     * HackerRank provides this code.
     */
    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int[] h = new int[26];

        String[] hItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < 26; i++) {
            int hItem = Integer.parseInt(hItems[i]);
            h[i] = hItem;
        }

        String word = scanner.nextLine();

        int result = designerPdfViewer(h, word);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

