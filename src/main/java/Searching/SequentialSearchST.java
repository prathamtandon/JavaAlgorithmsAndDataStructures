package Searching;

/**
 * Created by prathamt on 8/12/16.
 */
public class SequentialSearchST<Key, Value> {

    private Node first;         // first node in the linked list

    private class Node {
        //  linked list node
        Key key;
        Value val;
        Node next;
        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public Value get(Key key) {
        for(Node x = first; x != null; x = x.next)
            if(x.key.equals(key))
                return x.val;
        return null;
    }

    public void put(Key key, Value val) {
        // Search for key. Update value if found; grow table if new
        for (Node x = first; x != null; x = x.next)
            if(x.key.equals(key)) {
                x.val = val;
                return;
            }
        first = new Node(key, val, first);
    }
}
