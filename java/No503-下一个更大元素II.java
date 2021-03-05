class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int length = nums.length;
        if(length <= 0) {
            return new int[0];
        }
        Deque<Integer> stack = new ArrayDeque<>();
        int[] res = new int[length];
        int i = 0;
        for(i = 2 * length - 1; i >= 0; i--) {
            int cur = nums[i%length];
            while(!stack.isEmpty() && nums[stack.peek()] <= cur) {
                stack.pop();
            }
            if(stack.isEmpty()) {
                res[i%length] = -1;
            }
            else {
                res[i%length] = nums[stack.peek()];
            }
            stack.push(i%length);
        }
        return res;
    }
}