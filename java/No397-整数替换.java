class Solution {
    Map<Integer, Integer> cache;
    public int integerReplacement(int n) {
        cache = new HashMap<>();
        return dfs(n);
    }

    public int dfs(int n) {
        if (n == 1) {
            return 0;
        }
        if (cache.containsKey(n)) {
            return cache.get(n);
        }
        int ans = 0;
        if ((n&1) == 0) {
            int _n = n>>1;
            ans = dfs(_n)+1;
        } else {
            if (n == Integer.MAX_VALUE) {
                ans = dfs((n-1)/2)+1;
                cache.put(n, ans);
                return ans;
            }
            int minus1 = n-1;
            int plus1 = n+1;
            int lowbot_minus1 = minus1&(0-minus1);
            int lowbot_plus1 = plus1&(0-plus1);
            if (lowbot_minus1 == minus1) {
                ans = dfs(minus1)+1;
            } else if (lowbot_plus1 == plus1) {
                ans = dfs(plus1)+1;
            } else if (lowbot_minus1 > lowbot_plus1) {
                ans = dfs(minus1)+1;
            } else {
                ans = dfs(plus1)+1;
            }
        }
        cache.put(n, ans);
        return ans;
    }
}