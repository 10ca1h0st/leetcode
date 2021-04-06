class Solution {
    public int removeDuplicates(int[] nums) {
        int slow = 1;
        int fast = 1;
        int length = nums.length;
        if(length <= 1) {
            return length;
        }
        while(fast < length) {
            if(nums[fast] != nums[slow-1]) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }
}