class Solution {
    public String largestNumber(int[] cost, int target) {
		// 背包问题，定义dp[i][j]表示使用1到i这i个数字，其对应的cost之和刚好为j时，能够组成的最大数字，注意，是刚好等于j
        String[][] dp = new String[cost.length + 1][target + 1];
        for(int i = 0; i < dp.length; i++) {
            dp[i][0]= "0";
        }
        for(int i = 1; i < dp.length; i++) {
            for(int j = 1; j <= target; j++) {
                dp[i][j] = dp[i-1][j];
                if(j >= cost[i-1]) {
                    String temp = null;
					// 刚好为j
                    if(dp[i][j-cost[i-1]] != null) {
                        if(dp[i][j-cost[i-1]].compareTo("0") == 0) {
                            temp = String.valueOf(i);
                        }
                        else {
                            temp = String.valueOf(i) + dp[i][j-cost[i-1]];
                        }
                    }
                    if(temp == null) {
                        continue;
                    }
                    if(dp[i][j] == null) {
                        dp[i][j] = temp;
                    }
                    else if(dp[i][j].length() < temp.length()) {
                        dp[i][j] = temp;
                    }
                    else if(dp[i][j].length() == temp.length() && temp.compareTo(dp[i][j]) > 0) {
                        dp[i][j] = temp;
                    }
                }
            }
        }
        if(dp[cost.length][target] == null) {
            return "0";
        }
        return dp[cost.length][target];
    }
}