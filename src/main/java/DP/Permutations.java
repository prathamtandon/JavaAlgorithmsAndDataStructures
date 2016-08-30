package DP;

import java.util.ArrayList;

/**
 * Created by prathamt on 8/30/16.
 */
public class Permutations {

    public static ArrayList<String> getPerms(String s) {
        if(s == null)
            return null;
        ArrayList<String> permutations = new ArrayList<String>();
        if(s.length() == 0) {
            permutations.add("");
            return permutations;
        }

        char first = s.charAt(0);
        String remainder = s.substring(1);
        ArrayList<String> words = getPerms(remainder);
        for(String word: words) {
            for (int i = 0; i < word.length(); i++) {
                String str = insertCharAt(word, first, i);
                permutations.add(str);
            }
        }
        return permutations;
    }

    public static String insertCharAt(String word, char c, int i) {
        String start = word.substring(0, i);
        String end = word.substring(i);
        return start + c + end;
    }
}
