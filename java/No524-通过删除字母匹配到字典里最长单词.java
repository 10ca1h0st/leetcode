class Solution {
    public String findLongestWord(String s, List<String> dictionary) {
        Collections.sort(dictionary, new Comparator<String>() {
            @Override
            public int compare(String s, String t) {
                if (s.length() > t.length()) {
                    return -1;
                } else if (s.length() < t.length()) {
                    return 1;
                } else {
                    return s.compareTo(t);
                }
            }
        });
        for (String t : dictionary) {
            if (t.length() > s.length()) {
                continue;
            }
            if(lcs(s, t) == t.length()) {
                return t;
            }
        }
        return "";
    }

    public int lcs(String s, String t) {
        int[][] dp = new int[t.length()+1][s.length()+1];
        for (int i = 1; i <= t.length(); i++) {
            for (int j = 1; j <= s.length(); j++) {
                if (t.charAt(i-1) == s.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
                }
            }
            if (dp[i][s.length()] != i) {
                return -1;
            } 
        }
        return dp[t.length()][s.length()];
    }
}