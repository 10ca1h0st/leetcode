class Solution {
    public int fib(int n) {
        if(n == 0) {
            return 0;
        }
        if(n == 1) {
            return 1;
        }
        int[] fibnums = new int[n+1];
        fibnums[0] = 0;
        fibnums[1] = 1;
        int i = 2;
        while(i < n+1) {
            fibnums[i] = fibnums[i-1] + fibnums[i-2];
            i++;
        }
        return fibnums[n];
    }
}