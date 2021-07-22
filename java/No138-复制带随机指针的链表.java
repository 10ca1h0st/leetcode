/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Map<Node, List<Node>> pointer = new HashMap<>();
        Map<Node, Node> old2new = new HashMap<>();
        Node cur = head;
        Node dummy = new Node(-1);
        Node newCur = dummy;
        Node newNext = null;
        int val = 0;
        Node next = null;
        Node random = null;
        while (cur != null) {
            val = cur.val;
            next = cur.next;
            random = cur.random;
            newNext = new Node(cur.val);
            old2new.put(cur, newNext);
            if (pointer.containsKey(cur)) {
                // 之前有节点指向cur
                List<Node> froms = pointer.get(cur);
                for (Node from : froms) {
                    from = old2new.get(from);
                    from.random = newNext;
                }
            }
            if (old2new.containsKey(random)) {
                // 此时就可以设置random指针
                Node to = old2new.get(random);
                newNext.random = to;
            } else {
                List<Node> list = pointer.getOrDefault(random, new ArrayList<Node>());
                list.add(cur);
                pointer.put(random, list);
            }
            newCur.next = newNext;
            newCur = newNext;
            cur = next;
        }
        return dummy.next;
    }
}