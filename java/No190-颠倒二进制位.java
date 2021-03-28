public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        boolean[] one = new boolean[32];
        for(int i = 0; i < 32; i++) {
            boolean flag = ((n>>>i)&1) == 1;
            if(flag) {
                one[i] = true;
            }
        }
        int res = 0;
        for(int i = 0; i < 32; i++) {
            if(one[i]) {
                res = res|(1<<(31-i));
            }
        }
        return res;
    }
}

public class Solution {
    private static final int M1 = 0x55555555; // 01010101010101010101010101010101
    private static final int M2 = 0x33333333; // 00110011001100110011001100110011
    private static final int M4 = 0x0f0f0f0f; // 00001111000011110000111100001111
    private static final int M8 = 0x00ff00ff; // 00000000111111110000000011111111

    public int reverseBits(int n) {
        n = n >>> 1 & M1 | (n & M1) << 1;
        n = n >>> 2 & M2 | (n & M2) << 2;
        n = n >>> 4 & M4 | (n & M4) << 4;
        n = n >>> 8 & M8 | (n & M8) << 8;
        return n >>> 16 | n << 16;
    }
}

作者：LeetCode-Solution
链接：https://leetcode-cn.com/problems/reverse-bits/solution/dian-dao-er-jin-zhi-wei-by-leetcode-solu-yhxz/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。