class Solution {
    private int ans;
    private Map<Integer, Integer> val2indexStart;
    private Map<Integer, Integer> val2indexEnd;

    public int triangleNumber(int[] nums) {
        val2indexStart = new HashMap<>();
        val2indexEnd = new HashMap<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length/2; i++) {
            int temp = nums[i];
            nums[i] = nums[nums.length-1-i];
            nums[nums.length-1-i] = temp;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!val2indexStart.containsKey(nums[i])) {
                val2indexStart.put(nums[i], i);
            }
            val2indexEnd.put(nums[i], i);
        }
        ans = 0;
        List<Integer> edges = new ArrayList<>();
        backtrace(nums, 0, edges);
        return ans;
    }

    private void backtrace(int[] nums, int start, List<Integer> edges) {
        for (int i = start; i < nums.length; i++) {
            edges.add(nums[i]);
            if (edges.size() == 2) {
                int big = edges.get(0);
                int mid = edges.get(1);
                int small = big-mid+1;
                if (small == mid) {
                    ans += val2indexEnd.get(small) - i;
                } else if (small < mid) {
                    while (small <= mid && !val2indexEnd.containsKey(small)) {
                        small++;
                    }
                    if (val2indexEnd.containsKey(small)) {
                        ans += val2indexEnd.get(small) - i;
                    }
                } else {
                }
            } else {
                backtrace(nums, i+1, edges);
            }
            edges.remove(edges.size()-1);
        }
    }
}