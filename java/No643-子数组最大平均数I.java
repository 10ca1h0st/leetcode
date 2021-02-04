class Solution {
    public double findMaxAverage(int[] nums, int k) {
        int kSum = 0;
        int maxKSum = 0;
        for(int i = 0; i < k; i++) {
            kSum += nums[i];
        }
        maxKSum = kSum;
        int del = 0;
        for(int i = k; i < nums.length; i++, del++) {
            kSum -= nums[del];
            kSum += nums[i];
            maxKSum = Math.max(maxKSum, kSum);
        }
        return maxKSum / (double)k;
    }
}