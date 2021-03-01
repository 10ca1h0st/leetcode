class NumArray {

    private int[] prefixSum;

    public NumArray(int[] nums) {
        prefixSum = new int[nums.length];
        // prefixSum[i] = nums[0] + nums[1] + ... + nums[i]
        int sum = 0;
        for(int i = 0; i < nums.length; i++) {
            sum += nums[i];
            prefixSum[i] = sum;
        }
    }
    
    public int sumRange(int i, int j) {
        if(i == 0) {
            return prefixSum[j];
        }
        return prefixSum[j] - prefixSum[i-1];
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(i,j);
 */