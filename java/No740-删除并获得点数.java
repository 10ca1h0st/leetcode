class Solution {
    public int deleteAndEarn(int[] nums) {
        int maxVal = 0;
        for (int val : nums) {
            maxVal = Math.max(maxVal, val);
        }
        int[] sum = new int[maxVal + 1];
        for (int val : nums) {
            sum[val] += val;
        }
        return rob(sum);
    }

    public int rob(int[] nums) {
        int size = nums.length;
        int first = nums[0], second = Math.max(nums[0], nums[1]);
        for (int i = 2; i < size; i++) {
            int temp = second;
            second = Math.max(first + nums[i], second);
            first = temp;
        }
        return second;
    }
}

作者：LeetCode-Solution
链接：https://leetcode-cn.com/problems/delete-and-earn/solution/shan-chu-bing-huo-de-dian-shu-by-leetcod-x1pu/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。


// 自己写的，使用dfs，超时
class Solution {
    public int deleteAndEarn(int[] nums) {
        Map<Integer, Integer> counter = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        for(int num : nums) {
            set.add(num);
            int v = counter.getOrDefault(num, 0);
            v += 1;
            counter.put(num, v);
        }
        int ans = 0;
        // 存储已经选择或已经删除的num
        Set<Integer> done = new HashSet<>();
        for(int num : set) {
            int temp = 0;
            done.add(num);
            done.add(num+1);
            done.add(num-1);
            temp += num * counter.get(num);
            int res = dfs(counter, set, done);
            temp += res;
            ans = Math.max(ans, temp);
            done.remove(num);
            done.remove(num+1);
            done.remove(num-1);
        }
        return ans;
    }

    public int dfs(Map<Integer, Integer> counter, Set<Integer> set, Set<Integer> done) {
        int ans = 0;
        for(int num : set) {
            if(done.contains(num)) {
                continue;
            }
            int temp = 0;
            boolean remove1 = false;
            boolean remove2 = false;
            done.add(num);
            if(!done.contains(num+1)) {
                done.add(num+1);
                remove1 = true;
            }
            if(!done.contains(num-1)) {
                done.add(num-1);
                remove2 = true;
            }
            temp += num * counter.get(num);
            int res = dfs(counter, set, done);
            temp += res;
            ans = Math.max(ans, temp);
            done.remove(num);
            if(remove1) {
                done.remove(num+1);
            }
            if(remove2) {
                done.remove(num-1);
            }
        }
        return ans;
    }
}