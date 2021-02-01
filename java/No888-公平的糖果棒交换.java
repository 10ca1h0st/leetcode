class Solution {
    public int[] fairCandySwap(int[] A, int[] B) {
        int sumA = 0;
        int sumB = 0;
        for(int a : A) {
            sumA += a;
        }
        for(int b : B) {
            sumB += b;
        }
        int[] ret = new int[2];
        Arrays.fill(ret, -1);
        for(int i = 0; i < A.length; i++) {
            int a = A[i];
            for(int j = 0; j < B.length; j++) {
                int b = B[j];
                if(sumA - a + b == sumB -b + a) {
                    ret[0] = a;
                    ret[1] = b;
                    return ret;
                }
            }
        }
        return ret;
    }
}