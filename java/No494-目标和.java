class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for(int num : nums) {
            sum += num;
        }
        if(sum < target) {
            return 0;
        }
        if((sum - target) % 2 != 0) {
            return 0;
        }
        // sum > target，需要从nums找出和为(sum-target)/2的组合数
        // dp[i][j]表示从nums的前i个数，能够组成和为j的组合数
        int[][] dp = new int[nums.length+1][(sum-target)/2 + 1];
        for(int i = 0; i < (sum-target)/2 + 1; i++) {
            dp[0][i] = 0;
        }
        dp[0][0] = 1;
        // for(int i = 0; i < dp.length; i++) {
        //     dp[i][0] = 1;
        // }
        for(int i = 1; i < dp.length; i++) {
            for(int j = 0; j < (sum-target)/2 + 1; j++) {
                dp[i][j] = dp[i-1][j];
                if(nums[i-1] <= j) {
                    dp[i][j] += dp[i-1][j-nums[i-1]];
                }
            }
        }
        return dp[nums.length][(sum-target)/2];
    }
}