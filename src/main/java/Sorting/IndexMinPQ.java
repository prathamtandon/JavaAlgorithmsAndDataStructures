package Sorting;

/**
 * Created by prathamt on 8/15/16.
 */
public class IndexMinPQ<Item extends Comparable<Item>> {

    private int[] pq;       //  keys associated with items
    private Item[] items;   //  actual items
    private int[] qp;       //  inverted index on keys
    private int N = 0;

    public IndexMinPQ(int maxN) {
        pq = new int[maxN];
        qp = new int[maxN];
        for (int i = 0; i < maxN; i++) {
            qp[i] = -1;
        }
        items = (Item[]) new Comparable[maxN+1];
    }

    private boolean less(int i, int j) {
        return items[pq[i]].compareTo(items[pq[j]]) < 0;
    }

    private void exch(int i, int j) {
        Item t = items[i];
        items[i] = items[j];
        items[j] = t;

        int t1 = pq[i], t2 = pq[j];

        int k = pq[i];
        pq[i] = pq[j];
        pq[j] = k;

        qp[t1] = j;
        qp[t2] = i;
    }

    public void insert(int k, Item item) {
        items[++N] = item;
        pq[N] = k;
        qp[k] = N;
        swim(N);
    }

    public boolean contains(int k) {
        return qp[k] != -1;
    }

    public void delete(int k) {
        if(!contains(k)) return;
        int cur = qp[k];
        int cmp = items[cur].compareTo(items[N]);
        exch(cur, N--);
        items[N+1] = null;
        qp[k] = -1;
        if(cmp < 0)
            swim(cur);
        else
            sink(cur);
    }

    public int delMin() {
        Item min = items[1];
        int key = pq[1];
        exch(1, N--);
        items[N+1] = null;
        qp[key] = -1;
        sink(1);
        return key;
    }

    public void change(int k, Item item) {
        if(!contains(k)) return;
        Item cur = items[pq[k]];
        int cmp = cur.compareTo(item);
        if(cmp == 0) return;
        items[pq[k]] = item;
        if(cmp < 0)
            sink(k);
        else
            swim(k);
    }

    private void swim(int k) {
        while (k > 1 && less(k, k/2)) {
            exch(k/2, k);
            k /= 2;
        }
    }

    private void sink(int k) {
        while (2*k <= N) {
            int j = 2*k;
            if(j < N && less(j+1, j)) j++;
            if(less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    public boolean isEmpty() { return N == 0; }

}
