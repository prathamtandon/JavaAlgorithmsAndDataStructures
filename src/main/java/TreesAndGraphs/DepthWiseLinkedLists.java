package TreesAndGraphs;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by prathamt on 8/23/16.
 */
public class DepthWiseLinkedLists {

    private class Node {
        int val;
        Node left;
        Node right;
    }

    private static void createLevelLinkedList(Node root, ArrayList<LinkedList<Node>> lists, int level) {
        if(root == null) return;

        LinkedList<Node> list = null;
        if(lists.size() == level) {
            list = new LinkedList<Node>();
            lists.add(list);
        } else {
            list = lists.get(level);
        }

        list.add(root);
        createLevelLinkedList(root.left, lists, level + 1);
        createLevelLinkedList(root.right, lists, level + 1);
    }

    public static ArrayList<LinkedList<Node>> createLevelLinkedList(Node root) {
        ArrayList<LinkedList<Node>> lists = new ArrayList<LinkedList<Node>>();
        createLevelLinkedList(root, lists, 0);
        return lists;
    }
}
