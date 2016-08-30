package DP;

/**
 * Created by prathamt on 8/29/16.
 */
public class MagicIndex {

    public int magicIndexDistinctHelper(int[] array, int low, int high) {
        if(low > high)
            return -1;

        int mid = low + (high - low)/2;

        if(array[mid] == mid)
            return mid;

        if(array[mid] > mid)
            return magicIndexDistinctHelper(array, low, mid-1);

        return magicIndexDistinctHelper(array, mid+1, high);
    }

    public int magicIndexDistinct(int[] array) {
        return magicIndexDistinctHelper(array, 0, array.length - 1);
    }

    public int magicIndexNonDistinctHelper(int[] array, int low, int high) {
        if(low > high)
            return -1;

        int mid = low + (high - low)/2;

        if(array[mid] == mid)
            return mid;

        int left = Math.min(mid-1, array[mid]);
        int leftResult = magicIndexNonDistinctHelper(array, low, left);
        if(leftResult > -1)
            return leftResult;

        int right = Math.max(mid+1, array[mid]);
        int rightResult = magicIndexNonDistinctHelper(array, right, high);

        return rightResult;
    }
    
    public int magicIndexNonDistinct(int[] array) {
        return magicIndexNonDistinctHelper(array, 0, array.length - 1);
    }
}
