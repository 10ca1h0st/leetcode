/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        if(head == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        // 快慢指针，若存在环，slow与fast在环中相遇
        while(fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if(slow == fast) {
                break;
            }
        }
        if(fast.next == null || fast.next.next == null) {
            // 不存在环
            return null;
        }
        // 新建一个节点从head开始，和slow一起吗，每次走一步，最终会在环的入口相遇
        ListNode entry = head;
        while(entry != slow) {
            entry = entry.next;
            slow = slow.next;
        }
        return entry;
    }
}