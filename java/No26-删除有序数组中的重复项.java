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

// 2021-4-18 再次做题
class Solution {
    public int removeDuplicates(int[] nums) {
        if(nums.length <= 1) {
            return nums.length;
        }
        int slow = 1;
        int fast = 1;
        while(fast < nums.length) {
            if(nums[fast] != nums[fast-1]) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }
}