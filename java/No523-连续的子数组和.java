// 超时
class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        int[] prefixSum = new int[nums.length + 1];
        int sum = prefixSum[0];
        for(int i = 1; i < prefixSum.length; i++) {
            sum += nums[i-1];
            prefixSum[i] = sum;
        }
        int minus = 0;
        for(int end = prefixSum.length-1; end >= 2; end--) {
            for(int start = 0; start <= end-2; start++) {
                minus = prefixSum[end] - prefixSum[start];
                if(minus % k == 0) {
                    return true;
                }
            }
        }
        return false;
    }
}

// 官方解答
class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        int m = nums.length;
        if (m < 2) {
            return false;
        }
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0, -1);
        int remainder = 0;
        for (int i = 0; i < m; i++) {
            remainder = (remainder + nums[i]) % k;
            if (map.containsKey(remainder)) {
                int prevIndex = map.get(remainder);
                if (i - prevIndex >= 2) {
                    return true;
                }
            } else {
                map.put(remainder, i);
            }
        }
        return false;
    }
}

作者：LeetCode-Solution
链接：https://leetcode-cn.com/problems/continuous-subarray-sum/solution/lian-xu-de-zi-shu-zu-he-by-leetcode-solu-rdzi/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。