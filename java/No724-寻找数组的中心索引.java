class Solution {
    public int pivotIndex(int[] nums) {
        int sum = 0;
        for(int num : nums) {
            sum += num;
        }
        int leftSum = 0;
        for(int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            int rightSum = sum - leftSum - cur;
            if(leftSum == rightSum) {
                return i;
            }
            leftSum += cur;
        }
        return -1;
    }
}