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
    public ListNode rotateRight(ListNode head, int k) {
        ListNode last = null;
        int length = 0;
        ListNode cur = head;
        while(cur != null) {
            length++;
            last = cur;
            cur = cur.next;
        }
        if(length == 0) {
            return null;
        }
        int realK = k % length;
        if(realK == 0) {
            return head;
        }
        ListNode lastRealK = head;
        // lastRealK的前一个节点
        ListNode prev = null;
        cur = head;
        for(int i = 0; i < realK; i++) {
            cur = cur.next;
        }
        while(cur != null) {
            cur = cur.next;
            prev = lastRealK;
            lastRealK = lastRealK.next;
        }
        prev.next = null;
        last.next = head;
        return lastRealK;
    }
}