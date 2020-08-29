package graph;


import java.io.*;
import java.util.*;

/**
 * Breadth First Search: Shortest Reach
 *
 * https://www.hackerrank.com/challenges/bfsshortreach/problem
 */
public class BFSShortReach {

    private static final Scanner scanner = new Scanner(System.in);
    //Store all posible path from one node
    private static Map<Integer, Set<Integer>> mainStore = new HashMap<>();
    //Improve performance adding a cache mechanism
    private static Map<String, Integer> cachePathDistance = new HashMap<>();
    //Store visited node for each position
    private static Set<Integer> visited = new HashSet<>();
    //The path size
    private static final int PATH_SIZE = 6;

    //Generate a path unique key
    private static String getPathKey(int source, int target) {
        return source + "-" + target;
    }

    // Complete the bfs function below.

    /*Example
   1

    2 3 4

    5 6

    7 8
    9

6, 6,6,12,12,18,18,24, -1
1->2, 3, 4
2->1, 5, 6
3->1
4->1
5->2, 7, 8
6->2
7->5,9
8->5
9->7
*/

    static int[] bfs(int n, int m, int[][] edges, int s) {
        mainStore = new HashMap<>();
        cachePathDistance = new HashMap<>();
        for (int[] edge : edges) {
            //undirected graph both element are reachable
            storeNodeVectors(edge[0], edge[1]);
            storeNodeVectors(edge[1], edge[0]);
        }
        int[] response = new int[n - 1];
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (i != s) {
                visited = new HashSet<>();
                response[count++] = getNodeDistanceBFS(s, i);
            }
        }

        return response;

    }


    private static void storeNodeVectors(int source, int target) {
        Set<Integer> connectedNodes = mainStore.getOrDefault(source, new HashSet<>());
        connectedNodes.add(target);
        mainStore.put(source, connectedNodes);
    }

    /**
     * Check if there is a direct node access
     */
    private static boolean existDirectNodeAccess(Integer startNode, int endNode) {
        return mainStore.containsKey(startNode) &&
                mainStore.get(startNode).contains(endNode);

    }

    /**
     * Calculate the distance from one node to other
     * -1 is no path, each steps means 6
     */
    private static int getNodeDistanceBFS(int startNode, int endNode) {
        if (mainStore.containsKey(startNode) && mainStore.containsKey(endNode)) {
            String pathKey = getPathKey(startNode, endNode);
            if (cachePathDistance.containsKey(pathKey)) {
                return cachePathDistance.get(pathKey);
            }
            if (existDirectNodeAccess(startNode, endNode)) {
                cachePathDistance.put(pathKey, PATH_SIZE);
                return PATH_SIZE;
            }
            //If there is no direct access then search all the adjacent nodes
            int result = getNodeDistanceBFS(mainStore.get(startNode), endNode);
            result = result < 0 ? result : result + PATH_SIZE;
            cachePathDistance.put(pathKey, result);
            return result;
        }
        return -1;
    }

    private static int getNodeDistanceBFS(Set<Integer> nodeSet, int endNode) {
        if (nodeSet != null && !nodeSet.isEmpty()) {
            //Store all the adjacent nodes if no path
            Set<Integer> pendingElements = new HashSet<>();
            for (Integer node : nodeSet) {
                if (!visited.contains(node)) {
                    String pathKey = getPathKey(node, endNode);
                    if (cachePathDistance.containsKey(pathKey)) {
                        return cachePathDistance.get(pathKey);
                    }
                    visited.add(node);
                    if (existDirectNodeAccess(node, endNode)) {
                        cachePathDistance.put(pathKey, PATH_SIZE);
                        return PATH_SIZE;
                    } else if (mainStore.containsKey(node)) {
                        pendingElements.addAll(mainStore.get(node));
                    }

                }
            }
            int minDistance = getNodeDistanceBFS(pendingElements, endNode);
            return minDistance == -1 ? minDistance : PATH_SIZE + minDistance;
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String[] nm = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nm[0]);

            int m = Integer.parseInt(nm[1]);

            int[][] edges = new int[m][2];

            for (int i = 0; i < m; i++) {
                String[] edgesRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int j = 0; j < 2; j++) {
                    int edgesItem = Integer.parseInt(edgesRowItems[j]);
                    edges[i][j] = edgesItem;
                }
            }

            int s = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] result = bfs(n, m, edges, s);

            for (int i = 0; i < result.length; i++) {
                bufferedWriter.write(String.valueOf(result[i]));

                if (i != result.length - 1) {
                    bufferedWriter.write(" ");
                }
            }

            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}