class Solution {
    public int lengthOfLastWord(String s) {
        int ans = 0;
        int i = 0;
        while (i < s.length()) {
            if (s.charAt(i) == ' ') {
                i++;
                continue;
            } else {
                int length = 0;
                int j = i;
                while (j < s.length() && s.charAt(j) != ' ') {
                    j++;
                }
                length = j-i;
                ans= length;
                i = j;
            }
        }
        return ans;
    }
}