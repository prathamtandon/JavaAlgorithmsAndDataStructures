package TreesAndGraphs;

/**
 * Created by prathamt on 8/23/16.
 */
public class MinimalBST {

    static class Node {
        int val;
        Node left;
        Node right;

        public Node(int val) {
            this.val = val;
        }
    }

    public static Node createMinimalBST(int lo, int hi, int[] a) {
        if(lo > hi)
            return null;
        int mid = lo + (hi - lo) / 2;
        Node r = new Node(a[mid]);
        r.left = createMinimalBST(lo, mid-1, a);
        r.right = createMinimalBST(mid+1, hi, a);
        return r;
    }
}
