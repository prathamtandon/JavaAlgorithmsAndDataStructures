package Bits;

/**
 * Created by prathamt on 8/25/16.
 */
public class BitDifference {

    public int howManyBitsDiffer(int a, int b) {
        int count = 0;
        for(int c = a ^ b; c != 0; c >>= 1) {
            if((c & 1) > 0)
                count++;
        }
        return count;
    }
}
