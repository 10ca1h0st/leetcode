class Solution {
    public int longestSubarray(int[] nums, int limit) {
        int length = nums.length;
        // [left, right)表示连续最长子数组
        int left = 0;
        int right = 0;
        int ret = 0;
        PriorityQueue<Integer> min = new PriorityQueue<>();
        PriorityQueue<Integer> max = new PriorityQueue<>(Collections.reverseOrder());
        while(right < length) {
            min.offer(nums[right]);
            max.offer(nums[right]);
            right++;
            // [left, right)
            while(max.peek() - min.peek() > limit) {
                // nums[right]是max.peek()或者min.peek()
                max.remove(nums[left]);
                min.remove(nums[left]);
                left++;
            }
            ret = Math.max(ret, right - left);
        }
        return ret;
    }
}