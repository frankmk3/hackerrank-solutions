package tree;

import java.util.*;
import java.io.*;

class Node {

    Node left;
    Node right;
    int data;

    Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}

/**
 * https://www.hackerrank.com/challenges/binary-search-tree-insertion/problem
 */
public class BinarySearchTreeInsertion {

    public static void preOrder(Node root) {

        if (root == null)
            return;

        System.out.print(root.data + " ");
        preOrder(root.left);
        preOrder(root.right);

    }

 /* Node is defined as :
 class Node
    int data;
    Node left;
    Node right;

    */

    public static Node insert(Node root, int data) {
        if (root == null) {
            return new Node(data);
        }
        if (root.data > data) { // insert left if the new value is lower that the root
            root.left = insert(root.left, data);
        } else {//if not insert into the right
            root.right = insert(root.right, data);
        }
        return root;//returning the root
    }

    public static void main(String[] args) {