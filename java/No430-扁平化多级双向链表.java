/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};
*/

class Solution {
    public Node flatten(Node head) {
        if (head == null) {
            return null;
        }
        helper(head);
        return head;
    }

    // 扁平化以head为首的链表，并返回结果链表的最后一个节点
    public Node helper(Node head) {
        Node node = head;
        Node insert = null;
        Node prev = null;
        while (node != null) {
            if (insert != null) {
                node.prev = insert;
                insert = null;
            }
            if (node.child != null) {
                Node next = node.next;
                Node son = node.child;
                Node sonTail = helper(son);
                node.child = null;
                node.next = son;
                son.prev = node;
                sonTail.next = next;
                insert = sonTail;
                prev = sonTail;
                node = next;
            } else {
                prev = node;
                node = node.next;
            }
        }
        return prev;
    }
}