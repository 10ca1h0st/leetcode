class Solution {
    public int maxProfit(int[] prices) {
        int days = prices.length;
        int kmax = 2;
        // dp[i][k][0] : 第i天，进行了k次交易（买入算一次交易），目前状态为不持有（1为持有）
        int[][][] dp = new int[days+1][kmax+1][2];

        // init case
        dp[0][0][0] = 0;
        dp[0][0][1] = Integer.MIN_VALUE;
        for(int k = 1; k <= kmax; k++) {
            dp[0][k][0] = Integer.MIN_VALUE;
            dp[0][k][1] = Integer.MIN_VALUE;
        }
        for(int i = 1; i <= days; i++) {
            dp[i][0][0] = 0;
            dp[i][0][1] = Integer.MIN_VALUE;
        }

        for(int i = 1; i <= days; i++) {
            for(int k = 1; k <= kmax; k++) {
                dp[i][k][0] = Math.max(
                    dp[i-1][k][0],
                    dp[i-1][k][1] + prices[i-1]
                );
                dp[i][k][1] = (int)Math.max(
                    dp[i-1][k][1],
                    (long)dp[i-1][k-1][0] - prices[i-1]
                );
            }
        }
        int ret = 0;
        for(int i = 1; i <= days; i++) {
            for(int k = 0; k <= kmax; k++) {
                ret = Math.max(
                    ret,
                    dp[i][k][0]
                );
            }
        }
        return ret;
    }
}