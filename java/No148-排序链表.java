/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode sortList(ListNode head) {
        return mergeSort(head);
    }

    private ListNode mergeSort(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head;
        ListNode quick = head;
        ListNode pre = null;
        while (quick != null && quick.next != null) {
            pre = slow;
            slow = slow.next;
            quick = quick.next.next;
        }
        if (slow == quick) {
            // 一个节点
            return head;
        }
        pre.next = null;
        ListNode node1 = mergeSort(head);
        ListNode node2 = mergeSort(slow);
        return merge(node1, node2);
    }

    private ListNode merge(ListNode head1, ListNode head2) {
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }
        ListNode dummy = new ListNode(-100001);
        ListNode cur = dummy;
        while (head1 != null && head2 != null) {
            if (head1.val < head2.val) {
                cur.next = head1;
                head1 = head1.next;
                cur = cur.next;
                cur.next = null;
            } else {
                cur.next = head2;
                head2 = head2.next;
                cur = cur.next;
                cur.next = null;
            }
        }
        if (head1 != null) {
            cur.next = head1;
        } else {
            cur.next = head2;
        }
        return dummy.next;
    }
}