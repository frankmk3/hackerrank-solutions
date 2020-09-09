package recursion;

import java.io.*;
import java.util.*;
// Add any extra import statements you may need here
/* we have a recursive method

from  string and start and end
 if start and end are the same so return char in start
 if not will pick the center and return center + left same algo + rigth same algo

*/

class EncryptedWords {

    // These are the tests we use to determine if the solution is correct.
    // You can add your own at the bottom, but they are otherwise not editable!
    int test_case_number = 1;

    public static void main(String[] args) {
        new EncryptedWords().run();
    }

    // Add any helper functions you may need here
  /*
   a b c

   abc, 0, 2
    center = 1


    abcd 0, 3

    center = 1
    return b + same(.., 0, 0)  + same(.., 2, 3)
     b + a +.

      2, 3
  */
    private String encryptWord(String word, int start, int end) {
        if (end < start) {
            return "";
        }
        if (start == end) {
            return String.valueOf(word.charAt(start));
        }
        int center = (end - start) / 2 + start;//0 1 2 3=  4  center = 3/2 1 ,  0 1 2   0 - 2 = 1
        StringBuilder sb = new StringBuilder();
        sb.append(word.charAt(center));
        sb.append(encryptWord(word, start, center - 1));
        sb.append(encryptWord(word, center + 1, end));
        return sb.toString();
    }

    String findEncryptedWord(String s) {
        // Write your code here
        if (s == null || s.length() == 0) {
            return s;
        }
        return encryptWord(s, 0, s.length() - 1);
    }

    void check(String expected, String output) {
        boolean result = (expected.equals(output));
        char rightTick = '\u2713';
        char wrongTick = '\u2717';
        if (result) {
            System.out.println(rightTick + " Test #" + test_case_number);
        } else {
            System.out.print(wrongTick + " Test #" + test_case_number + ": Expected ");
            printString(expected);
            System.out.print(" Your output: ");
            printString(output);
            System.out.println();
        }
        test_case_number++;
    }

    void printString(String str) {
        System.out.print("[\"" + str + "\"]");
    }

    public void run() {
        String s_1 = "abc";
        String expected_1 = "bac";
        String output_1 = findEncryptedWord(s_1);
        check(expected_1, output_1);

        String s_2 = "abcd";
        String expected_2 = "bacd";
        String output_2 = findEncryptedWord(s_2);
        check(expected_2, output_2);

        // Add your own test cases here

    }
}