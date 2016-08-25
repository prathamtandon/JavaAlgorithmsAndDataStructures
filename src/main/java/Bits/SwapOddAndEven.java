package Bits;

/**
 * Created by prathamt on 8/25/16.
 */
public class SwapOddAndEven {

    public int swapOddEvenBits(int x) {
        //      Create a mask to turn off all odd bits
        //      Create another mask to turn off all even bits
        //      AND with first mask and shift right by 1
        //      AND with second mask and shift left by 1
        //      OR the partial results
        return (((x & 0xaaaaaaaa) >> 1) | ((x & 0x55555555) << 1));
    }
}
