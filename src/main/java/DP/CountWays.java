package DP;

/**
 * Created by prathamt on 8/28/16.
 */
public class CountWays {

    public static int countWays(int n, int[] map) {
        if(n < 0)
            return 0;
        else if(n == 0)
            return 1;
        else if(map[n] > -1)
            return map[n];
        else {
            map[n] = countWays(n-1, map) + countWays(n-2, map) + countWays(n-3, map);
            return map[n];
        }
    }
}
