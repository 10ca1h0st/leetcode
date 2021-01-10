class LRUCache {
    LinkedHashMap<Integer, Integer> listMap;

    public LRUCache(int capacity) {
        listMap = new LinkedHashMap<>() {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > capacity;
            }
        };
    }
    
    public int get(int key) {
        if(!listMap.containsKey(key)) {
            return -1;
        }
        int v = listMap.get(key);
        listMap.remove(key);
        listMap.put(key, v);
        return v;
    }
    
    public void put(int key, int value) {
        if(listMap.containsKey(key)) {
            listMap.remove(key);
        }
        listMap.put(key, value);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */