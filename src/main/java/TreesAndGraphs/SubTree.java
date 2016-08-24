package TreesAndGraphs;

/**
 * Created by prathamt on 8/24/16.
 */
public class SubTree {

    private class Node {
        int val;
        Node left;
        Node right;
    }
    boolean containsTree(Node t1, Node t2) {
        if(t2 == null)
            return true;
        return subTree(t1, t2);
    }

    boolean subTree(Node t1, Node t2) {
        if(t1 == null)
            return false;
        if(t1.val == t2.val)
            return matchTree(t1, t2);
        return (subTree(t1.left, t2) || subTree(t1.right, t2));
    }

    boolean matchTree(Node t1, Node t2) {
        if(t2 == null && t1 == null)
            return true;

        if(t1 == null || t2 == null)
            return false;

        if(t1.val != t2.val)
            return false;

        return (matchTree(t1.left, t2.left) && matchTree(t1.right, t2.right));
    }
}
