package Strings;

/**
 * Created by prathamt on 8/15/16.
 */
public class CountingSort<Item extends CountingSort.KeyValue> {

    private Item[] a;
    private int[] count;
    private Item[] aux;
    private final int R = 256;

    protected class KeyValue {
        private int key;
        private int value;

        public int key() { return key; }
        public int value() { return value; }
    }

    public CountingSort(Item[] a) {
        this.a = a;
        count = new int[a.length];
        aux = (Item[])new CountingSort.KeyValue[a.length];
        countFrequencies();
    }

    private void countFrequencies() {
        for (int i = 0; i < a.length; i++) {
            count[a[i].key() + 1] += 1;
        }
        generateCountIndices();
    }

    private void generateCountIndices() {
        for (int r = 0; r < R; r++) {
            count[r+1] += count[r];
        }
        distributeData();
    }

    private void distributeData() {
        for (int i = 0; i < a.length; i++) {
            aux[count[a[i].key() + 1]] = a[i];
        }
        copyBack();
    }

    private void copyBack() {
        for (int i = 0; i < a.length; i++) {
            a[i] = aux[i];
        }
    }

    public Item[] sorted() { return a; }

}
