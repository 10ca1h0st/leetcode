class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> counter = new HashMap<>();
        for(String word : words) {
            int v = counter.getOrDefault(word, 0);
            v++;
            counter.put(word, v);
        }
        Map<Integer, List<String>> count2List = new HashMap<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>(k);
        for(Map.Entry<String, Integer> entry : counter.entrySet()) {
            String key = entry.getKey();
            int v = entry.getValue();
            List<String> list = count2List.getOrDefault(v, new ArrayList<String>());
            list.add(key);
            count2List.put(v, list);
            if(pq.size() < k) {
                pq.add(v);
            }
            else {
                if(v > pq.peek()) {
                    pq.poll();
                    pq.add(v);
                }
            }
        }
        Deque<Integer> topKV = new ArrayDeque<>();
        while(!pq.isEmpty()) {
            topKV.offerFirst(pq.poll());
        }
        List<String> ans = new ArrayList<>();
        int prev = -1;
        for(int v : topKV) {
            List<String> ss = count2List.get(v);
            if(prev != v) {
                Collections.sort(ss);
            }
            ans.add(ss.get(0));
            ss.remove(0);
            count2List.put(v, ss);
            prev = v;
        }
        return ans;
    }
}