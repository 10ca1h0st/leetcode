class Solution {
    public int xorOperation(int n, int start) {
        int ans = start + 2 * 0;
        for(int i = 1; i < n; i++) {
            int cur = start + 2 * i;
            ans = ans ^ cur;
        }
        return ans;
    }
}