package Bits;

import java.util.ArrayList;

/**
 * Created by prathamt on 8/26/16.
 */
public class FindMissing {

    public int findMissing(ArrayList<Integer> array) {
        /* Start from the least significant bit and work out way up */
        return findMissing(array, 0);
    }

    public int findMissing(ArrayList<Integer> input, int column) {
        if(column >= 32)
            return 0;
        ArrayList<Integer> oneBits = new ArrayList<Integer>(input.size() / 2);
        ArrayList<Integer> zeroBits = new ArrayList<Integer>(input.size() / 2);

        for(Integer t: input) {
            if(t == 0)
                zeroBits.add(t);
            else
                oneBits.add(t);
        }

        if(zeroBits.size() <= oneBits.size()) {
            int v = findMissing(zeroBits, column + 1);
            return (v << 1) | 0;
        } else {
            int v = findMissing(oneBits, column + 1);
            return (v << 1) | 1;
        }
    }
}
