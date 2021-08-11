class Solution {
    public int numberOfArithmeticSlices(int[] nums) {
        if (nums.length < 3) {
            return 0;
        }
        // memo[i]表示以nums[i]结尾的等差子序列的映射(差值=>个数)
        Map<Long, Integer>[] memo = new Map[nums.length];
        // hasTwo[i]表示以nums[i]结尾的长度为2的序列的映射(差值=>个数)
        Map<Long, Integer>[] hasTwo = new Map[nums.length];
        for (int i = 0; i < memo.length; i++) {
            memo[i] = new HashMap<>();
            hasTwo[i] = new HashMap<>();
        }
        hasTwo[1].put((long)nums[1]-nums[0], 1);
        int ans = 0;
        for (int i = 2; i < nums.length; i++) {
            for (int j = i-1; j >= 0; j--) {
                long diff = (long)nums[i] - nums[j];
                //更新以nums[i]结尾的hasTwo
                hasTwo[i].put(diff, hasTwo[i].getOrDefault(diff, 0)+1);
                // 更新以nums[i]结尾的memo
                // 长度为2
                if (hasTwo[j].containsKey(diff)) {
                    memo[i].put(diff, memo[i].getOrDefault(diff, 0)+hasTwo[j].get(diff));
                    ans += hasTwo[j].get(diff);
                }
                // 长度大于2
                if (memo[j].containsKey(diff)) {
                    memo[i].put(diff, memo[i].getOrDefault(diff, 0)+memo[j].get(diff));
                    ans += memo[j].get(diff);
                }
            }
        }
        return ans;
    }
}