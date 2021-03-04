// 动态规划会超时
class Solution {
    public int jump(int[] nums) {
        int length = nums.length;
        if(length <= 1) {
            return 0;
        }
        // dp[i]代表从位置i到最后位置所需要的最少跳数
        int[] dp = new int[length];
        Arrays.fill(dp, length);
        // base case
        dp[length-1] = 0;
        for(int i = length - 2; i >=0; i--) {
            if(i + nums[i] >= length - 1) {
                // 直接从i到最后
                dp[i] = 1;
                continue;
            }
            for(int j = i + 1; j < length - 1; j++) {
                if(i + nums[i] >= j && dp[j] < length) {
                    // 从i到j再到最后
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }
        return dp[0];
    }
	
	// 使用贪心算法
	public int jump(int[] nums) {
        int length = nums.length;
        int end = 0, farthest = 0;
        int jump = 0;
        for(int i = 0; i < length - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);
            if(end == i) {
                jump++;
                end = farthest;
            }
        }
        return jump;
    }
}