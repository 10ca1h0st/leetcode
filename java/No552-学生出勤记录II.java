class Solution {
    public int checkRecord(int n) {
        if (n == 1) {
            return 3;
        }
        // 第一维是天数，第二维是到当天为止的缺勤数（不包括当天），第三维代表当天的三种状态（依次按A L P编号）
        // dp[i][0][0]==>dp[i][0][A] dp[i][0][1]==>dp[i][0][L]
        int[][][] dp = new int[n][2][3];
        // 初始化dp[0]
        dp[0][0][0] = 1;
        dp[0][0][1] = 1;
        dp[0][0][2] = 1;
        dp[0][1][0] = 0;
        dp[0][1][1] = 0;
        dp[0][1][2] = 0;

        // 初始化dp[1]
        dp[1][0][0] = 2;
        dp[1][0][1] = 2;
        dp[1][0][2] = 2;
        dp[1][1][0] = 0;
        dp[1][1][1] = 1;
        dp[1][1][2] = 1;

        final int A = 0;
        final int L = 1;
        final int P = 2;
        final int MOD = (int)Math.pow(10, 9) + 7;
        for (int i = 2; i < n; i++) {
            // dp[i][0][A]
            dp[i][0][A] = (int)(((long)dp[i-1][0][L] + dp[i-1][0][P]) % MOD);
            // dp[i][0][L]
            dp[i][0][L] = (int)(((long)dp[i-2][0][P] + dp[i-2][0][P] + (long)dp[i-2][0][L]) % MOD);
            dp[i][0][P] = (int)(((long)dp[i-1][0][P] + dp[i-1][0][L]) % MOD);
            dp[i][1][A] = 0;
            dp[i][1][L] = (int)(((long)dp[i-2][1][P] + dp[i-2][1][P] + dp[i-2][0][P] + dp[i-2][0][L] + dp[i-2][1][L] + dp[i-2][0][A] + dp[i-2][0][A]) % MOD);
            dp[i][1][P] = (int)(((long)dp[i-1][1][P] + dp[i-1][1][L] + dp[i-1][0][A]) % MOD);
        }
        int ans = 0;
        for (int j = 0; j < 2; j++) {
            for (int k = 0; k < 3; k++) {
                ans = (int)(((long)ans + (long)dp[n-1][j][k]) % MOD);
            }
        }
        return ans;
    }
}