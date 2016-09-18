package Hackathons.CodeForces372Div2;

/**
 * Created by prathamt on 9/17/16.
 */
public class CrazyComputer {

    private int allowedDelay;
    private int[] times;
    private int onScreen;

    public CrazyComputer(int N, int d, int[] t) {
        this.allowedDelay = d;
        this.times = t;
        if(N > 0)
            this.onScreen = 1;
        else
            this.onScreen = 0;
    }

    private void countRemaining() {
        int a = 0, b = 0;
        for (int i = 0; i < times.length; i++) {
            a = times[i];
            if(i+1 < times.length) {
                b = times[i + 1];
                if (b - a > allowedDelay)
                    onScreen = 1;
                else
                    onScreen += 1;
            }
        }
    }

    public static void main(String[] args) {
        int N = 6;
        int d = 1;
        int[] times = {1, 2, 3, 4, 5, 6};

        CrazyComputer cc = new CrazyComputer(N, d, times);
        cc.countRemaining();
        System.out.println("Remaining: " + String.valueOf(cc.onScreen));
    }


}
