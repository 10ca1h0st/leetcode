class Solution {
    public int findLHS(int[] nums) {
        Arrays.sort(nums);
        int prev = -1000000000-2;
        int countPrev = 0;
        int countCur = 0;
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            countCur = 0;
            while (i < nums.length && nums[i] == cur) {
                countCur++;
                i++;
            }
            if (cur-prev == 1) {
                ans = Math.max(ans, countCur+countPrev);
            }
            countPrev = countCur;
            prev = cur;
            i--;
        }
        return ans;
    }
}