import java.util.Scanner;
import java.util.Random;
public class Main {
    static class Node {
        Node next;
        int val;
        Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }
    public static void main(String[] args) {
        Node dummy = new Node(-1, null);
        Node head = new Node(30, null);
        dummy.next = head;
        Node cur = head;
        for (int i = 1; i < 30; i++) {
            Node node = new Node(new Random().nextInt(100), null);
            cur.next = node;
            cur = node;
        }
        Node dummyTail = new Node(-1, null);
        cur.next = dummyTail;
        quicksort(dummy, dummyTail);
        cur = dummy.next;
        while (cur != dummyTail) {
            System.out.print(cur.val+" ");
            cur = cur.next;
        }

    }

    public static void quicksort(Node start, Node end) {
        if (start.next != end) {
            Node ans = partition(start, end);
            quicksort(start, ans);
            quicksort(ans, end);
        }
    }

    public static Node partition(Node start, Node end) {
        Node dummy = start;
        start = start.next;
        int val = start.val;
        Node indexPrePre = dummy;
        Node indexPre = start;
        Node index = start.next;
        Node curPre = start;
        Node cur = start.next;
        Node done = end;
        while (cur != done) {
            if (cur.val < val) {
                Node indexNext = index.next;
                Node curNext = cur.next;
                swap(cur, index, curPre, indexPre);
                curPre = index;
                indexPrePre = indexPre;
                indexPre = cur;
                index = indexPre.next;
                cur = curNext;
            } else {
                curPre = cur;
                cur = cur.next;
            }
        }
        swap(start, indexPre, dummy, indexPrePre);
        return start;
    }

    public static void swap(Node p, Node q, Node preP, Node preQ) {
        if (p.next == q) {
            Node postQ = q.next;
            if (preP != null) {
                preP.next = q;
            }
            q.next = p;
            p.next = postQ;
            return;
        }
        if (q.next == p) {
            Node postP = p.next;
            if (preQ != null) {
                preQ.next = p;
            }
            p.next = q;
            q.next = postP;
            return;
        }
        Node postP = p.next;
        Node postQ = q.next;
        if (preP != null) {
            preP.next = q;
        }
        q.next = postP;
        if (preQ != null) {
            preQ.next = p;
        }
        p.next = postQ;
    }

}