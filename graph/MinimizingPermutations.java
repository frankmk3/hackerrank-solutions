package graph;


import java.util.*;

// Add any extra import statements you may need here
/*
Problem
Minimizing Permutations
In this problem, you are given an integer N, and a permutation, P of the integers from 1 to N, denoted as (a_1, a_2, ..., a_N). You want to rearrange the elements of the permutation into increasing order, repeatedly making the following operation:
Select a sub-portion of the permutation, (a_i, ..., a_j), and reverse its order.
Your goal is to compute the minimum number of such operations required to return the permutation to increasing order.
Signature
int minOperations(int[] arr)
Input
Array arr is a permutation of all integers from 1 to N, N is between 1 and 8
Output
An integer denoting the minimum number of operations required to arrange the permutation in increasing order
Example
If N = 3, and P = (3, 1, 2), we can do the following operations:
Select (1, 2) and reverse it: P = (3, 2, 1).
Select (3, 2, 1) and reverse it: P = (1, 2, 3).
output = 2
 */
/*
After a lot of time I came with this solution.

- group values in nodes, we will keep consecutive values with 1 as a difference
- then select firts node with 1 as a difference with a consecutive group. If not, pick the minor difference group.
- each time rotate and try to group again
O(N^2) time and O(N) as space

*/

class NodeSequence {

    public int first;
    public int last;

    public NodeSequence(int first, int last) {
        this.first = first;
        this.last = last;
    }
}

class MinimizingPermutations {

    // Add any helper functions you may need here

    private List<NodeSequence> generateNodeSequence(int[] arr) {
        List<NodeSequence> result = new ArrayList<>();
        if (arr == null || arr.length == 0) {
            return result;
        }
        NodeSequence node = new NodeSequence(arr[0], arr[0]);
        result.add(node);
        for (int i = 1; i < arr.length; i++) {
            if (Math.abs(node.last - arr[i]) == 1) {
                node.last = arr[i];
            } else {
                node = new NodeSequence(arr[i], arr[i]);
                result.add(node);
            }
        }
        return result;
    }


    private List<NodeSequence> compactSequence(List<NodeSequence> sequence) {
        List<NodeSequence> result = new ArrayList<>();
        if (sequence == null || sequence.isEmpty()) {
            return result;
        }

        NodeSequence node = sequence.get(0);
        result.add(node);
        for (int i = 1; i < sequence.size(); i++) {
            if (Math.abs(node.last - sequence.get(i).first) == 1) {
                node.last = sequence.get(i).last;
            } else {
                node = sequence.get(i);
                result.add(node);
            }
        }
        return result;
    }


    private void swapPosition(NodeSequence node) {
        int tmp = node.first;
        node.first = node.last;
        node.last = tmp;
    }


    private int getNodesDistance(NodeSequence node1, NodeSequence node2) {
        int distance1 = Math.min(Math.abs(node1.first - node2.first), Math.abs(node1.first - node2.last));
        int distance2 = Math.min(Math.abs(node1.last - node2.first), Math.abs(node1.last - node2.last));
        return Math.min(distance1, distance2);
    }

    private void swapNearNode(List<NodeSequence> initialNodes) {
        int initialSwapPos = 0;
        int initialDistance = 8; //max possible number
        for (int i = 0; i < initialNodes.size() - 1; i++) {
            NodeSequence nextNode = initialNodes.get(i + 1);
            NodeSequence currentNode = initialNodes.get(i);
            int distance = getNodesDistance(currentNode, nextNode);
            if (distance < initialDistance) {
                initialSwapPos = i;
                if (Math.abs(nextNode.first - nextNode.last) > 0
                        && (nextNode.last - nextNode.first < 0 ||
                        currentNode.last > nextNode.first)) {
                    initialSwapPos = i + 1;
                }
                initialDistance = distance;
            }
        }
        swapPosition(initialNodes.get(initialSwapPos));

    }

  /*
   1 2 3  = 0 permutations

   3 2 1  = 1

   2 3 1  = 2
   3 2 1 = 1   or 2 1 3  1 2 3

   3 1 2 = 2
   3 2 1  =1. or 1 3 2.  1 2 3

   3 1 2 4 = 2   2 -1 -1 0
   1 3 2 4
   1 2 3 4

   3 4 1 2 = 3    2 2  2 2
   4 3 1 2
   4 3 2 1
   1 2 3 4

   3 1 4 2
   1 3 4 2
   1 3 2 4
   1 2 3 4

   3 - 1, 1-2,   expected.  1-2, 2-3

   1 - 2, 3
   2 - 1
   3 - 1

   3
   |
   1 -- 2

   3
   |
   2 -- 1


   5 2 3 4 1

   5 4 3 2 1
   1 2 3 4 5

   5 - 2 - 3 - 4 - 1

   5 - 2, 3
   2 - 3, 5
   3 - 4, 2
   4 - 1
   1 - 4


   incorrect positions


   5 - 4
   2 - 3, 1


   5
   |
   2 -- 3 -- 4
             |
             1

   5 -- 4 -- 3 -- 2 - 1





  */

    int minOperations(int[] arr) {
        // Write your code here
        List<NodeSequence> initialNodes = generateNodeSequence(arr);
        int size = arr.length;


        int count = 0;
        while (initialNodes.size() > 1) {
            count++;
            swapNearNode(initialNodes);
            initialNodes = compactSequence(initialNodes);
        }

        return initialNodes.get(0).first == 1 ? count : count + 1;

    }


    // These are the tests we use to determine if the solution is correct.
    // You can add your own at the bottom, but they are otherwise not editable!
    int test_case_number = 1;

    void check(int expected, int output) {
        boolean result = (expected == output);
        char rightTick = '\u2713';
        char wrongTick = '\u2717';
        if (result) {
            System.out.println(rightTick + " Test #" + test_case_number);
        } else {
            System.out.print(wrongTick + " Test #" + test_case_number + ": Expected ");
            printInteger(expected);
            System.out.print(" Your output: ");
            printInteger(output);
            System.out.println();
        }
        test_case_number++;
    }

    void printInteger(int n) {
        System.out.print("[" + n + "]");
    }

    public void run() {

        int n_1 = 5;
        int[] arr_1 = {1, 2, 5, 4, 3};
        int expected_1 = 1;
        int output_1 = minOperations(arr_1);
        check(expected_1, output_1);

        int n_2 = 3;
        int[] arr_2 = {3, 1, 2};
        int expected_2 = 2;
        int output_2 = minOperations(arr_2);
        check(expected_2, output_2);

        // Add your own test cases here
        int n_3 = 5;
        int[] arr_3 = {5, 2, 3, 4, 1};
        int expected_3 = 2;
        int output_3 = minOperations(arr_3);
        check(expected_3, output_3);


        int n_4 = 9;
        int[] arr_4 = {2, 1, 4, 3, 6, 5, 7};
        int expected_4 = 3;
        int output_4 = minOperations(arr_4);
        check(expected_4, output_4);

    }

    public static void main(String[] args) {
        new MinimizingPermutations().run();
    }
}