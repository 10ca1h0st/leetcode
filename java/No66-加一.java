class Solution {
    public int[] plusOne(int[] digits) {
        int i = 0;
        for (i = digits.length-1; i >= 0; i--) {
            if (digits[i] < 9) {
                break;
            }
        }
        if (i < 0) {
            int[] ans = new int[digits.length+1];
            ans[0] = 1;
            return ans;
        }
        int[] ans = new int[digits.length];
        System.arraycopy(digits, 0, ans, 0, i+1);
        ans[i] += 1;
        return ans;
    }
}