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
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if(left > 1) {
            head.next = reverseBetween(head.next, left-1, right-1);
            return head;
        }
        // left == 1
        ListNode res = reverseFirstN(head, right);
        return res;
    }

// 递归翻转整个链表
    public ListNode reverse(ListNode head) {
        if(head == null) {
            return null;
        }
        if(head.next == null) {
            return head;
        }
        ListNode last = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }

// 递归翻转链表的前N个节点
    ListNode next = null;
    public ListNode reverseFirstN(ListNode head, int N) {
        if(N == 1) {
            next = head.next;
            return head;
        }
        ListNode last = reverseFirstN(head.next, N-1);
        head.next.next = head;
        head.next = next;
        return last;
    }
}