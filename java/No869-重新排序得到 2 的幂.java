class Solution {
    public boolean reorderedPowerOf2(int n) {
        List<Integer> nums = new ArrayList<>();
        int div = 10;
        while (n != 0) {
            int mod = n%div;
            n = n/div;
            nums.add(mod);
        }
        int length = nums.size();
        boolean[] used = new boolean[length];
        return dfs(nums, used, 0, 0);
    }

    public boolean dfs(List<Integer> nums, boolean[] used, int cur, int length) {
        if (length == nums.size()) {
            return (cur&(cur-1))==0;
        }
        for (int i = 0; i < used.length; i++) {
            if (!used[i] && (length > 0 || nums.get(i)!=0)) {
                used[i] = true;
                int _cur = cur;
                cur = cur*10 + nums.get(i);
                if (dfs(nums, used, cur, length+1)) {
                    return true;
                }
                used[i] = false;
                cur = _cur;
            }
        }
        return false;
    }
}