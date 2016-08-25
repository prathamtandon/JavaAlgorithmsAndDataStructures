package Bits;

/**
 * Created by prathamt on 8/24/16.
 */
public class NextSmallestAndLargest {

    public int getNext(int n) {
        /* Compute c0 and c1 */
        int c = n;
        int c0 = 0;
        int c1 = 0;
        while(((c & 1) == 0) && (c != 0)) {
            c0++;
            c >>= 1;
        }

        while((c & 1) == 1) {
            c1++;
            c >>= 1;
        }

        /* Error: if n == 11...1100...00, then there is no bigger number with same number of 1s */
        if(c0 + c1 == 32 || c0 + c1 == 0)
            return -1;

        int p = c0 + c1;

        n |= (1 << p);                  //  Flip rightmost non-trailing zero
        n &= ~((1 << p) - 1);           //  Clear all bits to right of p
        n |= (1 << (c1 - 1)) - 1;       //  Insert (c1 - 1) ones on the right
        return n;
    }

    public int getPrev(int n) {
        int temp = n;
        int c0 = 0;
        int c1 = 0;
        while((temp & 1) == 1) {
            c1++;
            temp >>= 1;
        }

        if(temp == 0) return -1;

        while(((temp & 1) == 0) && (temp != 0)) {
            c0++;
            temp >>= 1;
        }

        int p = c0 + c1;
        n &= ((~0) << (p + 1));

        int mask = (1 << (c1 + 1)) - 1;
        n |= mask << (c0 - 1);

        return n;
    }
}
