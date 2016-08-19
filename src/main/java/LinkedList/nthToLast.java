package LinkedList;

/**
 * Created by prathamt on 8/19/16.
 */
public class nthToLast {

    private Node head;
    private class Node {
        int value;
        Node next;
    }

    int nthToLast(int k) throws Exception {
        Node p1 = head;
        Node p2 = head;

        int count = 0;
        while(count < k) {
            if(p1 == null)
                throw new Exception("Invalid k");
            p1 = p1.next;
            count++;
        }
        if(p1 == null)
            throw new Exception("Invalid k");
        for(; p1.next != null; p1 = p1.next)
            p2 = p2.next;
        return p2.value;
    }
}
