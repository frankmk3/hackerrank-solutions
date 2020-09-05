import java.util.*;
// Add any extra import statements you may need here

/*
for each char in the input
check if the char is in these range A-Z, a-z or 0-9
rotate using the diference of the rotation factor
B B-A = 1 + factor %(Z-A)
factor 2
B B-A = 1 + 2 %(Z-A) = 3
result = A + 3

*/

class RotationalCipher {

    // Add any helper functions you may need here
    private char rotateCharacter(char inputChar, char min, char max, int rotationFactor) {
        int pos = (inputChar - min + rotationFactor) % (max - min + 1);
        return (char) (min + pos);
    }

    String rotationalCipher(String input, int rotationFactor) {
        // Write your code here
        if (input != null) {
            char[] result = new char[input.length()];
            for (int i = 0; i < input.length(); i++) {
                char currentChar = input.charAt(i);
                if (currentChar >= 'A' && currentChar <= 'Z') {
                    currentChar = rotateCharacter(currentChar, 'A', 'Z', rotationFactor);
                } else if (currentChar >= 'a' && currentChar <= 'z') {
                    currentChar = rotateCharacter(currentChar, 'a', 'z', rotationFactor);
                } else if (currentChar >= '0' && currentChar <= '9') {
                    currentChar = rotateCharacter(currentChar, '0', '9', rotationFactor);
                }
                result[i] = currentChar;
            }
            return new String(result);
        }
        return null;
    }


    // These are the tests we use to determine if the solution is correct.
    // You can add your own at the bottom, but they are otherwise not editable!
    int test_case_number = 1;

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
        String input_1 = "All-convoYs-9-be:Alert1.";
        int rotationFactor_1 = 4;
        String expected_1 = "Epp-gsrzsCw-3-fi:Epivx5.";
        String output_1 = rotationalCipher(input_1, rotationFactor_1);
        check(expected_1, output_1);

        String input_2 = "abcdZXYzxy-999.@";
        int rotationFactor_2 = 200;
        String expected_2 = "stuvRPQrpq-999.@";
        String output_2 = rotationalCipher(input_2, rotationFactor_2);
        check(expected_2, output_2);

        // Add your own test cases here

    }

    public static void main(String[] args) {
        new RotationalCipher().run();
    }
}