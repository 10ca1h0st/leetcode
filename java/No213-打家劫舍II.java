class Solution {
    public int rob(int[] nums) {
        if(nums.length == 1) {
            return nums[0];
        }
        int withoutHead = robAcyclic(Arrays.copyOfRange(nums, 1, nums.length));
        int withoutTail = robAcyclic(Arrays.copyOfRange(nums, 0, nums.length-1));
        return Math.max(withoutHead, withoutTail);
    }

    public int robAcyclic(int[] nums) {
        // dp[i]代表从第一个房子偷到第i个房子，最大的偷盗金额
        int[] dp = new int[nums.length + 1];
        dp[1] = nums[1-1];
        int max = dp[1];
        for(int i = 2; i < dp.length; i++) {
            dp[i] = Math.max(nums[i-1] + dp[i-2], dp[i-1]);
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}