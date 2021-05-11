class Solution {
    public int[] xorQueries(int[] arr, int[][] queries) {
        // 使用前缀xor数组
        int[] prefix = new int[arr.length + 1];
        prefix[0] = 0;
        int xor = prefix[0];
        for(int i = 1; i < prefix.length; i++) {
            xor = xor ^ arr[i-1];
            prefix[i] = xor;
        }
        int[] ans = new int[queries.length];
        int i = 0;
        for(int[] gap : queries) {
            int left = gap[0];
            int right = gap[1];
            ans[i] = prefix[left] ^ prefix[right+1];
            i++;
        }
        return ans;
    }
}