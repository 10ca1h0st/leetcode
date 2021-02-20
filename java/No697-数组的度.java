class Solution {
    public int findShortestSubArray(int[] nums) {
        int left = 0;
        int right = 0;
        int length = nums.length;
        int maxNum = -1;
        for(int num : nums) {
            maxNum = Math.max(maxNum, num);
        }
        int[] counter = new int[maxNum + 1];
        int max = 0;
        for(int num : nums) {
            counter[num]++;
            max = Math.max(max, counter[num]);
        }
        // System.out.println(max);
        counter = new int[maxNum + 1];
        int key = -1;
        int ret = length;
        while(right < length) {
            counter[key=nums[right]]++;
            right++;
            if(counter[key] == max) {
                while(nums[left] != key) {
                    counter[nums[left]]--;
                    left++;
                }
                ret = Math.min(ret, right - left);
            }
        }
        return ret;
    }
}