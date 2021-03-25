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
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode cur = head;
        ListNode nxt = null;
        while(cur != null) {
            nxt = cur.next;
            while(nxt != null && nxt.val == cur.val) {
                nxt = nxt.next;
            }
            cur.next = nxt;
            cur = nxt;
        }
        return dummy.next;
    }
}