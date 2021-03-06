class Solution {
    public int minDistance(String word1, String word2) {
        int length1 = word1.length();
        int length2 = word2.length();
        if(length1 == 0) {
            return length2;
        }
        if(length2 == 0) {
            return length1;
        }
        // dp[i][j]代表word1前i个字符和word2前j个字符之间的编辑距离
        int[][] dp = new int[length1+1][length2+1];
        // base case
        for(int i = 1; i <= length1; i++) {
            dp[i][0] = i;
        }
        for(int j = 1; j <= length2; j++) {
            dp[0][j] = j;
        }
        for(int i = 1; i <= length1; i++) {
            for(int j = 1; j <= length2; j++) {
                if(word1.charAt(i-1) == word2.charAt(j-1)) {
                    // dp[i][j] = min(dp[i-1][j-1], dp[i-1][j] + 1, dp[i][j-1] + 1);
                    dp[i][j] = dp[i-1][j-1];
                }
                else {
                    // dp[i-1][j-1]代表替换，dp[i-1][j]代表删除，dp[i][j-1]代表插入
                    dp[i][j] = min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]) + 1;
                }
            }
        }
        return dp[length1][length2];
    }

    public static int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }
}