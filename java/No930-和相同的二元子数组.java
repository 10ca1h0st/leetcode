// 使用前缀和数组
class Solution {
    public int numSubarraysWithSum(int[] nums, int goal) {
        int length = nums.length;
        if (length < goal) {
            return 0;
        }
        int[] prefix = new int[length+1];
        int sum = 0;
        for (int i = 0; i < length; i++) {
            sum += nums[i];
            prefix[i+1] = sum;
        }
        if (sum < goal) {
            return 0;
        }
        int ans = 0;
        for (int i = length; i >= 1; i--) {
            for (int j = i-1; j >=0; j--) {
                if (prefix[i] - prefix[j] > goal) {
                    break;
                }
                if (prefix[i] - prefix[j] == goal) {
                    ans++;
                }
            }
        }
        return ans;
    }
}

// 超时
class Solution {
    public int numSubarraysWithSum(int[] nums, int goal) {
        int length = nums.length;
        if (length < goal) {
            return 0;
        }
        // dp[i][j]表示已nums[i]结尾，和为j的子数组的个数
        int[][] dp = new int[length][goal+1];
        if (goal >= nums[0]) {
            dp[0][nums[0]] = 1;
        }
        int ans = 0;
        ans += dp[0][goal];
        for (int i = 1; i < length; i++) {
            if (nums[i] == 0) {
                dp[i][0] = dp[i-1][0] + 1;
            }
            for (int j = 1; j <= goal; j++) {
                if (nums[i] == 0) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j] = dp[i-1][j-1];
                    if (j == 1) {
                        dp[i][j] += 1;
                    }
                }
            }
            ans += dp[i][goal];
        }
        return ans;
    }
}