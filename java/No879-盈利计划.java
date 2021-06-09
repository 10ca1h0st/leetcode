// 官方解答
class Solution {
    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        int len = group.length, MOD = (int)1e9 + 7;
        int[][][] dp = new int[len + 1][n + 1][minProfit + 1];
        dp[0][0][0] = 1;
        for (int i = 1; i <= len; i++) {
            int members = group[i - 1], earn = profit[i - 1];
            for (int j = 0; j <= n; j++) {
                for (int k = 0; k <= minProfit; k++) {
                    if (j < members) {
                        dp[i][j][k] = dp[i - 1][j][k];
                    } else {
                        dp[i][j][k] = (dp[i - 1][j][k] + dp[i - 1][j - members][Math.max(0, k - earn)]) % MOD;
                    }
                }
            }
        }
        int sum = 0;
        for (int j = 0; j <= n; j++) {
            sum = (sum + dp[len][j][minProfit]) % MOD;
        }
        return sum;
    }
}

作者：LeetCode-Solution
链接：https://leetcode-cn.com/problems/profitable-schemes/solution/ying-li-ji-hua-by-leetcode-solution-3t8o/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。


// 在39/45超出内存限制
class Solution {
    static int MOD = 1000000000 + 7;

    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        int tasks = profit.length;
        int maxProfit = 0;
        for(int p : profit) {
            maxProfit += p;
        }
        // dp[i][j][k]表示从前i个任务中选，利润为j，所需人数为k的计划数
        int[][][] dp = new int[tasks+1][maxProfit+1][n+1];
        for(int i = 0; i < tasks + 1; i++) {
            dp[i][0][0] = 1;
        }
        int ans = 0;
        for(int i = 1; i < tasks + 1; i++) {
            for(int j = 0; j < maxProfit + 1; j++) {
                for(int k = 0; k < n + 1; k++) {
                    // dp[i][j][k] = dp[i-1][j-profit[i-1]][k-group[i-1]] + dp[i-1][j][k];
                    dp[i][j][k] = dp[i-1][j][k];
                    if(k >= group[i-1] && j >= profit[i-1]) {
                        dp[i][j][k] = (int)(((long)dp[i][j][k] + (long)dp[i-1][j-profit[i-1]][k-group[i-1]]) % MOD);
                    }
                    dp[i][j][k] = dp[i][j][k] % MOD;
                    if(i == tasks && j >= minProfit) {
                        ans = (int)(((long)ans + (long)dp[i][j][k]) % MOD);
                    }
                }
            }
        }
        return ans;
    }
}

// 击败了5%
class Solution {
    static int MOD = 1000000000 + 7;

    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        int tasks = profit.length;
        int maxProfit = 0;
        for(int p : profit) {
            maxProfit += p;
        }
        // dp[i][j][k]表示从前i个任务中选，利润为j，所需人数为k的计划数
        // int[][][] dp = new int[tasks+1][maxProfit+1][n+1];
        int[][][] dp = new int[2][maxProfit+1][n+1];
        dp[0][0][0] = 1;
        int ans = 0;
        for(int reali = 1; reali < tasks + 1; reali++) {
            int i = reali % 2;
            for(int j = 0; j < maxProfit + 1; j++) {
                for(int k = 0; k < n + 1; k++) {
                    if(j == 0 && k == 0) {
                        dp[i][0][0] = 1;
                    }
                    else {
                        // i^1的作用是1-->0,0-->1
                        dp[i][j][k] = dp[i ^ 1][j][k];
                        if(k >= group[reali-1] && j >= profit[reali-1]) {
                            dp[i][j][k] = (int)(((long)dp[i][j][k] + (long)dp[i ^ 1][j-profit[reali-1]][k-group[reali-1]]) % MOD);
                        }
                    }
                    dp[i][j][k] = dp[i][j][k] % MOD;
                    if(reali == tasks && j >= minProfit) {
                        ans = (int)(((long)ans + (long)dp[i][j][k]) % MOD);
                    }
                }
            }
        }
        return ans;
    }
}