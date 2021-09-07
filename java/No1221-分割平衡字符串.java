class Solution {
    public int balancedStringSplit(String s) {
        int countL = 0;
        int countR = 0;
        int ans = 0;
        char[] chs = s.toCharArray();
        for (char ch : chs) {
            if (ch == 'L') {
                countL++;
            } else {
                countR++;
            }
            if (countL == countR) {
                ans++;
                countL = 0;
                countR = 0;
            }
        }
        return ans;
    }
}