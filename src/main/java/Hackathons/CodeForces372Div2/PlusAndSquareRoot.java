package Hackathons.CodeForces372Div2;

import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by prathamt on 9/17/16.
 */
public class PlusAndSquareRoot {

    private BigInteger currentNumber;
    private BigInteger square;
    private BigInteger currentLevel;
    private BigInteger nextLevel;
    private BigInteger goalLevel;
    private ArrayList<BigInteger> plusPresses;

    public PlusAndSquareRoot(BigInteger n) {
        this.goalLevel = n;
        this.currentNumber = BigInteger.ONE.add(BigInteger.ONE);
        this.currentLevel = BigInteger.ONE;
        this.nextLevel = currentLevel.add(BigInteger.ONE);
        this.plusPresses = new ArrayList<BigInteger>();
    }

    public ArrayList<BigInteger> getPlusPresses() {
        BigInteger temp = nextLevel;
        square = temp.pow(2);
        while(true) {
            BigInteger difference = square.subtract(currentNumber);
            if(difference.mod(currentLevel).equals(BigInteger.ZERO)) {
                BigInteger pressCount = difference.divide(currentLevel);
                plusPresses.add(pressCount);
                if(nextLevel.equals(goalLevel))
                    return plusPresses;
                currentNumber = temp;
                currentLevel = nextLevel;
                nextLevel = currentLevel.add(BigInteger.ONE);
                temp = nextLevel;
                square = temp.pow(2);
            } else {
                temp = square.subtract(nextLevel);
                square = temp.pow(2);
            }
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int n = in.nextInt();
        PlusAndSquareRoot obj = new PlusAndSquareRoot(new BigInteger(String.valueOf(n)).add(BigInteger.ONE));
        ArrayList<BigInteger> result = obj.getPlusPresses();
        for(BigInteger count: result) {
            out.println(count.toString());
        }
        out.close();
    }
}
