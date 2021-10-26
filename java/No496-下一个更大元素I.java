class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] ans = new int[nums1.length];
        Map<Integer, Integer> index = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            index.put(nums1[i], i);
        }
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = nums2.length-1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums2[i]) {
                stack.pop();
            }
            if (index.containsKey(nums2[i])) {
                if (stack.isEmpty()) {
                    ans[index.get(nums2[i])] = -1;
                } else {
                    ans[index.get(nums2[i])] = stack.peek();
                }
            }
            stack.push(nums2[i]);
        }
        return ans;
    }
}