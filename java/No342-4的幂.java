class Solution {
    public boolean isPowerOfFour(int n) {
        if(n <= 0) {
            return false;
        }
        // 首先判断是否为2的幂次方
        int _n = n & (n-1);
        if(_n != 0) {
            return false;
        }
        // 是2的多少幂次方
        int pow = -1;
        while(n != 0) {
            n = n >> 1;
            pow++;
        }
        if(pow % 2 == 0) {
            return true;
        }
        return false;
    }
}