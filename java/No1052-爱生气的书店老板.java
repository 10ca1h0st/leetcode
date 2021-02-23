class Solution {
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        // 一定满意的用户数
        int mustGood = 0;
        int length = customers.length;
        for(int i = 0; i < length; i++) {
            if(grumpy[i] == 0) {
                mustGood += customers[i];
            }
        }
        // 凭借长达X分钟的不生气，那些原本不满意的用户变满意
        int couldGood = 0;
        int couldGoodMax = 0;
        int left = 0;
        int right = 0;
        while(right < length) {
            if(grumpy[right] == 1) {
                couldGood += customers[right];
            }
            right++;
            if(right - left > X) {
                if(grumpy[left] == 1) {
                    couldGood -= customers[left];
                }
                left++;
            }
            couldGoodMax = Math.max(couldGoodMax, couldGood);
        }
        return mustGood + couldGoodMax;
    }
}