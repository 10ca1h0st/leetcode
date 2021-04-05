class Solution {
    public int removeDuplicates(int[] nums) {
        int copyIndex = 0;
        int prev = 0;
        int count = 1;
        for(int i = 0; i < nums.length; ) {
            i++;
            while(i < nums.length && nums[i] == nums[prev]) {
                count++;
                i++;
            }
            for(int j = 0; j < Math.min(count, 2); j++) {
                nums[copyIndex] = nums[prev];
                copyIndex++;
            }
            prev = i;
            count = 1;
        }
        return copyIndex;
    }
}