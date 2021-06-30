class Solution {
    public int numWays(int n, int[][] relation, int k) {
        List<Integer>[] graph = new List[n];
        for(int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for(int[] edge : relation) {
            graph[edge[0]].add(edge[1]);
        }
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(0);
        int step = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size > 0) {
                int from = queue.poll();
                for(int to : graph[from]) {
                    queue.add(to);
                }
                size--;
            }
            step++;
            if(step == k) {
                int ans = 0;
                for(int id : queue) {
                    if(id == n-1) {
                        ans++;
                    }
                }
                return ans;
            }
        }
        return 0;
    }
}

// 官方题解，动态规划
class Solution {
    public int numWays(int n, int[][] relation, int k) {
        int[][] dp = new int[k + 1][n];
        dp[0][0] = 1;
        for (int i = 0; i < k; i++) {
            for (int[] edge : relation) {
                int src = edge[0], dst = edge[1];
                dp[i + 1][dst] += dp[i][src];
            }
        }
        return dp[k][n - 1];
    }
}