package TreesAndGraphs;

import java.util.HashMap;

/**
 * Created by prathamt on 8/24/16.
 */
public class PathsWithSum {
    private class Node {
        int val;
        Node left;
        Node right;
    }
    public static int countPathsWithSum(Node root, int targetSum) {
        if (root == null) return 0;
        HashMap<Integer, Integer> pathCount = new HashMap<Integer, Integer>();
        incrementHashTable(pathCount, 0, 1);
        return countPathsWithSum(root, targetSum, 0, pathCount);
    }

    private static int countPathsWithSum(Node node, int targetSum, int runningSum, HashMap<Integer, Integer> map) {
        if(node == null) return 0;

        runningSum += node.val;
        incrementHashTable(map, runningSum, 1);

        int sum = runningSum - targetSum;
        int totalPaths = map.containsKey(sum) ? map.get(sum) : 0;

        totalPaths += countPathsWithSum(node.left, targetSum, runningSum, map);
        totalPaths += countPathsWithSum(node.right, targetSum, runningSum, map);

        incrementHashTable(map, runningSum, -1);        //      avoid counting the same path again

        return totalPaths;
    }

    private static void incrementHashTable(HashMap<Integer, Integer> map, int key, int delta) {
        if(!map.containsKey(key))
            map.put(key, 0);
        map.put(key, map.get(key) + delta);
    }
}
