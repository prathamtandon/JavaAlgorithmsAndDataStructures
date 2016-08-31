package DP;

/**
 * Created by prathamt on 8/31/16.
 */
public class ValidParens {

    private static void printParens(int nLParen, int nRParen, StringBuilder s) {
        if(nLParen == 0 && nRParen == 0) {
            System.out.println(s.toString());
            return;
        }
        if(nLParen == 0 && nRParen > 0) {
            printParens(nLParen, nRParen  - 1, new StringBuilder(s.append(")")));
            return;
        }
        if(nLParen == nRParen) {
            printParens(nLParen - 1, nRParen, new StringBuilder(s.append("(")));
            return;
        }
        if(nLParen < nRParen) {
            printParens(nLParen - 1, nRParen, new StringBuilder(s).append("("));
            printParens(nLParen, nRParen - 1, new StringBuilder(s).append(")"));
        }
    }

    public static void printParens(int n) {
        printParens(n, n, new StringBuilder());
    }

    public static void main(String[] args) {
        printParens(3);
    }
}
