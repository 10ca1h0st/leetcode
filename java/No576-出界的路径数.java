class Solution {
    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        // dp[i][j][k]表示从(i, j)坐标出发，经过k次移动，能够出界的路径数
        int[][][] dp = new int[m][n][maxMove+1];
        int MOD = (int)Math.pow(10, 9) + 7;
        int ans = 0;
        for (int k = 1; k <= maxMove; k++) {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (k == 1) {
                        int v = 0;
                        // 向上
                        if (i-1 < 0) {
                            v++;
                        }
                        // 向下
                        if (i+1 >= m) {
                            v++;
                        }
                        // 向左
                        if (j-1 < 0) {
                            v++;
                        }
                        // 向右
                        if (j+1 >= n) {
                            v++;
                        }
                        dp[i][j][k] = v;
                        if (i == startRow && j == startColumn) {
                            ans = (int)((ans + (long)v) % MOD);
                        }
                    } else {
                        int v = 0;
                        if (i-1 >= 0) {
                            v = (int)(((long)v + dp[i-1][j][k-1]) % MOD);
                        }
                        if (i + 1 < m) {
                            v = (int)(((long)v + dp[i+1][j][k-1]) % MOD);
                        }
                        if (j-1 >= 0) {
                            v = (int)(((long)v + dp[i][j-1][k-1]) % MOD);
                        }
                        if (j+1 < n) {
                            v = (int)(((long)v + dp[i][j+1][k-1]) % MOD);
                        }
                        dp[i][j][k] = v;
                        if (i == startRow && j == startColumn) {
                            ans = (int)((ans + (long)v) % MOD);
                        }
                    }
                }
            }
        }
        return ans;
    }
}