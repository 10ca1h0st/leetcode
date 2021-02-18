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
    ListNode dummy;
    ListNode append;
    boolean moveAppend;
    int k;
    public ListNode reverseKGroup(ListNode head, int k) {
        if(k == 1) {
            return head;
        }
        dummy = new ListNode();
        append = dummy;
        moveAppend = false;
        this.k = k;
        helper(head, 1);
        return dummy.next;
    }

    void helper(ListNode node, int order) {
        ListNode next = node.next;
        if(next == null) {
            append.next = node;
            if(order == k) {
                // 链表长度刚好
                moveAppend = true;
                append = append.next;
            }
            return;
        }
        helper(next, order%k + 1);
        node.next = append.next;
        append.next = node;
        // 是否移动append
        if(order == k) {
            moveAppend = true;
        }
        if(moveAppend) {
            append = append.next;
            if(order == 1) {
                append = dummy;
            }
        }
    }

    
}