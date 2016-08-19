package Strings;

/**
 * Created by prathamt on 8/18/16.
 */
public class SimpleCompression {

    public static String compress(String s) {
        if(countCompressed(s) > s.length())
            return s;
        StringBuilder sb = new StringBuilder("");
        int count = 1;
        for (int i = 1; i < s.length(); i++) {
            if(s.charAt(i) != s.charAt(i-1)) {
                sb.append(s.charAt(i-1));
                sb.append(count);
                count = 1;
            } else
                count += 1;
        }
        sb.append(s.charAt(s.length()-1));
        sb.append(count);
        return sb.toString();
    }

    private static int countCompressed(String s) {
        if(s.length() < 2) return s.length()+1;
        int count = 0;
        int runningCount = 1;
        for (int i = 1; i < s.length(); i++) {
            if(s.charAt(i) != s.charAt(i-1)) {
                count += Integer.toString(runningCount).length() + 1;
                runningCount = 1;
            } else
                runningCount += 1;
        }
        return count;
    }

    public static void main(String[] args) {
        String s = "aabcccccaaa";
        System.out.println("s: " + s);
        System.out.println("c: " + compress(s));
    }
}
