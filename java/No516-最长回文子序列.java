class Solution {
    public int longestPalindromeSubseq(String s) {
        StringBuilder sb = new StringBuilder(s);
        String s2 = sb.reverse().toString();
        return longestCommonSubseq(s, s2);
    }

    private int longestCommonSubseq(String s1, String s2) {
        // dp[i][j]表示s1的前i个字符组成的字符串和s2前j个的最长公共子序列
        int[][] dp = new int[s1.length()+1][s2.length()+1];
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if (s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }
}