// 超时 51/54
class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        int left = 0;
        int right = 0;
        List<Integer> min2max = new LinkedList<>();
        while(right < nums.length) {
            int i = 0;
            while(i < min2max.size()) {
                if(min2max.get(i) < left) {
                    min2max.remove(i);
                }
                else {
                    if(nums[right] < nums[min2max.get(i)]) {
                        break;
                    }
                    i++;
                }
            }
            min2max.add(i, right);
            if((i > 0 && (long)nums[right] - nums[min2max.get(i-1)] <= t) || (i < min2max.size()-1 && (long)nums[min2max.get(i+1)] - nums[right] <= t)) {
                return true;
            }
            right++;

            if(right - left > k) {
                left++;
            }
        }
        return false;
    }
}


// 官方题解 使用TreeSet维护窗口中的元素
class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        int n = nums.length;
        TreeSet<Long> set = new TreeSet<Long>();
        for (int i = 0; i < n; i++) {
            Long ceiling = set.ceiling((long) nums[i] - (long) t);
            if (ceiling != null && ceiling <= (long) nums[i] + (long) t) {
                return true;
            }
            set.add((long) nums[i]);
            if (i >= k) {
                set.remove((long) nums[i - k]);
            }
        }
        return false;
    }
}


// 官方解答 使用桶来维护窗口内元素
class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        int n = nums.length;
        Map<Long, Long> map = new HashMap<Long, Long>();
        long w = (long) t + 1;
        for (int i = 0; i < n; i++) {
            long id = getID(nums[i], w);
            if (map.containsKey(id)) {
                return true;
            }
            if (map.containsKey(id - 1) && Math.abs(nums[i] - map.get(id - 1)) < w) {
                return true;
            }
            if (map.containsKey(id + 1) && Math.abs(nums[i] - map.get(id + 1)) < w) {
                return true;
            }
            map.put(id, (long) nums[i]);
            if (i >= k) {
                map.remove(getID(nums[i - k], w));
            }
        }
        return false;
    }

    public long getID(long x, long w) {
        if (x >= 0) {
            return x / w;
        }
        return (x + 1) / w - 1;
    }
}