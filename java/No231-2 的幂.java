class Solution {
    public boolean isPowerOfTwo(int n) {
        if(n <= 0) {
            return false;
        }
        n = n & (n-1);
        if(n == 0) {
            return true;
        }
        return false;
    }
}