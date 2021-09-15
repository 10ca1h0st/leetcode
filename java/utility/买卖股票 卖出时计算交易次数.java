class Solution {
    public int maxProfit(int k, int[] prices) {
        // dp[i][j][0]表示到了第i天结束，目前为止进行了最多j次卖出，目前不持有股票，此时的收益
        int[][][] dp = new int[prices.length+1][k+1][2];
        for (int j = 0; j <= k; j++) {
            dp[0][j][1] = Integer.MIN_VALUE;
        }
        for (int i = 1; i <= prices.length; i++) {
            for (int j = 0; j <= k; j++) {
                if (j == 0) {
                    dp[i][0][0] = 0;
                    dp[i][0][1] = Math.max(dp[i-1][0][1], dp[i-1][0][0] - prices[i-1]);
                } else {
                    dp[i][j][0] = Math.max(dp[i-1][j][0], dp[i-1][j-1][1] + prices[i-1]);
                    dp[i][j][1] = Math.max(dp[i-1][j][1], dp[i-1][j][0] - prices[i-1]);
                }
            }
        }
        return dp[prices.length][k][0];
    }
}