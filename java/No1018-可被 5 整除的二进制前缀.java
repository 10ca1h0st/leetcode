class Solution {
    public List<Boolean> prefixesDivBy5(int[] A) {
        int remainder = 0;
        List<Boolean> ret = new ArrayList<>();
        for(int i : A) {
            remainder = remainder<<1;
            remainder = remainder + i;
            if(remainder % 5 == 0) {
                ret.add(true);
            }
            else {
                ret.add(false);
            }
            remainder = remainder % 5;
        }
        return ret;
    }
}