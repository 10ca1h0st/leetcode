class Solution {
    public List<Integer> addToArrayForm(int[] A, int K) {
        int length = A.length;
        int carry = 0;
        int i = length-1;
        List<Integer> ret = new ArrayList<>();
        while(i >=0 || K > 0) {
            if(i < 0) {
                int addition = carry + K%10;
                carry = addition / 10;
                addition = addition % 10;
                ret.add(0, addition);
                K = K / 10;
            }
            else if(K == 0) {
                int addition = carry + A[i];
                carry = addition / 10;
                addition = addition % 10;
                ret.add(0, addition);
                i--;
            }
            else {
                int addition = carry + K%10 + A[i];
                carry = addition / 10;
                addition = addition % 10;
                ret.add(0, addition);
                i--;
                K = K / 10;
            }
        }
        if(carry > 0) {
            ret.add(0, carry);
        }
        return ret;
    }
}