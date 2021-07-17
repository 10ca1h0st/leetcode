class Solution {
    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        int ans = 0;
        dp[0] = nums[0];
        ans = dp[0];
        for (int i = 1; i < dp.length; i++) {
            dp[i] = nums[i];
            if (dp[i-1] > 0) {
                dp[i] += dp[i-1];
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}