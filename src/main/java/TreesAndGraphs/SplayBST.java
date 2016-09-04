package TreesAndGraphs;

/**
 * Created by prathamt on 9/4/16.
 */
public class SplayBST<Key extends Comparable<Key>, Value> {

    private Node root;

    private class Node {
        private Key key;
        private Value value;
        private Node left, right;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    // return value associated with given key
    // return null if key not found
    public Value get(Key key) {
        root = splay(root, key);
        int cmp = root.key.compareTo(key);
        if (cmp == 0) return root.value;
        return null;
    }

    // Splay tree insertion
    public void put(Key key, Value value) {
        if(root == null) {
            root = new Node(key, value);
            return;
        }

        root = splay(root, key);

        int cmp = key.compareTo(root.key);

        // Insert new node at root
        if(cmp < 0) {
            Node n = new Node(key, value);
            n.left = root.left;
            n.right = root;
            root.left = null;
            root = n;
        }

        else if(cmp > 0) {
            Node n = new Node(key, value);
            n.right = root.right;
            n.left = root;
            root.right = null;
            root = n;
        }

        else {
            root.value = value;
        }
    }

    // Splay tree deletion
    // This splays the key, then does a slightly modified Hibbard deletion on
    // the root (if it is the node to be deleted; if it is not, the key was not
    // in the tree). The modification is that rather than swapping the root (call
    // it node A) with its successor, it's successor (call it node B) is moved to
    // the root position by splaying for the deletion key in A's right subtree. Finally,
    // A's right child is made the new root's right child.
    public void remove(Key key) {
        if(root == null) return;

        root = splay(root, key);

        int cmp = key.compareTo(root.key);

        if(cmp == 0) {
            if(root.left == null) {
                root = root.right;
            }
            else {
                Node x = root.right;
                root = root.left;
                splay(root, key);
                root.right = x;
            }
        }
    }

    // Splay tree function
    //  splay key in the tree rooted at Node h. If a node with that key exists,
    //  it is splayed to the root of the tree. It it does not, the last node
    //  along the search path for the key is splayed to the root.
    private Node splay(Node h, Key key) {
        if(h == null) return null;

        int cmp1 = key.compareTo(h.key);

        if(cmp1 < 0) {
            // key not in tree, so we are done
            if(h.left == null)
                return h;
            int cmp2 = key.compareTo(h.left.key);
            if(cmp2 < 0) {
                h.left.left = splay(h.left.left, key);
                h = rotateRight(h);
            }
            else if(cmp2 > 0) {
                h.left.right = splay(h.left.right, key);
                if (h.left.right != null)
                    h.left = rotateLeft(h.left);
            }

            if(h.left == null) return h;
            else                return rotateRight(h);
        }
        else if(cmp1 > 0) {
            // key not in tree, so we are done
            if(h.right == null)
                return h;
            int cmp2 = key.compareTo(h.right.key);
            if(cmp2 < 0) {
                h.right.left = splay(h.right.left, key);
                if(h.right.left != null)
                    h.right = rotateRight(h.right);
            }
            else if(cmp2 > 0) {
                h.right.right = splay(h.right.right, key);
                h = rotateLeft(h);
            }

            if(h.right == null)     return h;
            else                    return rotateLeft(h);
        }
        else return h;
    }

    public int height() { return height(root); }
    private int height(Node x) {
        if(x == null) return -1;
        return 1 + Math.max(height(x.left), height(x.right));
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if(x == null) return 0;
        return 1 + size(x.left) + size(x.right);
    }

    // right rotate
    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        return x;
    }

    // left rotate
    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        return x;
    }
}
