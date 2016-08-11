package Collections;

import java.util.Iterator;

/**
 * Created by prathamt on 8/9/16.
 */
public class Queue<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int N;

    private class Node {
        Item item;
        Node next;
    }

    public boolean isEmpty() { return N == 0; }
    public int size() { return N; }

    public Iterator<Item> iterator() {
        return null;
    }

    public void enqueue(Item item) {
        Node oldLast = last;
        last = new Node();
        last.item = item;
        if(isEmpty())
            first = last;
        else
            oldLast.next = last;
        N++;
    }

    public Item dequeue() {
        Item item = first.item;
        first = first.next;
        N--;
        if(isEmpty())
            last = null;
        return item;
    }
}
