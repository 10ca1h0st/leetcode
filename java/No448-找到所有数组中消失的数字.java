class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> ret = new ArrayList<>();
        boolean[] exist = new boolean[nums.length+1];
        for(int n : nums) {
            if(!exist[n]) {
                exist[n] = true;
            }
        }
        for(int i = 1; i < exist.length; i++) {
            if(!exist[i]) {
                ret.add(i);
            }
        }
        return ret;
    }
}