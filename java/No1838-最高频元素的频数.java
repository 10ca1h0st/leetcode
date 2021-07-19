// 官方解答 使用滑动窗口
class Solution {
    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        long total = 0;
        int l = 0, res = 1;
        for (int r = 1; r < n; ++r) {
            total += (long) (nums[r] - nums[r - 1]) * (r - l);
            while (total > k) {
                total -= nums[r] - nums[l];
                ++l;
            }
            res = Math.max(res, r - l + 1);
        }
        return res;
    }
}

作者：LeetCode-Solution
链接：https://leetcode-cn.com/problems/frequency-of-the-most-frequent-element/solution/zui-gao-pin-yuan-su-de-pin-shu-by-leetco-q5g9/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

class Solution {
    public int maxFrequency(int[] nums, int k) {
        Map<Integer, Integer> freqs = new HashMap<>();
        for (int num : nums) {
            freqs.put(num, freqs.getOrDefault(num, 0) + 1);
        }
        // 对键排序,倒序
        List<Integer> keys = new ArrayList<>(freqs.keySet());
        Collections.sort(keys, Collections.reverseOrder());

        int ans = 0;
        int i = 0;
        for (; i < keys.size(); i++) {
            int key = keys.get(i);
            int freq = freqs.get(key);
            // 什么也不用操作的频数
            int res = freq;
            int times = k;
            int j = i + 1;
            while (j < keys.size()) {
                int nextKey = keys.get(j);
                int nextFreq = freqs.get(nextKey);
                int diff = key - nextKey;
                // 使用操作能改变的个数
                int changeCount = times / diff;
                if (changeCount > nextFreq) {
                    res += nextFreq;
                    times -= nextFreq * diff;
                } else {
                    res += changeCount;
                    times -= changeCount * diff;
                    break;
                }
                j++;
            }
            ans = Math.max(res, ans);
        }
        return ans;
    }
}