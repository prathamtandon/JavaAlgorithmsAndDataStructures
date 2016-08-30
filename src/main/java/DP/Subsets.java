package DP;

import java.util.ArrayList;

/**
 * Created by prathamt on 8/29/16.
 */
public class Subsets {

    public ArrayList<ArrayList<Integer>> getSubsets(ArrayList<Integer> set, int index) {
        ArrayList<ArrayList<Integer>> allSubsets;
        if(set.size() == 0) {
            allSubsets = new ArrayList<ArrayList<Integer>>();
            allSubsets.add(new ArrayList<Integer>());
        } else {
            allSubsets = getSubsets(set, index + 1);
            int item = set.get(index);
            ArrayList<ArrayList<Integer>> moreSubsets = new ArrayList<ArrayList<Integer>>();
            for(ArrayList<Integer> subset: allSubsets) {
                ArrayList<Integer> newSubset = new ArrayList<Integer>();
                newSubset.addAll(subset);
                newSubset.add(item);
                moreSubsets.add(newSubset);
            }
            allSubsets.addAll(moreSubsets);
        }

        return allSubsets;
    }


    public ArrayList<ArrayList<Integer>> getSubsets2(ArrayList<Integer> set) {
        ArrayList<ArrayList<Integer>> allSubsets = new ArrayList<ArrayList<Integer>>();
        int max = 1 << set.size(); // n << k => n * 2^k
        for (int i = 0; i < max; i++) {
            ArrayList<Integer> subset = convertIntToSet(i, set);
            allSubsets.add(subset);
        }
        return allSubsets;
    }

    public ArrayList<Integer> convertIntToSet(int x, ArrayList<Integer> set) {
        ArrayList<Integer> subset = new ArrayList<Integer>();
        for (int k = x, index = 0; k > 0 ; k >>= 1, index++) {
            if((k & 1) > 0)
                subset.add(set.get(index));
        }
        return subset;
    }
}
