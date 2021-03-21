public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        if(n == 0) {
            return 0;
        }
        int res = 1;
        while((n&(n-1)) != 0) {
            res++;
            n = n&(n-1);
        }
        return res;
    }
}