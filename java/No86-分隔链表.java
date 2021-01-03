/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode partition(ListNode head, int x) {
        if(head == null) {
            return null;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode small = dummy;
        ListNode big = null;

        while(small.next!=null) {
            if(small.next.val < x) {
                small = small.next;
            }
            else {
                break;
            }
        }
        big = small.next;
        if(big == null) {
            return dummy.next;
        }
        while(big.next != null) {
            if(big.next.val >= x) {
                big = big.next;
            }
            else {
                ListNode cur = big.next;
                big.next = cur.next;
                ListNode tmp = small.next;
                small.next = cur;
                cur.next = tmp;
                small = small.next;
            }
        }
        return dummy.next;

    }
}