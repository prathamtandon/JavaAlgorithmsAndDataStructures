package TreesAndGraphs;

/**
 * Created by prathamt on 8/23/16.
 */
public class BSTChecker {

    private class Node {
        int val;
        Node left;
        Node right;
    }

    public static boolean checkBST(Node n) {
        return checkBST(n, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static boolean checkBST(Node n, int min, int max) {
        if(n == null) return true;

        if(n.val < min || n.val > max)
            return false;

        if(!checkBST(n.left, min, n.val) || !checkBST(n.right, n.val, max))
            return false;

        return true;
    }
}
