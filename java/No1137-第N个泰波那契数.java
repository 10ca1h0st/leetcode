class Solution {
    private Map<Integer, Integer> cache;

    public int tribonacci(int n) {
        cache = new HashMap<>();
        cache.put(0, 0);
        cache.put(1, 1);
        cache.put(2, 1);
        return cal(n);
    }

    private int cal(int n) {
        if (cache.containsKey(n)) {
            return cache.get(n);
        }
        int res = cal(n-3) + cal(n-2) + cal(n-1);
        cache.put(n, res);
        return res;
    }
}