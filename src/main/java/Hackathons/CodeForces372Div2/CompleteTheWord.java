package Hackathons.CodeForces372Div2;

import java.util.HashMap;

/**
 * Created by prathamt on 9/17/16.
 */
public class CompleteTheWord {

    private String input;
    private HashMap<String, Boolean> alphabets;
    private static int alphabetSize = 26;
    private boolean isValidResult = false;
    private static String INVALID_LETTER = "INVALID";

    public CompleteTheWord(String input) {
        this.input = input;
        alphabets = new HashMap<String, Boolean>();
        resetAlphabets();
    }

    private void resetAlphabets() {
        for (int i = 1; i <= alphabetSize; i++) {
            alphabets.put(String.valueOf((char)(i + 64)), false);
        }
    }

    private String checkIfValid(String ipt) {
        if(ipt.length() < alphabetSize) {
            isValidResult = false;
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ipt.length(); i++) {
            String key = String.valueOf(ipt.charAt(i));
            if(key.equals("?")) {
                String candidate = resetAndGetFirstValid();
                if(candidate.equals(INVALID_LETTER)) {
                    isValidResult = false;
                    return "";
                }
                sb.append(candidate);
            } else if(alphabets.get(key)) {
                isValidResult = false;
                return "";
            } else {
                alphabets.put(key, true);
                sb.append(key);
            }
        }
        isValidResult = true;
        return sb.toString();
    }

    private String resetAndGetFirstValid() {
        for(String key: alphabets.keySet()) {
            if(!alphabets.get(key)) {
                alphabets.put(key, true);
                return key;
            }
        }
        return INVALID_LETTER;
    }

    private String completeWord() {
        if(input.length() < alphabetSize) {
            isValidResult = false;
            return "";
        }
        for (int i = 0; i <= input.length() - alphabetSize; i++) {
            resetAlphabets();
            String substring = input.substring(i, alphabetSize);
            String res = checkIfValid(substring);
            if(isValidResult)
                return res;
        }
        isValidResult = false;
        return "";
    }

    public static void main(String[] args) {
        String input = "?????????????B???????????????";
        CompleteTheWord ctw = new CompleteTheWord(input);
        String res = ctw.completeWord();
        if(ctw.isValidResult)
            System.out.println("Result: " + res);
        else
            System.out.println(-1);
    }
}
