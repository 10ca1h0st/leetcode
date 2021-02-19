class Solution {
    public int longestOnes(int[] A, int K) {
        // [left, right)最多包含K个0
        int left = 0;
        int right = 0;
        int length = A.length;
        int ret = 0;
        int contains0 = 0;
        while(right < length) {
            if(A[right] == 0) {
                contains0++;
            }
            right++;
            while(contains0 > K) {
                if(A[left] == 0) {
                    contains0--;
                }
                left++;
            }
            ret = Math.max(ret, right-left);
        }
        ret = Math.max(ret, right-left);
        return ret;
    }
}