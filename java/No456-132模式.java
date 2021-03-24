class Solution {
    public boolean find132pattern(int[] nums) {
        if(nums.length < 3) {
            return false;
        }
        int[] dp1 = new int[nums.length];
        Arrays.fill(dp1, Integer.MAX_VALUE);
        int min = nums[0];
        for(int i = 1; i < nums.length; i++) {
            if(nums[i] > min) {
                dp1[i] = min;
            }
            min = Math.min(min, nums[i]);
        }
        boolean res = false;
        outter:
        for(int i = 2; i < nums.length; i++) {
            for(int j = i-1; j >= 1; j--) {
                if(nums[i] < nums[j] && dp1[j] < nums[i]) {
                    res = true;
                    break outter;
                }
            }
        }
        return res;
    }
}