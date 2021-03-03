class Solution {
    public int[] countBits(int num) {
        int[] ret = new int[num+1];
        for(int i = 0; i <= num; i++) {
            ret[i] = countBit(i);
        }
        return ret;
    }
    public int countBit(int num) {
        int ret = 0;
        while(num != 0) {
            if((num & 1) != 0) {
                ret++;
            }
            num = num >> 1;
        }
        return ret;
    }
}

// 动态规划 最高有效位
class Solution {
    public int[] countBits(int num) {
        int[] bits = new int[num + 1];
        int highBit = 0;
        for (int i = 1; i <= num; i++) {
            if ((i & (i - 1)) == 0) {
                highBit = i;
            }
            bits[i] = bits[i - highBit] + 1;
        }
        return bits;
    }
}


// 动态规划 最低有效位
class Solution {
    public int[] countBits(int num) {
        int[] bits = new int[num + 1];
        for (int i = 1; i <= num; i++) {
            bits[i] = bits[i >> 1] + (i & 1);
        }
        return bits;
    }
}

// 动态规划 最低设置位
class Solution {
    public int[] countBits(int num) {
        int[] bits = new int[num + 1];
        for (int i = 1; i <= num; i++) {
            bits[i] = bits[i & (i - 1)] + 1;
        }
        return bits;
    }
}