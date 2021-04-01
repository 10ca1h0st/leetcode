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