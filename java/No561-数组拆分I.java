class Solution {
    public int arrayPairSum(int[] nums) {
        int length = nums.length;
        int ret = 0;
        Arrays.sort(nums);
        for(int i = length-1; i >=0; ) {
            // 目前的二元组(nums[i], nums[i-1])
            ret += nums[i-1];
            i -= 2;
        }
        return ret;
    }
}