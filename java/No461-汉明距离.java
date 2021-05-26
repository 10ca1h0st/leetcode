class Solution {
    public int hammingDistance(int x, int y) {
        int xor = x ^ y;
        int ans = 0;
        while(xor != 0) {
            ans++;
            xor = xor & (xor-1);
        }
        return ans;
    }
}