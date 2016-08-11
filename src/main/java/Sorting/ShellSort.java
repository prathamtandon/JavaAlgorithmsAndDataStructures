package Sorting;

/**
 * Created by prathamt on 8/10/16.
 */
public class ShellSort {
    public static void sort(Comparable[] a) {
        // Sort a[] into increasing order.
        int N = a.length;
        int h = 1;
        while(h < N/3) h = 3*h + 1;
        while(h >= 1) {
            // The outer loop sets different endings for the h-subsequence
            for (int i = h; i < N; i++) {
                // The inner loop simulates insertion-sort on the current h-subsequence
                for (int j = i; j >= h && less(a[j], a[j-h]) ; j -= h) {
                    exch(a, j, j-h);
                }
            }
            h /= 3;
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if(less(a[i], a[i-1]))
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        sort(args);
        assert isSorted(args);
        show(args);
    }
}
