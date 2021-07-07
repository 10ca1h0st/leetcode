// 超时, 普通遍历
class Solution {
    private final static int MOD = 1_000_000_000 + 7;

    public int countPairs(int[] deliciousness) {
        int ans = 0;
        int length = deliciousness.length;
        int curFood = -1;
        long sum = -1;
        for (int i = 1; i < length; i++) {
            curFood = deliciousness[i];
            for (int j = i - 1; j >= 0; j--) {
                // 直接将相加两个菜品，可能会溢出，所以使用long保存
                sum = curFood + deliciousness[j];
                if (isPowerOfTwo(sum)) {
                    ans = ans + 1;
                    if(ans == MOD) {
                        ans = 0;
                    }
                }
            }
        }
        return ans;
    }

    private boolean isPowerOfTwo(long n) {
        if (n == 0) {
            return false;
        }
        if ((n & (n-1)) == 0) {
            return true;
        } else {
            return false;
        }
    }
}

// 官方解答
class Solution {
    public int countPairs(int[] deliciousness) {
        final int MOD = 1000000007;
        int maxVal = 0;
        for (int val : deliciousness) {
            maxVal = Math.max(maxVal, val);
        }
        int maxSum = maxVal * 2;
        int pairs = 0;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int n = deliciousness.length;
        for (int i = 0; i < n; i++) {
            int val = deliciousness[i];
            for (int sum = 1; sum <= maxSum; sum <<= 1) {
                int count = map.getOrDefault(sum - val, 0);
                pairs = (pairs + count) % MOD;
            }
            map.put(val, map.getOrDefault(val, 0) + 1);
        }
        return pairs;
    }
}

作者：LeetCode-Solution
链接：https://leetcode-cn.com/problems/count-good-meals/solution/da-can-ji-shu-by-leetcode-solution-fvg9/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。