class Solution {
    public void rotate(int[] nums, int k) {
        if(nums.length == 0) {
            return;
        }
        int length = nums.length;
        k = k%length;
        boolean right = true;
        if(k > length - k) {
            right = false;
            k = length - k;
        }
        while(k-- > 0) {
            if(right) {
                int i = 0;
                int prev = nums[i];
                for(i = 1; i <= length; i++) {
                    int temp = nums[i%length];
                    nums[i%length] = prev;
                    prev = temp;
                }
            }
            else {
                int i = length - 1;
                int prev = nums[i];
                nums[i] = nums[0];
                for(i = length - 2; i >= 0; i--) {
                    int temp = nums[i];
                    nums[i] = prev;
                    prev = temp;
                }
            }
        }
    }
}