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
        if(headA == null || headB == null) {
            return null;
        }
        ListNode a = headA;
        // a已经走完了链表A
        boolean aDone = false;
        ListNode b = headB;
        boolean bDone = false;
        while(a != b) {
            if(a.next == null) {
                if(aDone) {
                    return null;
                }
                a = headB;
                aDone = true;
            }
            else {
                a = a.next;
            }
            if(b.next == null) {
                if(bDone) {
                    return null;
                }
                b= headA;
                bDone = true;
            }
            else {
                b = b.next;
            }
        }
        return a;
    }
}