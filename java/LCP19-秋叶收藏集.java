class Solution {
    public int minimumOperations(String leaves) {
        // dp[i][j]代表使第j个叶子（索引从0开始）为第i部分所需的最少操作次数
        // 第0部分是r，第1部分是y，第2部分是r
        int[][] dp = new int[3][leaves.length()];
        dp[0][0] = leaves.charAt(0)=='r'?0:1;
        // 因为最多操作次数不多于leaves.length()次，所以leaves.length()+1代表MAX_VALUE
        dp[1][0] = leaves.length()+1;
        dp[2][0] = leaves.length()+1;
        int i = 0;
        for(int j = 1; j < leaves.length(); j++) {
            if(leaves.charAt(j) == 'r') {
                dp[0][j] = dp[0][j-1];
            }
            else {
                dp[0][j] = dp[0][j-1] + 1;
            }
        }
        i = 1;
        for(int j = 1; j < leaves.length(); j++) {
            if(leaves.charAt(j) == 'r') {
                dp[1][j] = Math.min(dp[0][j-1], dp[1][j-1]) + 1;
            }
            else {
                dp[1][j] = Math.min(dp[0][j-1], dp[1][j-1]);
            }
        }
        i = 2;
        for(int j = 1; j < leaves.length(); j++) {
            if(leaves.charAt(j) == 'r') {
                dp[2][j] = Math.min(dp[2][j-1], dp[1][j-1]);
            }
            else {
                dp[2][j] = Math.min(dp[2][j-1], dp[1][j-1]) + 1;
            }
        }
        return dp[2][leaves.length()-1];
    }
}