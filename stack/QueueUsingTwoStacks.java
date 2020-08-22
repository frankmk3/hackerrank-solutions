package stack;

import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.Stack;

/**
 * https://www.hackerrank.com/challenges/queue-using-two-stacks/problem
 */
public class QueueUsingTwoStacks {

    private static final Scanner scanner = new Scanner(System.in);
    private static final StackQueue stackQueue = new StackQueue();

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        int total = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < total; i++) {
            int action = scanner.nextInt();

            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            switch (action) {
                case 1:
                    stackQueue.push(scanner.nextInt());
                    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
                    break;
                case 2:
                    stackQueue.pop();
                    break;
                default:
                    System.out.println(stackQueue.peek());
                    break;
            }
        }
    }
}

class StackQueue {

    //Store incoming data
    private Stack<Integer> principal;
    //Keep the old value on the top
    private Stack<Integer> secondary;

    public StackQueue() {
        principal = new Stack<>();
        secondary = new Stack<>();
    }

    /**
     * Put data into the principal Stack only if the secondary Stack is empty(the store is empty)
     */
    public void push(int value) {
        if (!secondary.isEmpty()) {
            principal.push(value);
        } else {
            secondary.push(value);
        }
    }

    /**
     * Remove the element at the front
     *
     * @throws EmptyStackException if this stack is empty.
     */
    public int pop() {
        //oldest element is in the secondary stack
        int result = secondary.pop();
        //if the secondary stack is empty then move the elements from the principal and will put the values in the right order
        if (secondary.isEmpty()) {
            while (!principal.isEmpty()) {
                secondary.push(principal.pop());
            }
        }
        return result;
    }

    /**
     * Get the element at the front
     *
     * @throws EmptyStackException if this stack is empty.
     */
    public int peek() {
        return secondary.peek();
    }
}