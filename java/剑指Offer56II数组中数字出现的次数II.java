class Solution {
    public int singleNumber(int[] nums) {
        int[] bitMasks = new int[32];
        for(int num : nums) {
            int i = 32;
            while(i-- > 0) {
                if((num & (1<<i)) != 0) {
                    bitMasks[i]++;
                }
            }
        }
        int res = 0;
        for(int i = 0; i < 32; i++) {
            if(bitMasks[i]%3 != 0) {
                res += (1<<i);
            }
        }
        return res;
    }
}