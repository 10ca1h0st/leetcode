// 使用二分搜索
 TimeMap {

    private static class ValueAndTime {
        String v;
        int t;

        ValueAndTime(String v, int t) {
            this.v = v;
            this.t = t;
        }

        String getV() {
            return v;
        }

        int getT() {
            return t;
        }
    }

    private Map<String, List<ValueAndTime>> map;

    /** Initialize your data structure here. */
    public TimeMap() {
        map = new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {
        if (map.containsKey(key)) {
            List<ValueAndTime> list = map.get(key);
            ValueAndTime tail = new ValueAndTime(value, timestamp);
            list.add(tail);
            map.put(key, list);
        } else {
            List<ValueAndTime> list = new ArrayList<>();
            ValueAndTime tail = new ValueAndTime(value, timestamp);
            list.add(tail);
            map.put(key, list);
        }
    }
    
    public String get(String key, int timestamp) {
        if (!map.containsKey(key)) {
            return "";
        } else {
            List<ValueAndTime> list = map.get(key);
            String ans = binarySearch(list, timestamp);
            return ans;
        }
    }

    private String binarySearch(List<ValueAndTime> list, int target) {
        int low = 0;
        int high = list.size() - 1;
        int middle = 0;
        String ans = "";
        ValueAndTime o = null;
        while (low <= high) {
            middle = low + (high - low) / 2;
            o = list.get(middle);
            if (o.getT() < target) {
                ans = o.getV();
                low = middle + 1;
            } else if (o.getT() > target) {
                high = middle - 1;
            } else {
                ans = o.getV();
                return ans;
            }
        }
        return ans;
    }
}

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */

// 44/46 超时
class TimeMap {

    private static class ValueAndTime {
        String v;
        int t;

        ValueAndTime(String v, int t) {
            this.v = v;
            this.t = t;
        }

        String getV() {
            return v;
        }

        int getT() {
            return t;
        }
    }

    private Map<String, List<ValueAndTime>> map;

    /** Initialize your data structure here. */
    public TimeMap() {
        map = new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {
        if (map.containsKey(key)) {
            List<ValueAndTime> list = map.get(key);
            ValueAndTime tail = new ValueAndTime(value, timestamp);
            list.add(tail);
            map.put(key, list);
        } else {
            List<ValueAndTime> list = new ArrayList<>();
            ValueAndTime tail = new ValueAndTime(value, timestamp);
            list.add(tail);
            map.put(key, list);
        }
    }
    
    public String get(String key, int timestamp) {
        if (!map.containsKey(key)) {
            return "";
        } else {
            List<ValueAndTime> list = map.get(key);
            int size = list.size();
            ValueAndTime cur = null;
            String ans = "";
            for (int i = 0; i < size; i++) {
                cur = list.get(i);
                if (cur.getT() <= timestamp) {
                    ans = cur.getV();
                } else {
                    break;
                }
            }
            return ans;
        }
    }
}

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */