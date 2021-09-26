class Solution {
    public int getSum(int a, int b) {
        int ans = 0;
        int i = 0;
        boolean add = false;
        // a&(1<<i)
        while (i <= 31) {
            int bitA = a&(1<<i);
            int bitB = b&(1<<i);
            if ((bitA&bitB) != 0) {
                if (add) {
                    ans = ans | (1<<i);
                }
                add = true;
            } else if ((bitA|bitB) != 0) {
                if (!add) {
                    ans = ans | (1<<i);
                } else {
                    add = true;
                }
            } else {
                if (add) {
                    ans = ans | (1<<i);
                    add = false;
                }
            }
            i++;
        }
        return ans;
    }
}