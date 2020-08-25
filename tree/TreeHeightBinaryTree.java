package tree;

import java.util.*;
import java.io.*;

/**
 * https://www.hackerrank.com/challenges/tree-height-of-a-binary-tree/problem
 */
public class TreeHeightBinaryTree {

    /*
    class Node
        int data;
        Node left;
        Node right;


        null I will assume 0.   if   if has no left or right
        then return max of left or right +1
    */
    public static int height(Node root) {
        // Write your code here.
        if (root == null || (root.left == null && root.right == null)) {
            return 0;
        }
        return Math.max(height(root.left), height(root.right)) + 1;
    }

    public static Node insert(Node root, int data) {
        if (root == null) {
            return new Node(data);
        } else {
            Node cur;
            if (data <= root.data) {
                cur = insert(root.left, data);
                root.left = cur;
            } else {
                cur = insert(root.right, data);
                root.right = cur;
            }
            return root;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        Node root = null;
        while (t-- > 0) {
            int data = scan.nextInt();
            root = insert(root, data);
        }
        scan.close();
        int height = height(root);
        System.out.println(height);
    }
}

