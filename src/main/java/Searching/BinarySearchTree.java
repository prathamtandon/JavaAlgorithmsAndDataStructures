package Searching;

/**
 * Created by prathamt on 8/11/16.
 */
public class BinarySearchTree<Key extends Comparable<Key>, Value> {

    private Node root;

    private class Node {
        private Key key;            //  key
        private Value val;          //  associate value
        private Node left, right;   //  links to subtrees
        private int N;              //  # of nodes in subtree rooted here

        public Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    public int size() {
        return size(root);
    }

    public int size(Node x) {
        if(x == null) return 0;
        return x.N;
    }

    public Value get(Key k) {
        return get(root, k);
    }

    private Value get(Node x, Key k) {
        if(x == null) return null;

        int cmp = x.key.compareTo(k);
        if(cmp == 0) return x.val;
        else if(cmp < 0) return get(x.right, k);
        else return get(x.left, k);
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if(cmp < 0)
            x.left = put(x.left, key, val);
        else if(cmp > 0)
            x.right = put(x.right, key, val);
        else
            x.val = val;
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Key select(int i) {
        return select(root, i);
    }

    private Key select(Node x, int i) {
        if(x == null) return null;
        int t = x.left.N;
        if(t > i) return select(x.left, i);
        else if(t < i) return select(x.right, i-t-1);
        return x.key;
    }

    public int rank(Key k) {
        return rank(root, k);
    }

    private int rank(Node x, Key k) {
        if(x == null) return 0;
        int cmp = x.key.compareTo(k);
        if(cmp < 0)
            return 1 + size(x.left) + rank(x.right, k);
        else if(cmp > 0)
            return rank(x.left, k);
        return size(x.left);
    }

    public Key min() {
        return min(root).key;
    }

    private Node min(Node x) {
        if(x.left == null) return x;
        return min(x.left);
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if(x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.N = 1 + size(x.left) + size(x.right);
        return x;
    }

    public void delete(Key k) {
        root = delete(root, k);
    }

    private Node delete(Node x, Key k) {
        if(x == null) return null;
        int cmp = x.key.compareTo(k);
        if(cmp < 0)
            x.right = delete(x.right, k);
        else if(cmp > 0)
            x.left = delete(x.left, k);
        else {
            if (x.right == null) return x.left;
            else if(x.left == null) return x.right;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }
}
