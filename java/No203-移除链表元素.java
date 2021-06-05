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
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode p = dummy;
        ListNode n = dummy.next;
        while(n != null) {
            if(n.val == val) {
                p.next = n.next;
                n = p.next;
            }
            else {
                p = n;
                n = n.next;
            }
        }
        return dummy.next;
    }
}