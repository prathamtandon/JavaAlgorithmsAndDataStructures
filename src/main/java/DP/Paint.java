package DP;

/**
 * Created by prathamt on 9/1/16.
 */
public class Paint {

    enum Color {
        Black, White, Red, Yellow, Green
    }

    boolean paintFill(Color[][] screen, int x, int y, Color ocolor, Color ncolor) {
        if(x < 0 || x >= screen[0].length ||
                y < 0 || y >= screen.length) {
            return false;
        }
        if(screen[y][x] == ocolor) {
            screen[y][x] = ncolor;
            paintFill(screen, x - 1, y, ocolor, ncolor);
            paintFill(screen, x + 1, y, ocolor, ncolor);
            paintFill(screen, x, y - 1, ocolor, ncolor);
            paintFill(screen, x, y + 1, ocolor, ncolor);
        }
        return true;
    }

    boolean paintFill(Color[][] screen, int x, int y, Color ncolor) {
        if(screen[y][x] == ncolor) return false;
        return paintFill(screen, x, y, screen[y][x], ncolor);
    }
}
