class Solution {
    public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
        if((n == 1 && target > 1) || m < target) {
            return -1;
        }
        // dp[i][j][k]代表第i个房子涂成第j+1个颜色，组成了k个街区，所花费的价值
        int[][][] dp = new int[m][n][target+1];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                Arrays.fill(dp[i][j], Integer.MAX_VALUE);
            }
        }
        if(houses[0] != 0) {
            dp[0][houses[0]-1][1] = 0;
        }
        else {
            for(int j = 0; j < n; j++) {
                dp[0][j][1] = cost[0][j];
            }
        }
        for(int i = 1; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(houses[i] != 0 && houses[i] != j+1) {
                    continue;
                }
                // 第i个房子可以涂第j+1个颜色
                for(int k = 1; k <= Math.min(i+1, target); k++) {
                    // dp[i][j][k] = dp[i-1][0...j-1, j+1...n-1][k-1] + dp[i-1][j][k]
                    int curCost = 0;
                    if(houses[i] != j+1) {
                        curCost = cost[i][j];
                    }
                    if(k == 1) {
                        dp[i][j][1] = (int)Math.min(dp[i][j][1], (long)dp[i-1][j][1] + curCost);
                    }
                    else {
                        for(int prevColor = 0; prevColor < n; prevColor++) {
                            if(prevColor == j) {
                                dp[i][j][k] = (int)Math.min(dp[i][j][k], (long)dp[i-1][prevColor][k] + curCost);
                            }
                            else {
                                dp[i][j][k] = (int)Math.min(dp[i][j][k], (long)dp[i-1][prevColor][k-1] + curCost);
                            }
                        }
                    }
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        for(int j = 0; j < n; j++) {
            ans = Math.min(ans, dp[m-1][j][target]);
        }
        if(ans == Integer.MAX_VALUE) {
            return -1;
        }
        return ans;
    }
}