class Solution {
    public int getMaximumGenerated(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int end = 0;
        if (n%2 == 0) {
            end = n / 2;
        } else {
            end = n/2 + 1;
        }
        int[] nums = new int[end+1];
        nums[0] = 0;
        nums[1] = 1;
        for (int i = 2; i <= end; i++) {
            if (i%2 == 0) {
                nums[i] = nums[i/2];
            } else {
                nums[i] = nums[i/2] + nums[i/2 + 1];
            }
        }
        int max = 1;
        for (int i = 1; i < end; i++) {
            max = Math.max(nums[i]+nums[i+1], max);
        }
        return max;
    }
}