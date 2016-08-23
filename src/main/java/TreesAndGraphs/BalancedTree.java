package TreesAndGraphs;

/**
 * Created by prathamt on 8/22/16.
 */
public class BalancedTree {

    private class Node {
        int val;
        Node left;
        Node right;
    }

    public static int checkHeight(Node root) {
        if(root == null)
            return 0;
        int leftHeight = checkHeight(root.left);
        if(leftHeight == -1)
            return -1;
        int rightHeight = checkHeight(root.right);
        if(rightHeight == -1)
            return -1;
        int heightDiff = leftHeight - rightHeight;
        if(Math.abs(heightDiff) > 1)
            return -1;
        else
            return 1 + Math.max(leftHeight, rightHeight);
    }

    public static boolean isBalanced(Node root) {
        return checkHeight(root) != -1;
    }
}
