class Solution {
    public int findLengthOfLCIS(int[] nums) {
        int ret = 0;
        for(int i = 0; i < nums.length; ) {
            int j = i + 1;
            for(; j < nums.length; j++) {
                if(nums[j-1] >= nums[j]) {
                    break;
                }
            }
            ret = Math.max(ret, j - i);
            i = j;
        }
        return ret;
    }
}