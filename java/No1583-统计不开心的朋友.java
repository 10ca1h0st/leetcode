class Solution {
    public int unhappyFriends(int n, int[][] preferences, int[][] pairs) {
        Map<Integer, Map<Integer, Integer>> love = new HashMap<>();
        for (int i = 0; i < preferences.length; i++) {
            int[] preference = preferences[i];
            Map<Integer, Integer> v = love.getOrDefault(i, new HashMap<>());
            for (int j = 0; j < preference.length; j++) {
                v.put(preference[j], preference.length-j);
            }
            love.put(i, v);
        }
        boolean[] unhappy = new boolean[n];
        int ans = 0;
        for (int i = 0; i < pairs.length; i++) {
            for (int j = i + 1; j < pairs.length; j++) {
                judge(pairs, i, j, love, unhappy);
            }
        }
        for (boolean b : unhappy) {
            if (b) {
                ans++;
            }
        }
        return ans;
    }

    private int judge(int[][] pairs, int i, int j, Map<Integer, Map<Integer, Integer>> love, boolean[] unhappy) {
        int[] pair1 = pairs[i];
        int[] pair2 = pairs[j];
        int x = pair1[0];
        int y = pair1[1];
        int u = pair2[0];
        int v = pair2[1];
        int res = 0;
        if (love.get(x).get(u) > love.get(x).get(y) && love.get(u).get(x) > love.get(u).get(v)) {
            unhappy[x] = true;
            unhappy[u] = true;
        }
        if (love.get(x).get(v) > love.get(x).get(y) && love.get(v).get(x) > love.get(v).get(u)) {
            unhappy[x] = true;
            unhappy[v] = true;
        }
        if (love.get(y).get(u) > love.get(y).get(x) && love.get(u).get(y) > love.get(u).get(v)) {
            unhappy[y] = true;
            unhappy[u] = true;
        }
        if (love.get(y).get(v) > love.get(y).get(x) && love.get(v).get(y) > love.get(v).get(u)) {
            unhappy[y] = true;
            unhappy[v] = true;
        }
        return res;
    }
}