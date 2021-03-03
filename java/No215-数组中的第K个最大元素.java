class Solution {
    public int findKthLargest(int[] nums, int k) {
        int start = 0;
        int end = nums.length - 1;
        int target = nums.length - k;
        int i = partition(nums, start, end);
        while(i != target) {
            if(i > target) {
                end = i - 1;
                i = partition(nums, start, end);
            }
            else {
                start = i + 1;
                i = partition(nums, start, end);
            }
        }
        return nums[i];
    }

    // 以nums[end]为flag，以flag为中间元素将nums[start...end]分割为两部分，并返回最终flag应放置位置的索引，注意end>=start
    public int partition(int[] nums, int start, int end) {
        int flag = nums[end];
        int i = start - 1;
        // 小于等于索引i的地方放着小于flag的元素
        for(int j = start; j < end; j++) {
            if(nums[j] < flag) {
                swap(nums, ++i, j);
            }
        }
        swap(nums, i+1, end);
        return i+1;
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}