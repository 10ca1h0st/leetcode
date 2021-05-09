// 官方解法 二分查找
class Solution {
    public int minDays(int[] bloomDay, int m, int k) {
        if (k * m > bloomDay.length) {
            return -1;
        }
        int low = 1, high = 1;
        int length = bloomDay.length;
        for (int i = 0; i < length; i++) {
            high = Math.max(high, bloomDay[i]);
        }
        while (low < high) {
            int days = (high - low) / 2 + low;
            if (canMake(bloomDay, days, m, k)) {
                high = days;
            } else {
                low = days + 1;
            }
        }
        return low;
    }

    public boolean canMake(int[] bloomDay, int days, int m, int k) {
        int bouquets = 0;
        int flowers = 0;
        int length = bloomDay.length;
        for (int i = 0; i < length && bouquets < m; i++) {
            if (bloomDay[i] <= days) {
                flowers++;
                if (flowers == k) {
                    bouquets++;
                    flowers = 0;
                }
            } else {
                flowers = 0;
            }
        }
        return bouquets >= m;
    }
}


// 超出时间限制 第84个样例
class Solution {
    public int minDays(int[] bloomDay, int m, int k) {
        int need = m * k;
        int length = bloomDay.length;
        if(need > length) {
            return -1;
        }
        // dp[i][j]代表用前j朵花组成i束花所需的最少天数
        int[][] dp = new int[m+1][k+1];
        // 默认就为0
        // for(int j = 0; j < length+1; j++) {
        //     dp[0][j] = 0;
        // }
        // 当j<k*i时，是非法的
        for(int i = 0; i < m+1; i++) {
            for(int j = 0; j < Math.min(k*i, k+1); j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        int ans = Integer.MAX_VALUE;
        for(int j = k; j < length+1; j++) {
            for(int i = 1; i < m+1; i++) {
                // 第j朵花构成了第i束花的最后一朵
                int choose = dp[i-1][(j-k) % (k+1)];
                if(choose != Integer.MAX_VALUE) {
                    for(int a = j-k+1; a <= j; a++) {
                        choose = Math.max(choose, bloomDay[a-1]);
                    }
                }
                // 前j-1朵花就能形成i束花
                int notChoose = dp[i][(j-1) % (k+1)];
                dp[i][j % (k+1)] = Math.min(choose, notChoose);
                if(i == m) {
                    ans = Math.min(ans, dp[i][j % (k+1)]);
                }
            }
        }
        return ans;
    }
}

// 超出内存限制 第83个样例
class Solution {
    public int minDays(int[] bloomDay, int m, int k) {
        int need = m * k;
        int length = bloomDay.length;
        if(need > length) {
            return -1;
        }
        // dp[i][j]代表用前j朵花组成i束花所需的最少天数
        int[][] dp = new int[m+1][length+1];
        // 默认就为0
        // for(int j = 0; j < length+1; j++) {
        //     dp[0][j] = 0;
        // }
        // 当j<k*i时，是非法的
        for(int i = 0; i < m+1; i++) {
            for(int j = 0; j < k*i; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        int ans = Integer.MAX_VALUE;
        for(int i = 1; i < m+1; i++) {
            for(int j = k*i; j < length+1; j++) {
                // 第j朵花构成了第i束花的最后一朵
                int choose = dp[i-1][j-k];
                if(choose != Integer.MAX_VALUE) {
                    for(int a = j-k+1; a <= j; a++) {
                        choose = Math.max(choose, bloomDay[a-1]);
                    }
                }
                // 前j-1朵花就能形成i束花
                int notChoose = dp[i][j-1];
                dp[i][j] = Math.min(choose, notChoose);
                if(i == m) {
                    ans = Math.min(ans, dp[i][j]);
                }
            }
        }
        return ans;
    }
}