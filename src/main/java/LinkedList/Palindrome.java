package LinkedList;

import Collections.Stack;

/**
 * Created by prathamt on 8/19/16.
 */
public class Palindrome {

    private class Node {
        int val;
        Node next;
    }

    public static boolean isPalindrome(Node head) {
        Node fast = head;
        Node slow = head;

        Stack<Integer> stack = new Stack<Integer>();

        while(fast != null && fast.next != null) {
            stack.push(slow.val);
            slow = slow.next;
            fast = fast.next.next;
        }

        if(fast != null) {
            slow = slow.next;
        }

        while(slow != null) {
            int top = stack.pop();
            if(top != slow.val) {
                return false;
            }
            slow = slow.next;
        }
        return true;
    }
}
