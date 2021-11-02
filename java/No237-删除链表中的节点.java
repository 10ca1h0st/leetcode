/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public void deleteNode(ListNode node) {
        while (true) {
            ListNode nxt = node.next;
            node.val = nxt.val;
            if (nxt.next == null) {
                node.next = null;
                break;
            }
            node = nxt;
        }
        return;
    }
}