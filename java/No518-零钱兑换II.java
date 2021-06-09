class Solution {
    public int change(int amount, int[] coins) {
        int types = coins.length;
        // dp[i][j]表示使用前i个硬币，能凑出价值为j的金额的方法数
        int[][] dp = new int[types+1][amount+1];
        for(int i = 0; i < dp.length; i++) {
            dp[i][0] = 1;
        }
        for(int i = 1; i < dp.length; i++) {
            for(int j = 1; j <= amount; j++) {
                // dp[i][j] = dp[i-1][j] + dp[i][j-coins[i-1]]
                dp[i][j] = dp[i-1][j];
                if(coins[i-1] <= j) {
                    dp[i][j] += dp[i][j-coins[i-1]];
                }
            }
        }
        return dp[types][amount];
    }
}