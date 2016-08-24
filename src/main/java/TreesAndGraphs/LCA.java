package TreesAndGraphs;

/**
 * Created by prathamt on 8/24/16.
 */
public class LCA {

    public static class Result {
        public Node node;
        public boolean isAncestor;
        public Result(Node node, boolean isAncestor) {
            this.node = node;
            this.isAncestor = isAncestor;
        }
    }

    private class Node {
        int val;
        Node left;
        Node right;
    }

    Result commonAncestorHelper(Node root, Node p, Node q) {
        if(root == null)
            return new Result(null, false);
        if(p == root && q == root)
            return new Result(root, true);
        Result rl = commonAncestorHelper(root.left, p , q);
        if(rl.isAncestor)
            return rl;
        Result rr = commonAncestorHelper(root.right, p , q);
        if(rr.isAncestor)
            return rr;

        if(rl.node != null && rr.node != null)
            return new Result(root, true);
        if(p == root || q == root) {
            boolean isAncestor = rl.node != null || rr.node != null;
            return new Result(root, isAncestor);
        }
        return new Result(rl.node == null ? rr.node : rl.node, false);
    }

    Node commonAncestor(Node root, Node p, Node q) {
        Result r = commonAncestorHelper(root, p , q);
        if(r.isAncestor)
            return r.node;
        return null;
    }
}
