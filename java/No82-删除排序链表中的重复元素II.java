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
        ListNode lastNotDup = dummy;
        ListNode cur = head;
        while(cur != null) {
            ListNode next = cur.next;
            boolean isCurDup = false;
            while(next != null && next.val == cur.val) {
                isCurDup = true;
                next = next.next;
            }
            if(isCurDup) {
                cur = next;
            }
            else {
                lastNotDup.next = cur;
                lastNotDup = cur;
                cur.next = null;
                cur = next;
            }
        }
        return dummy.next;
    }
}