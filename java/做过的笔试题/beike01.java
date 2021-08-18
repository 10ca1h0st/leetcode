import java.util.*;


public class beike01 {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param n int整型
     * @param m long长整型
     * @return long长整型一维数组
     */
    public long[] FarmerNN (int n, long m) {
        // write code here
        long[] ans = new long[n];
        long base = 0;
        long _m = m;
        base = 2 * (_m / (n+n-2));
        _m = _m % (n+n-2);
        ans[0] = base/2;
        ans[n-1] = base/2;
        for (int i = 1; i < n-1; i++) {
            ans[i] = base;
        }
        int index = 0;
        boolean add = true;
        while (_m > 0) {
            ans[index] += 1;
            _m -= 1;
            if (add) {
                index++;
            } else {
                index--;
            }
            if (index == n) {
                index = n-2;
                add = false;
            }
            if (index == -1) {
                index = 1;
                add = true;
            }
        }
        return ans;
    }
}