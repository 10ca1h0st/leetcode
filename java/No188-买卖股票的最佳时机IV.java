class Solution {
    public int maxProfit(int k, int[] prices) {
        int days = prices.length;
        int[][][] dp = new int[days+1][k+1][2];
        // dp[i][k][0]代表在第i天结束时，已经交易了k次，当前的状态为不持有股票，此时的利润
        // 买入股票再卖出股票算一次交易，因此k的数量，只有在买入股票的时候会变化
        // base case
        int i = 0;
        for(i = 0; i < days+1; i++) {
            // 没进行一次交易，不可能持有股票
            dp[i][0][1] = Integer.MIN_VALUE;
        }

        // 第i天，没进行一次交易，状态为不持有股票，利润为0
        // dp[i][0][0] == 0, no need to set

        for(i = 1; i < k+1; i++) {
            // 第0天（就是还没开始），不可能进行交易
            dp[0][i][0] = Integer.MIN_VALUE;
        }

        for(i =0 ; i < k+1; i++) {
            // 第0天，当k为0时，代表没进行交易，因此不可能持有股票，当k大于0时，因为第0天相当于还没开始，不能交易
            dp[0][i][1] = Integer.MIN_VALUE;
        }

        int day = 0;
        int kIndex = 0;
        for(day = 1; day < days+1; day++) {
            for(kIndex = 1; kIndex < k+1; kIndex++) {
                dp[day][kIndex][0] = Math.max(
                    dp[day-1][kIndex][0],
                    dp[day-1][kIndex][1] + prices[day-1]
                );
                dp[day][kIndex][1] = (int)Math.max(
                    dp[day-1][kIndex][1],
                    (long)dp[day-1][kIndex-1][0] - prices[day-1]
                );
            }
        }

        int ret = Integer.MIN_VALUE;
        for(i = 0; i < k+1; i++) {
            ret = Math.max(ret, dp[days][i][0]);
        }
        return ret;
    }
}