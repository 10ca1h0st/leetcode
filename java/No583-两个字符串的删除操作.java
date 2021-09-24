class Solution {
    public int minDistance(String word1, String word2) {
        return word1.length() + word2.length() - 2*lcs(word1, word2);
    }

    public int lcs(String a, String b) {
        int len1 = a.length();
        int len2 = b.length();
        int[][] dp = new int[len1+1][len2+1];
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (a.charAt(i-1) == b.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
                }
            }
        }
        return dp[len1][len2];
    }
}