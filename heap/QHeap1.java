package heap;

import java.io.*;
import java.util.*;

/**
 * https://www.hackerrank.com/challenges/qheap1/problem
 */
public class QHeap1 {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        int total = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        for (int i = 0; i < total; i++) {
            int action = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            switch (action) {
                case 1:
                    priorityQueue.add(scanner.nextInt());
                    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
                    break;
                case 2:
                    priorityQueue.remove(scanner.nextInt());
                    break;
                default:
                    System.out.println(priorityQueue.peek());
                    break;
            }
        }
    }
}