class Solution {
    public int titleToNumber(String columnTitle) {
        char[] arr = columnTitle.toCharArray();
        int ans = 0;
        for (char ch : arr) {
            ans = ans * 26 + (int)(ch-'A') + 1;
        }
        return ans;
    }
}