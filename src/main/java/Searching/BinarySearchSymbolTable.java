package Searching;

import Collections.Queue;

/**
 * Created by prathamt on 8/11/16.
 */
public class BinarySearchSymbolTable<Key extends Comparable<Key>, Value> {

    private Key[] keys;
    private Value[] values;
    private int N;

    public BinarySearchSymbolTable(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        values = (Value[]) new Object[capacity];
    }

    public int size() {
        return N;
    }

    public Value get(Key key) {
        if(isEmpty()) return null;
        int i = rank(key);
        if(i < N && keys[i].compareTo(key) == 0)
            return values[i];
        return null;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int rank(Key key) {
        return rank(key, 0, N-1);
    }

    private int rank(Key key, int lo, int hi) {
        if(hi < lo) return lo;
        int mid = lo + (hi - lo) / 2;
        int compare = keys[mid].compareTo(key);
        if(compare == 0) return mid;
        else if(compare < 0)
            return rank(key, mid+1, hi);
        else
            return rank(key, lo, mid-1);
    }

    public void put(Key key, Value val) {
        // Search for key. Update value if found; grow table if new.
        int i = rank(key);
        // key is already present in ST
        if(i < N && keys[i].compareTo(key) == 0) {
            values[i] = val;
            return;
        }
        for (int j = N; j > i; j--) {
            keys[j] = keys[j-1];
            values[j] = values[j-1];
        }
        keys[i] = key;
        values[i] = val;
        N++;
    }

    public Key min() {
        return keys[0];
    }

    public Key max() {
        return keys[N-1];
    }

    public Key select(int k) {
        return keys[k];
    }

    public Key ceiling(Key k) {
        int i = rank(k);
        return select(i);
    }

    public Key floor(Key k) {
        int i = rank(k);
        if(i < N && keys[i].compareTo(k) == 0)
            return k;
        return select(i-1);
    }

    public Key delete(Key k) {
        int i = rank(k);
        if(!(i < N && keys[i].compareTo(k) == 0))
            return null;
        for (int j = i; j < N-1; j++) {
            keys[j] = keys[j+1];
        }
        N--;
        return k;
    }

    public boolean contains(Key k) {
        int i = rank(k);
        return i < N && keys[i].compareTo(k) == 0;
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> q = new Queue<Key>();
        for (int i = rank(lo); i < rank(hi); i++) {
            q.enqueue(keys[i]);
        }
        if(contains(hi))
            q.enqueue(keys[rank(hi)]);
        return q;
    }
}
