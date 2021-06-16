// 我的方法，本来超时，看了题解之后，添加了缓存dp，通过
class Solution {
    public boolean stoneGame(int[] piles) {
        int[] prefixSum = new int[piles.length + 1];
        int sum = 0;
        for(int i = 1; i <= piles.length; i++) {
            prefixSum[i] = sum + piles[i-1];
            sum = prefixSum[i];
        }
        int[][] dp = new int[piles.length][piles.length];
        int ans = backtrace(piles, prefixSum, 0, piles.length-1, dp);
        if(ans > sum - ans) {
            return true;
        }
        return false;
    }

    public int backtrace(int[] piles, int[] prefixSum, int start, int end, int[][] dp) {
        if(start > end) {
            return 0;
        }
        if(dp[start][end] != 0) {
            return dp[start][end];
        }
        int ans = 0;
        int sum = prefixSum[end+1] - prefixSum[start];
        // 选第一个
        ans = piles[start] + sum - piles[start] - backtrace(piles, prefixSum, start+1, end, dp);
        // 选最后一个
        ans = Math.max(ans, piles[end] + sum - piles[end] - backtrace(piles, prefixSum, start, end-1, dp));
        dp[start][end] = ans;
        return ans;
    }
}

// 官方解答
class Solution {
    public boolean stoneGame(int[] piles) {
        int length = piles.length;
        int[][] dp = new int[length][length];
        for (int i = 0; i < length; i++) {
            dp[i][i] = piles[i];
        }
        for (int i = length - 2; i >= 0; i--) {
            for (int j = i + 1; j < length; j++) {
                dp[i][j] = Math.max(piles[i] - dp[i + 1][j], piles[j] - dp[i][j - 1]);
            }
        }
        return dp[0][length - 1] > 0;
    }
}

作者：LeetCode-Solution
链接：https://leetcode-cn.com/problems/stone-game/solution/shi-zi-you-xi-by-leetcode-solution/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。