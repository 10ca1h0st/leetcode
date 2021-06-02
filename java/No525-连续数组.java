class Solution {
    public int findMaxLength(int[] nums) {
        if(nums.length < 2) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        // 将0当做-1
        int sum = 0;
        map.put(0, -1);
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == 1) {
                sum += 1;
            }
            else {
                sum -= 1;
            }
            if(map.containsKey(sum)) {
                ans = Math.max(ans, i - map.get(sum));
            }
            else {
                map.put(sum, i);
            }
        }
        return ans;
    }
}