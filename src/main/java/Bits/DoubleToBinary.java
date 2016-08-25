package Bits;

/**
 * Created by prathamt on 8/24/16.
 */
public class DoubleToBinary {

    public static String printBinary(double num) {
        if(num >= 1 || num <= 0)
            return "ERROR";

        StringBuilder sb = new StringBuilder(".");
        while(num > 0) {
            double r = num * 2;
            if(r >= 1) {
                num = r - 1;
                sb.append("1");
            } else {
                num = r;
                sb.append("0");
            }
            if(sb.length() >= 32)
                return "ERROR";
        }
        return sb.toString();
    }
}
