class Solution {
    public int numDecodings(String s) {
        // dp[i]代表以s.charAt(i)结尾的子串，解码的方法总数
        // dp[i]为0代表以s.charAt(i)结尾的子串，不是一个合法的编码消息
        int[] dp = new int[s.length()];
        int i = 0;
        if(s.charAt(i) == '0') {
            return 0;
        }
        dp[i] = 1;
        for(i = 1; i < dp.length; i++) {
            char prev = s.charAt(i-1);
            char cur = s.charAt(i);
            // cur单独解码
            if('0' < cur && cur <= '9') {
                // 以s.charAt(i-1)结尾的子串是合法的
                if(dp[i-1] != 0) {
                    dp[i] += dp[i-1];
                }
            }
            // cur和prev一起解码
            if(prev == '0' || prev > '2') {
                continue;
            }
            int v = (prev-'0') * 10 + (cur-'0');
            if(v > 26) {
                continue;
            }
            if(i == 1) {
                dp[i] += 1;
            }
            else {
                // 判断以s.charAt(i-2)结尾的子串是否合法
                if(dp[i-2] != 0) {
                    dp[i] += dp[i-2];
                }
            }
        }
        return dp[dp.length-1];
    }
}