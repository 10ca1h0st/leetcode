class MyHashMap {

    static final int MAX = 1 << 13;

    private class Node {
        int k;
        int v;
        Node next;

        Node(int k, int v) {
            this.k = k;
            this.v = v;
        }
    }

    Node[] table;

    private int hash(int key) {
        return key & (MAX-1);
    }

    /** Initialize your data structure here. */
    public MyHashMap() {
        table = new Node[MAX];
    }
    
    /** value will always be non-negative. */
    public void put(int key, int value) {
        int index = hash(key);
        Node cur = table[index];
        if(cur == null) {
            table[index] = new Node(key, value);
            return;
        }
        Node prev = null;
        while(cur != null && cur.k != key) {
            prev = cur;
            cur = cur.next;
        }
        if(cur == null) {
            prev.next = new Node(key, value);
            return;
        }
        cur.v = value;
    }
    
    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int index = hash(key);
        Node cur = table[index];
        while(cur != null) {
            if(cur.k == key) {
                return cur.v;
            }
            cur = cur.next;
        }
        return -1;
    }
    
    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int index = hash(key);
        Node cur = table[index];
        Node prev = null;
        while(cur != null) {
            if(cur.k == key) {
                break;
            }
            prev = cur;
            cur = cur.next;
        }
        if(cur != null) {
            if(prev == null) {
                table[index] = cur.next;
            }
            else {
                prev.next = cur.next;
            }
        }
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */