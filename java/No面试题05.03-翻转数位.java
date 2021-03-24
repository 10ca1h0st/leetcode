class Solution {
    public int reverseBits(int num) {
        int and = 1;
        int res = 0;
        for(int i = 0; i < 32; i++) {
            and = 1 << i;
            res = Math.max(res, getContinueOne(num|and));
        }
        return res;
    }

    public int getContinueOne(int num) {
        int res = 0;
        int con = 0;
        while(num != 0) {
            if((num&1) == 1) {
                con++;
            }
            else {
                res = Math.max(con, res);
                con = 0;
            }
            num = num >>> 1;
        }
        res = Math.max(res, con);
        return res;
    }
}