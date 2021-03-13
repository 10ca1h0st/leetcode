class MyHashSet {

    static final int SIZE = 10000;

    private Node[] table;

    class Node {
        int val;
        Node next;
        Node(int val) {
            this.val = val;
        }
    }

    /** Initialize your data structure here. */
    public MyHashSet() {
        table = new Node[SIZE];
    }

    private int hash(int key) {
        return key%SIZE;
    }
    
    public void add(int key) {
        int index = hash(key);
        if(table[index] == null) {
            table[index] = new Node(key);
        }
        else {
            Node n = table[index];
            while(n.val != key && n.next != null) {
                n = n.next;
            }
            if(n.val == key) {
                return;
            }
            Node next = new Node(key);
            n.next = next;
        }
    }
    
    public void remove(int key) {
        int index = hash(key);
        Node n = table[index];
        if(n == null) {
            return;
        }
        if(n.val == key) {
            table[index] = n.next;
        }
        while(n.next != null) {
            if(n.next.val == key) {
                n.next = n.next.next;
                break;
            }
            n = n.next;
        }
    }
    
    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        int index = hash(key);
        Node n = table[index];
        while(n != null) {
            if(n.val == key) {
                return true;
            }
            n = n.next;
        }
        return false;
    }
}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */