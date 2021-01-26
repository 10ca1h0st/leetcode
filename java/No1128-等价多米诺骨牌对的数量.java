class Solution {
    public int numEquivDominoPairs(int[][] dominoes) {
        int[] count = new int[100];
        for(int[] d : dominoes) {
            count[d[0] * 10 + d[1]]++;
        }
        int ret = 0;
        ret += sumToN(count[0]-1);
        for(int i = 1; i < 100; i++) {
            // System.out.println("i is "+i+" reverse(i) is "+reverse(i));
            int reverseI = reverse(i);
            if(reverseI == i) {
                ret += sumToN(count[i] - 1);
            }
            else {
                ret += sumToN(count[i] + count[reverseI] - 1);
            }
            // System.out.println("count[i] is "+count[i]+" count[reverse(i)] is "+count[reverse(i)]);
            count[i] = 0;
            count[reverse(i)] = 0;
        }
        return ret;
    }

    // 计算1+2+3+...+n
    int sumToN(int n) {
        if(n <= 0) {
            return 0;
        }
        return (1 + n) * n / 2;
    }

    // 将整数的十位与各位互换，例如01->10，21->12
    int reverse(int n) {
        int i = n % 10;
        int j = n / 10;
        return i * 10 + j;
    }
}