package Strings;

import Utils.StdRandom;

import java.math.BigInteger;
import java.util.Random;

/**
 * Created by prathamt on 8/17/16.
 */
public class RabinKarp {

    private long patHash;     //  pattern hash
    private int M;              //  pattern length
    private long Q;             //  a large prime
    private int R = 256;        //  alphabet size
    private long RM;            //   R^(M-1) % Q

    public RabinKarp(String pat) {
        this.M = pat.length();
        Q = longRandomPrime();
        RM = 1;
        for (int i = 1; i <= M - 1; i++) {
            RM = (R * RM) % Q;
        }
        patHash = hash(pat, M);
    }

    private long longRandomPrime() {
        return BigInteger.probablePrime(10, new Random()).longValue();
    }

    private long hash(String key, int M) {
        long h = 0;
        for (int i = 0; i < M; i++) {
            h = (R*h + key.charAt(i)) % Q;
        }
        return h;
    }

    private int search(String txt) {
        int N = txt.length();
        long txtHash = hash(txt, M);
        if(patHash == txtHash) return 0;
        for(int i = M; i < N; i++) {
            // Remove leading digit, add trailing digit, check for match
            // An extra Q is added at beginning to ensure everything stays positive.
            txtHash = (txtHash + Q - RM * txt.charAt(i-M) % Q) % Q;
            txtHash = (txtHash * R + txt.charAt(i)) % Q;
            if(txtHash == patHash)
                return i - M + 1;       //  Using Monte Carlo correctness

        }
        return N;
    }

    public static void main(String[] args) {
        String pat = "AABAAA";
        String txt = "AABAABAAAA";
        RabinKarp rk = new RabinKarp(pat);
        int offset = rk.search(txt);
        System.out.println("text:    " + txt);
        System.out.print("pattern: ");
        for (int i = 0; i < offset; i++) {
            System.out.print(" ");
        }
        System.out.println(pat);
    }
}
