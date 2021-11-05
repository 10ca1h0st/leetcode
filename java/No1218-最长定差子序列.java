class Solution {
    public int longestSubsequence(int[] arr, int difference) {
        int ans = 0;
        Map<Integer, Integer> dp = new HashMap<Integer, Integer>();
        for (int v : arr) {
            dp.put(v, dp.getOrDefault(v - difference, 0) + 1);
            ans = Math.max(ans, dp.get(v));
        }
        return ans;
    }
}

作者：LeetCode-Solution
链接：https://leetcode-cn.com/problems/longest-arithmetic-subsequence-of-given-difference/solution/zui-chang-ding-chai-zi-xu-lie-by-leetcod-xkua/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。


//不使用哈希表会超时
class Solution {
    public int longestSubsequence(int[] arr, int difference) {
        int[] dp = new int[arr.length];
        Arrays.fill(dp, 1);
        int max = 1;
        for (int i = 1; i < arr.length; i++) {
            for (int j = i-1; j >= 0; j--) {
                if (arr[i]-arr[j]==difference && dp[j]+1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    break;
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}