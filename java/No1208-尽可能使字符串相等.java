class Solution {
    public int equalSubstring(String s, String t, int maxCost) {
        int left = 0;
        int right = 0;
        int ret = 0;
        int curCost = 0;
        int length = s.length();
        while(right < length) {
            curCost += Math.abs(s.charAt(right) - t.charAt(right));
            right++;
            // 现在，curCost代表[left, right)区间中所有消耗之和
            while(curCost > maxCost) {
                curCost -= Math.abs(s.charAt(left) - t.charAt(left));
                left++;
            }
            // 现在，curCost满足<=maxCost的要求
            ret = Math.max(ret, right-left);
        }
        return ret;
    }
}