package LinkedList;

/**
 * Created by prathamt on 8/19/16.
 */
public class FindLoop {

    private class Node {
        int value;
        Node next;
    }

    Node FindBeginning(Node head) {
        Node slow = head;
        Node fast = head;

        while(fast != null && fast.next != null && slow != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) {
                break;
            }
        }

        if(fast == null || fast.next == null)
            return null;
        slow = head;
        while(slow != head) {
            slow = slow.next;
            fast = fast.next;
        }

        return fast;
    }
}
