class Solution {
    public int missingNumber(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        return (1+nums.length)*nums.length/2-sum;
    }
}