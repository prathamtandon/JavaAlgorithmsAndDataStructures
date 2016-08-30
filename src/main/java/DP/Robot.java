package DP;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by prathamt on 8/29/16.
 */
public class Robot {

    private class Point{
        int x;
        int y;

        public Point(int x, int y) {this.x = x; this.y = y;}
    }

    private boolean isFree(int x, int y) {
        return true;
    }

    public boolean getPath(int x, int y, ArrayList<Point> path, HashMap<Point, Boolean> cache) {
        Point p = new Point(x, y);
        if(cache.containsKey(p))
            return cache.get(p);
        if(x == 0 && y == 0)
            return true;
        boolean success = false;

        if(x > 0 && isFree(x - 1, y))
            success = getPath(x - 1, y, path , cache);
        if(y > 0 && isFree(x, y - 1))
            success = getPath(x, y - 1, path, cache);

        if(success)
            path.add(p);

        cache.put(p, success);
        return success;
    }
}
