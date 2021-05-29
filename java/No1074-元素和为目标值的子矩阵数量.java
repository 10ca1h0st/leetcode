class Solution {
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] arr = new int[cols];
        int ans = 0;
        for(int end = 0; end < rows; end++) {
            arr = new int[cols];
            for(int start = end; start >=0; start--) {
                for(int i = 0; i < cols; i++) {
                    arr[i] += matrix[start][i];
                }
                ans += subArr(arr, target);
            }
        }
        return ans;
    }

    public int subArr(int[] nums, int k) {
        int count = 0, pre = 0;
        HashMap < Integer, Integer > mp = new HashMap < > ();
        mp.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            pre += nums[i];
            if (mp.containsKey(pre - k)) {
                count += mp.get(pre - k);
            }
            mp.put(pre, mp.getOrDefault(pre, 0) + 1);
        }
        return count;
    }
}