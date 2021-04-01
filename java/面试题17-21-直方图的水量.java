// 接雨水问题，每一个格子能接的雨水取决于这个格子左右两边的最大高度的较小者和该格子高度的差
// 优化技巧，使用双指针
// 用变量leftMaxTillNow表示左指针左边所有格子的最大高度
// 用变量rightMaxTillNow表示右指针右边所有格子的最大高度
// 情况1：
// 当leftMaxTillNow小于等于rightMaxTillNow的时候，对于左指针所指的格子
// 若该格子的高度小于leftMaxTillNow，则该格子能装的水量为leftMaxTillNow与该格子高度之差
// 若该格子的高度大于leftMaxTillNow，则该格子不能装水
// 将left自增
// 情况2：当leftMaxTillNow大于rightMaxTillNow的时候，和情况1分析相似
class Solution {
    public int trap(int[] height) {
        if(height.length <= 1) {
            return 0;
        }
        int left = 0;
        int right = height.length - 1;
        int leftMaxTillNow = height[left];
        int rightMaxTillNow = height[right];
        int res = 0;
        left++;
        right--;
        while(left <= right) {
            if(leftMaxTillNow <= rightMaxTillNow) {
                if(height[left] <= leftMaxTillNow) {
                    res += leftMaxTillNow - height[left];
                }
                else {
                    leftMaxTillNow = height[left];
                }
                left++;
            }
            else {
                // leftMaxTillNow > rightMaxTillNow
                if(height[right] <= rightMaxTillNow) {
                    res += rightMaxTillNow - height[right];
                }
                else {
                    rightMaxTillNow = height[right];
                }
                right--;
            }
        }
        return res;
    }
}