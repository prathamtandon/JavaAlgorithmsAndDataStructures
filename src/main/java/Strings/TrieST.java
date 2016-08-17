package Strings;

import Collections.Queue;

/**
 * Created by prathamt on 8/15/16.
 */
public class TrieST<Value> {

    private static int R = 256;
    private Node root;

    private static class Node {
        private Object val;
        private Node[] next = new Node[R];
    }

    public Value get(String key) {
        Node x = get(root, key, 0);
        if(x == null) return null;
        return (Value) x.val;
    }

    private Node get(Node x, String key, int d) {
        // Return value associated with key in subtrie rooted at x
        if(x == null) return null;
        if(d == key.length()) return x;
        char c = key.charAt(d);
        return get(x.next[c], key, d+1);
    }

    public void put(String key, Value val) {
        root = put(root, key, val, 0);
    }

    private Node put(Node x, String key, Value val, int d) {
        if(x == null) x = new Node();
        if(d == key.length()) { x.val = val; return x; }
        char c = key.charAt(d);
        x.next[c] = put(x.next[c], key, val, d+1);
        return x;
    }

    public Iterable<String> keysWithPrefix(String pre) {
        Queue<String> q = new Queue<String>();
        collect(get(root, pre, 0), pre, q);
        return q;
    }

    public Iterable<String> keysThatMatch(String pat) {
        Queue<String> q = new Queue<String>();
        collect(root, "", pat, q);
        return q;
    }

    public String longestPrefixOf(String s) {
        int length = search(root, s, 0, 0);
        return s.substring(0, length);
    }

    private int search(Node x, String s, int d, int max) {
        if(x == null) return max;
        if(x.val != null) max = d;
        if(d == s.length()) return max;
        char c = s.charAt(d);
        return search(x.next[c], s, d+1, max);
    }

    private void collect(Node x, String pre, String pat, Queue<String> q) {
        if(x == null) return;
        int d = pre.length();
        if(d == pat.length() && x.val != null) q.enqueue(pre);
        if(d == pat.length()) return;
        char next = pat.charAt(d);
        for(char c = 0; c < R; c++)
            if(next == '.' || next == c)
                collect(x.next[c], pre + c, pat, q);
    }

    private void collect(Node x, String pre, Queue<String> q) {
        if(x == null) return;
        if(x.val != null) q.enqueue(pre);
        for(char c = 0; c < R; c++)
            collect(x.next[c], pre + c, q);
    }

    public void delete(String key) {
        root = delete(root, key, 0);
    }

    public Node delete(Node x, String s, int d) {
        if(x == null) return x;
        if(d == s.length())
            x.val = null;
        else {
            char c = s.charAt(d);
            x.next[c] = delete(x.next[c], s, d+1);
        }
        if(x.val != null) return x;
        for(char c = 0; c < R; c++)
            if(x.next[c] != null)
                return x;

        return null;
    }
}
