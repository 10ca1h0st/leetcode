class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int ret = 0;
        int start = 0;
        while(start < nums.length && nums[start] != 1) {
            start++;
        }
        for(int i = start; i < nums.length; i++) {
            if(nums[i] != 1) {
                ret = Math.max(ret, i-start);
                start = i+1;
            }
        }
        // 每次写这种类型的题时，总是忘记在for循环外部更新值
        ret = Math.max(ret, nums.length-start);
        return ret;
    }
}