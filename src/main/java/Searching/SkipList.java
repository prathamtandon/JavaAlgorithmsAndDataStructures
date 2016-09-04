package Searching;

import java.util.Random;

/**
 * Created by prathamt on 9/4/16.
 */
public class SkipList {
    public SkipListEntry head;      //  First element of the top level
    public SkipListEntry tail;      //  Last element of the top level

    public int n;                   //  number of entries in the skip list

    public int h;                   //  number of levels in the skip list
    public Random r;                //  coin toss

    public SkipList() {
        SkipListEntry p1, p2;

        p1 = new SkipListEntry(SkipListEntry.negInf, null);
        p2 = new SkipListEntry(SkipListEntry.posInf, null);

        // Link the -oo and +oo objects together
        p1.right = p2;
        p2.left = p1;

        // Initialize head and tail
        head = p1;
        tail = p2;

        // Other initializations
        h = 0;
        n = 0;
        r = new Random();
    }

    /*
    findEntry(k): find the largest key x <= k, on the LOWEST level of the Skip list
     */
    public SkipListEntry findEntry(String key) {
        SkipListEntry p;

        // Start at "head"
        p = head;

        while (true) {
            // Search right until you find a larger entry
            /**
             * Eg. k = 34
             * 10 -> 20 -> 30 -> 40
             *              |
             *              p must stop at 30
             *              p.right.key equals 40
             */
            while((p.right.key.compareTo(key) <= 0) && !p.right.key.equals(SkipListEntry.posInf))
                p = p.right;

            // Go down one level if we can
            if (p.down != null)
                p = p.down;
            else
                break;
        }

        return p;
    }

    // Returns the value associated with a key
    public Integer get(String key) {
        SkipListEntry result = findEntry(key);
        if(result.key.equals(key))
            return result.value;
        return null;
    }

    // Add a new top layer to the skip list
    private void createNewTopLayer() {
        SkipListEntry p1, p2;

        // Make the -oo and +oo entries
        p1 = new SkipListEntry(SkipListEntry.negInf, null);
        p2 = new SkipListEntry(SkipListEntry.posInf, null);

        p1.right = p2;
        p2.left = p1;

        p1.down = head;
        head.up = p1;

        p2.down = tail;
        tail.up = p2;

        head = p1;
        tail = p2;

        h++;
    }

    // Insert a new item into skip list
    public Integer put(String k, Integer v) {
        SkipListEntry p, q;
        int i;

        p = findEntry(k);
        // Check if key is found
        if(p.key.equals(k)) {
            Integer old = p.value;
            p.value = v;
            return old;
        }

        // Key k is not found, then p == floorEntry(k) [Largest existing key x <= k]
        q = new SkipListEntry(k, v);

        // Insert q in the lowest level after p
        q.right = p.right;
        p.right.left = q;
        q.left = p;
        p.right = q;

        // Make a tower for the entry having a RANDOM height
        i = 0;      //  Current level
        while(r.nextDouble() < 0.5 /* coin toss */) {
            // coin toss success => build a new level
            if(i >= h)  // We reached the top level
            {
                createNewTopLayer();
            }

            // Find the first element with an up link
            while(p.up == null) {
                p = p.left;
            }

            p = p.up;

            // Add one more (k, v) to right of p and make its down point to q, then update q
            /**
             * p -> (k,v) -> p.right
             *         |
             *         q
             */
            SkipListEntry e;
            e = new SkipListEntry(k, v);

            e.right = p.right;
            p.right.left = e;
            e.left = p;
            p.right = e;
            q.up = e;
            e.down = q;
            q = e;

            i++;        // current level increases by 1
        }

        n++;            //  one more entry in the skip list
        return null;    //  no old value
    }

    private void deleteTopLayer() {
        head = head.down;
        tail = tail.down;
        h--;
    }

    public Integer deleteKey(String k) {
        SkipListEntry p = findEntry(k);
        if(!p.key.equals(k))        // key not found
            return null;

        Integer v = p.value;
        boolean endsInTopLayer = false;
        while(p != null) {
            p.left.right = p.right;
            p.right.left = p.left;
            endsInTopLayer = p.left.key.equals(SkipListEntry.negInf) && p.right.key.equals(SkipListEntry.posInf);
            p = p.up;
        }

        if(endsInTopLayer)
        {
            deleteTopLayer();
        }
        n--;
        return v;
    }
}
