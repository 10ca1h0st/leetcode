/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode removeDuplicateNodes(ListNode head) {
        boolean[] appear = new boolean[20000];
        ListNode cur = head;
        ListNode dumb = new ListNode(-1);
        ListNode prev = dumb;
        prev.next = head;
        while(cur != null) {
            if(appear[cur.val]) {
                prev.next = cur.next;
                cur = cur.next;
            }
            else {
                appear[cur.val] = true;
                prev = cur;
                cur = cur.next;
            }
        }
        return dumb.next;
    }
}