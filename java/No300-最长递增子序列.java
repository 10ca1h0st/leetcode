class Solution {
    public int lengthOfLIS(int[] nums) {
        // dp[i]代表以nums[i]结尾的最长递增子序列的长度
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int ret = 1;
        for(int i = 0; i < nums.length; i++) {
            for(int j = i+1; j < nums.length; j++) {
                if(nums[j] > nums[i]) {
                    // 注意，计算dp[j]时不能直接将dp[j]设为dp[i]+1
                    dp[j] = Math.max(dp[j], dp[i] + 1);
                    ret = Math.max(ret, dp[j]);
                }
            }
        }
        return ret;
    }
}