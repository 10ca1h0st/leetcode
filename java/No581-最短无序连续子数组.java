class Solution {
    public int findUnsortedSubarray(int[] nums) {
        // dp[i][j]表示nums[i...j]这个子数组的最短无序连续子数组的长度
        int[][] dp = new int[2][nums.length];
        dp[0][nums.length-1] = 0;
        // dp[i][j]; i <= j
        int max = -100001;
        int min = 100001;
        for (int i = nums.length-2; i >= 0; i--) {
            // 目前要编辑的行号
            int curI = (nums.length-1-i)%2;
            max = -100001;
            min = 100001;
            max = Math.max(max, nums[i]);
            min = Math.min(min, nums[i]);
            for (int j = i+1; j <= nums.length-1; j++) {
                max = Math.max(max, nums[j]);
                min = Math.min(min, nums[j]);
                if (j == i+1) {
                    if (nums[i] <= nums[j]) {
                        dp[curI][j] = 0;
                    } else {
                        dp[curI][j] = 2;
                    }
                } else {
                    // j-i > 1
                    if (nums[i] <= min && nums[j] >= max) {
                        dp[curI][j] = dp[(curI+1)%2][j-1];
                    } else if (nums[i] <= min) {
                        dp[curI][j] = dp[(curI+1)%2][j];
                    } else if (nums[j] >= max) {
                        dp[curI][j] = dp[curI][j-1];
                    } else {
                        dp[curI][j] = j-i+1;
                    }
                }
            }
        }
        return dp[(nums.length-1)%2][nums.length-1];
    }
}

// 超时
class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int prev = -100001;
        for (int i = 0; i < nums.length; i++) {
            if (prev > nums[i]) {
                break;
            }
            prev = nums[i];
            if (i == nums.length-1) {
                return 0;
            }
        }
        int length = 0;
        for (length = 2; length <= nums.length; length++) {
            int start = 0;
            for (start = 0; start+length-1 < nums.length; start++) {
                int min = 100001;
                int max = -100001;
                for (int i = start; i <= start+length-1; i++) {
                    min = Math.min(min, nums[i]);
                    max = Math.max(max, nums[i]);
                }
                prev = -100001;
                boolean fail = false;
                for (int i = 0; i < start; i++) {
                    if (prev > nums[i] || nums[i] > min) {
                        fail = true;
                        break;
                    }
                    prev = nums[i];
                }
                if (fail) {
                    continue;
                }
                prev = max;
                for (int i = start+length; i < nums.length; i++) {
                    if (prev > nums[i] || nums[i] < max) {
                        fail = true;
                        break;
                    }
                    prev = nums[i];
                }
                if (fail) {
                    continue;
                }
                return length;
            }
        }
        return -1;
    }
}