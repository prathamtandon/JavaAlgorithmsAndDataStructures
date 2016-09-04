package Searching;

/**
 * Created by prathamt on 9/4/16.
 */
public class SkipListEntry {
    public String key;
    public Integer value;

    public SkipListEntry left;
    public SkipListEntry right;
    public SkipListEntry up;
    public SkipListEntry down;

    public static String negInf = "-oo";   //   -inf key value
    public static String posInf = "+oo";   //   +inf key value

    public SkipListEntry(String key, Integer value) {
        this.key = key;
        this.value = value;
    }
}
