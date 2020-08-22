package stack;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

/**
 * https://www.hackerrank.com/challenges/balanced-brackets/problem
 */
public class BalancedBrackets {

    private static final String OPEN_OPERATORS = "({[";
    private static final String CLOSE_OPERATORS = ")}]";
    private static final Scanner scanner = new Scanner(System.in);

    public static boolean bracketsMatch(String sentence) {
        Stack<Character> operators = new Stack<>();
        for (int i = 0; i < sentence.length(); i++) {
            char operator = sentence.charAt(i);
            //If the operator is an open operator then we put in the stack
            if (isOpenGroupOperator(operator)) {
                operators.add(operator);
            } else if (!operators.isEmpty() &&
                    isCloserOperatorOf(operator, operators.peek())
            ) {
                //if is a close operator and is the one that match with the latest open operator
                //the operator is removed from the stack
                operators.pop();
            } else {//if the close operator not match with the latest open operator is not balanced
                return false;
            }
        }
        //the structure is balanced if there is no operators left
        return operators.isEmpty();
    }


    private static boolean isOpenGroupOperator(char value) {
        return OPEN_OPERATORS.indexOf(value) != -1;
    }

    private static boolean isCloserOperatorOf(char closeOperator, char openOperator) {
        return OPEN_OPERATORS.indexOf(openOperator) == CLOSE_OPERATORS.indexOf(closeOperator);
    }

    // Complete the isBalanced function below.
    static String isBalanced(String sentence) {

        return bracketsMatch(sentence) ? "YES" : "NO";
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            String s = scanner.nextLine();

            String result = isBalanced(s);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
