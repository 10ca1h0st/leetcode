class Solution {
    public int singleNumber(int[] nums) {
        int res = 0;
        for(int i = 0; i < 32; i++) {
            int ith = 0;
            for(int num : nums) {
                if((num & (1 << i)) != 0) {
                    ith++;
                }
            }
            ith = ith % 3;
            res = res | (ith << i);
        }
        return res;
    }
}