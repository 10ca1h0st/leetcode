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
    public ListNode[] splitListToParts(ListNode head, int k) {
        int length = 0;
        ListNode node = head;
        while (node != null) {
            length++;
            node = node.next;
        }
        if (length == 0) {
            return new ListNode[k];
        }
        int div = length / k;
        int remain = length % k;
        ListNode[] ans = new ListNode[k];
        node = head;
        int index = 0;
        while (node != null) {
            ans[index] = node;
            int size = div;
            if (remain > 0) {
                size++;
            }
            remain = Math.max(0, remain-1);
            ListNode prev = null;
            while (size > 0) {
                prev = node;
                node = node.next;
                size--;
            }
            prev.next = null;
            index++;
        }
        return ans;
    }
}