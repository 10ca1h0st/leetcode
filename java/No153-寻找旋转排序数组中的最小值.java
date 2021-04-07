class Solution {
    public int findMin(int[] nums) {
        int length = nums.length;
        int left = 0;
        int right = length-1;
        int mid = 0;
        while(left <= right) {
            mid = left + (right-left)/2;
            if(nums[mid] <= nums[right] && nums[mid] >= nums[left]) {
                return nums[left];
            }
            else if(nums[mid] > nums[right]) {
                left = mid + 1;
            }
            else if(nums[mid] < nums[left]) {
                left++;
            }
        }
        return 0;
    }
}