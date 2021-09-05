class Solution {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int mid = -1;
        while (left <= right) {
            mid = left + (right-left)/2;
            if (nums[mid] < target) {
                left = mid+1;
                // left++; // 可以用 但是慢
            } else if (nums[mid] > target) {
                right = mid-1;
                // right--;
            } else {
                return mid;
            }
        }
        return -1;
    }
}