/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p1 = headA;
        ListNode p2 = headB;
        boolean p1Change = false;
        boolean p2Change = false;
        while ((p1 != null || !p1Change) && (p2 != null || !p2Change)) {
            if (p1 == p2) {
                return p1;
            }
            if (p1 == null) {
                p1 = headB;
                p1Change = true;
            } else {
                p1 = p1.next;
            }
            if (p2 == null) {
                p2 = headA;
                p2Change = true;
            } else {
                p2 = p2.next;
            }
        }
        return null;
    }
}