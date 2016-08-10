package Collections;

import java.util.Iterator;

/**
 * Created by prathamt on 8/9/16.
 */
public class Stack<Item> implements Iterable<Item> {

    private class ReverseArrayIterator implements Iterator<Item> {
        private int i = N;

        public boolean hasNext() {
            return i > 0;
        }

        public Item next() {
            return a[--i];
        }

        public void remove() {
            // NO-OP
        }
    }

    private Item[] a;
    private int N;

    private void resize(int max) {
        // Move stack of size N <= max into a new array of size max.
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    public Stack(int cap) {
        a = (Item[]) new Object[cap];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void push(Item item) {
        // Add item to top of stack
        if(N == a.length) resize(2 * a.length);
        a[N++] = item;
    }

    public Item pop() {
        Item item = a[--N];
        a[N] = null;
        if(N > 0 && N == a.length / 4) resize(a.length / 2);
        return item;
    }

}

