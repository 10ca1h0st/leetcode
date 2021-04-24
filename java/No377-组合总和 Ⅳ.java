class Solution {
    Map<Integer, Integer> map;

    public int combinationSum4(int[] nums, int target) {
        map = new HashMap<>();
        Arrays.sort(nums);
        int ans = 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == target) {
                ans++;
                continue;
            }
            int t = dfs(nums, nums[i], target);
            ans += t;
        }
        return ans;
    }

    // 回溯法
    public int dfs(int[] nums, int sum, int target) {
        if(map.containsKey(target-sum)) {
            return map.get(target-sum);
        }
        int count = 0;
        for(int i = 0 ; i < nums.length; i++) {
            if(sum + nums[i] > target) {
                break;
            }
            if(sum + nums[i] == target) {
                count++;
                break;
            }
            count += dfs(nums, sum + nums[i], target);
        }
        map.put(target-sum, count);
        return count;
    }
}

// 官方解答 更清晰
class Solution {
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (num <= i) {
                    dp[i] += dp[i - num];
                }
            }
        }
        return dp[target];
    }
}

作者：LeetCode-Solution
链接：https://leetcode-cn.com/problems/combination-sum-iv/solution/zu-he-zong-he-iv-by-leetcode-solution-q8zv/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。