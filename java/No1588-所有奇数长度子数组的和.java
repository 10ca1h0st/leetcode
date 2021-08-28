class Solution {
    public int sumOddLengthSubarrays(int[] arr) {
        int[] prefixSum = new int[arr.length + 1];
        prefixSum[0] = 0;
        int sum = 0;
        for (int i = 1; i < prefixSum.length; i++) {
            sum += arr[i-1];
            prefixSum[i] = sum;
        }
        int ans = 0;
        for (int i = 1; i <= arr.length; ) {
            for (int j = 0; j+i <= arr.length; j++) {
                ans += prefixSum[j+i] - prefixSum[j];
            }
            i += 2;
        }
        return ans;
    }
}