package Bits;

/**
 * Created by prathamt on 9/3/16.
 */
public class MissingAndRepeating {

    public static void printMissingAndRepeating(int[] a, int n) {
        int expected = 0;
        for (int i = 0; i < n; i++) {
            expected = expected ^ (i + 1);
        }

        int actual = 0;
        for (int i = 0; i < a.length; i++) {
            actual = actual ^ a[i];
        }

        int result = expected ^ actual;

        //  get left-most set bit mask
        int mask = result ^ (result & (result - 1));

        int group1 = 0, group2 = 0;

        for (int i = 0; i < a.length; i++) {
            if((a[i] & mask) > 0)
                group1 = group1 ^ a[i];
            else
                group2 = group2 ^ a[i];
        }

        for (int i = 0; i < n; i++) {
            if(((i + 1) & mask) > 0)
                group1 = group1 ^ (i + 1);
            else
                group2 = group2 ^ (i + 1);
        }

        // group1 now contains the missing and group2 the repeating number
        System.out.println(String.format("The two elements are: %d and %d", group1, group2));

    }

    public static void main(String[] args) {
        int[] a = {4,3,6,2,1,1};
        int n = 6;
        printMissingAndRepeating(a, n);
    }
}
