// 超出内存限制
class Solution {
    public int maxIceCream(int[] costs, int coins) {
        // dp[i][j]表示用价值为i的现金买前j个雪糕，能买到的最大数量
        // dp[i][j] = max(dp[i][j-1], dp[i-costs[j-1]][j-1] + 1)
        int[][] dp = new int[coins+1][costs.length+1];
        dp[0][0] = 0;
        // for(int i = 0; i < dp.length; i++) {
        //     dp[i][0] = 0;
        // }
        // for(int j = 0; j < dp[0].length; j++) {
        //     dp[0][j] = 0;
        // }
        for(int i = 1; i <= coins; i++) {
            for(int j = 1; j <= costs.length; j++) {
                dp[i][j] = dp[i][j-1];
                if(i >= costs[j-1]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i-costs[j-1]][j-1] + 1);
                }
            }
        }
        return dp[coins][costs.length];
    }
}

// 直接排序数组，然后贪心
class Solution {
    public int maxIceCream(int[] costs, int coins) {
        Arrays.sort(costs);
        int ans = 0;
        for(int cost : costs) {
            if(coins >= cost) {
                ans++;
                coins -= cost;
            }
            else {
                break;
            }
        }
        return ans;
    }
}

// 官方使用计数排序
class Solution {
    public int maxIceCream(int[] costs, int coins) {
        int[] freq = new int[100001];
        for (int cost : costs) {
            freq[cost]++;
        }
        int count = 0;
        for (int i = 1; i <= 100000; i++) {
            if (coins >= i) {
                int curCount = Math.min(freq[i], coins / i);
                count += curCount;
                coins -= i * curCount;
            } else {
                break;
            }
        }
        return count;
    }
}

作者：LeetCode-Solution
链接：https://leetcode-cn.com/problems/maximum-ice-cream-bars/solution/xue-gao-de-zui-da-shu-liang-by-leetcode-ia3m7/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。