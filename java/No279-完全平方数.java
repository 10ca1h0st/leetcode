class Solution {
    public int numSquares(int n) {
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        int squareRoot = 2;
        for(int i = 2; i <= n; i++) {
            if(i == squareRoot*squareRoot) {
                dp[i] = 1;
                squareRoot++;
            }
            else {
                dp[i] = Integer.MAX_VALUE;
                for(int j = 1; j < squareRoot; j++) {
                    dp[i] = Math.min(dp[i], 1 + dp[i-j*j]);
                }
            }
        }
        return dp[n];
    }
}