
class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        // 从小到大排序
        Arrays.sort(nums);
        // dp[i]表示包含nums[i]的最大整除子集
        int[] dp = new int[nums.length];
        // 因为要返回子集的具体信息，因此用map来记录最大整除子集的内容
        Map<Integer, List<Integer>> map = new HashMap<>();
        dp[0] = 1;
        map.put(0, new ArrayList<>(List.of(nums[0])));
        // 假设最大整除子集是dp[i]，则max_index == i
        int max_index = 0;
        // 最大整除子集的大小
        int max = dp[max_index];
        int i = 1;
        for(; i < nums.length; i++) {
            int j = i-1;
            // dp[i] = dp[i_max_index] + 1
            int i_max_index = -1;
            int i_max = -1;
            // 遍历之前的dp数组，超出dp[i]的值
            for(; j >= 0; j--) {
                if(nums[i] % nums[j] == 0) {
                    if(dp[j] + 1 > i_max) {
                        i_max_index = j;
                        i_max = dp[j] + 1;
                    }
                }
            }
            if(i_max_index == -1) {
                dp[i] = 1;
                map.put(i, new ArrayList<>(List.of(nums[i])));
            }
            else {
                dp[i] = i_max;
                List<Integer> t1 = map.get(i_max_index);
                List<Integer> t2 = new ArrayList<>(t1);
                t2.add(nums[i]);
                map.put(i, t2);
            }
            if(dp[i] > max) {
                max = dp[i];
                max_index = i;
            }
        }
        return map.get(max_index);
    }
}